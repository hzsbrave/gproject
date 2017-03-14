package com.gproject.solr.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.category.mapper.CategoryMapperCustom;
import com.gproject.category.pojo.CategoryCustom;
import com.gproject.category.pojo.vo.CategoryExample;
import com.gproject.shoppingcartprods.mapper.ShoppingCartProdCustomMapper;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProdCustom;
import com.gproject.solr.pojo.query.ProductDetailQueryVo;
import com.gproject.solr.pojo.vo.ProductDetail;
import com.gproject.solr.Util.QueryUtils;
import com.gproject.solr.base.ProductSolrIndexQueryAdapter;
import com.gproject.solr.base.SolrIndexQuery;
import com.gproject.solr.base.SolrServerFactory;
import com.gproject.solr.facade.ProductSolrFacade;
import com.gproject.solr.pojo.query.Page;
import com.gproject.solr.pojo.query.SeachParam;
import com.gproject.solr.pojo.vo.ProductCustom;
import com.gproject.util.message.ResponseType;
import com.gproject.util.redis.core.RedisTemplate;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ProductSolrService.java
 * describe: 产品搜索逻辑层
 * Author hezishan
 * Date 2017/2/27 20:24
 */
@Service
public class ProductSolrService extends BaseService<ProductCustom, Integer> implements ProductSolrFacade {

    @Autowired
    private SolrServerFactory factory;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;
    @Autowired
    private ShoppingCartProdCustomMapper shoppingCartProdCustomMapper;

    public Object searchProduct(SeachParam param) throws Exception {
        if (null == param)
            return FAIL(ResponseType.PARAMETER_NULL, "search parameter is null");
        List<ProductCustom> prods = new ArrayList<ProductCustom>();
        Gson gson = new Gson();
        Page<ProductCustom> products = new Page<ProductCustom>().setPageNo(param.getPageNo()).setPageSize(param.getPageSize());
        String keyword = param.getKeyword();
        if ("".equals(keyword)) {
            keyword = "all";
        }
        //从redis中读取数据
        String json = (String) redisTemplate.get(keyword);
        if (StringUtils.isBlank(json)) {
            SolrQuery query = QueryUtils.buildProductSearchQuery(param);
            SolrIndexQuery solrIndexQuery = new ProductSolrIndexQueryAdapter(factory.getProductServer()).query(query);
            prods = solrIndexQuery.asList(ProductCustom.class);
            //销售量处理后的prods
            prods = handlerSalesNum(prods);
            System.out.println(prods);
            //放入redis缓存
            redisTemplate.set(keyword, gson.toJson(prods), 43200);
        } else {
            Type type = new TypeToken<ArrayList<ProductCustom>>() {
            }.getType();
            prods = gson.fromJson(json.toString(), type);
            System.out.println(prods);
        }
        //根据分页码，截取产品列表
        int count = (param.getPageNo()) + (param.getPageSize());
        int total = prods.size();
        List<ProductCustom> subprods = null;
        if (count > total) {
            subprods = prods.subList(param.getPageNo() - 1, total);
        } else {
            subprods = prods.subList(param.getPageNo() - 1, count);
        }
        products = products.setResultList(subprods).setTotalCount(prods.size());
        System.out.println(products.getResultList().size());
        return SUCCESS(products);
    }

    @Override
    public Object searchProducsByProdId(List list) {
        //查询语句
        SolrQuery query = QueryUtils.buildQueryByProdIds(list);
        //产品查询
        SolrIndexQuery solrIndexQuery = new ProductSolrIndexQueryAdapter(factory.getProductServer()).query(query);
        List<ProductCustom> prods = solrIndexQuery.asList(ProductCustom.class);
        //处理销售量
        prods=handlerSalesNum(prods);
        return SUCCESS(prods);
    }

    @Override
    public Object searchProductById(int prodId) {
        //从redis中读取缓存
        String json = (String) redisTemplate.get("productId" + prodId);
        Gson gson = new Gson();
        List<ProductCustom> prods = new ArrayList<ProductCustom>();
        ProductCustom prod = new ProductCustom();
        //缓存中不存在
        if (StringUtils.isBlank(json)) {
            //查询语句
            SolrQuery query = QueryUtils.buildQueryByProdId(prodId);
            //产品查询
            SolrIndexQuery solrIndexQuery = new ProductSolrIndexQueryAdapter(factory.getProductServer()).query(query);
            prods = solrIndexQuery.asList(ProductCustom.class);
            //放在缓存
            prods = handlerSalesNum(prods);
            if (null != prods && 0 != prods.size()) {
                prod = prods.get(0);
            }
        } else {
            //从缓存中读取
            Type type = new TypeToken<ProductCustom>() {
            }.getType();
            prod = gson.fromJson(json.toString(), type);
        }
        ProductDetail productDetail = getProductDetail(prod);
        CategoryExample example = new CategoryExample();
        example.setCondition(prod.getCategoryId());
        CategoryCustom cat = categoryMapperCustom.selectCategoryById(example);
        productDetail.setCategoryName(cat.getCategoryName());
        return SUCCESS(productDetail);
    }

    @Override
    public Object checkProductNum(ProductDetailQueryVo vo) {
        redisTemplate.delete("productId1");
        if(null==vo)
            return FAIL(ResponseType.PARAMETER_NULL,"query is null");
        String json=(String) redisTemplate.get("productId"+vo.getProductId());

        Gson gson = new Gson();
        List<ProductCustom> prods = new ArrayList<ProductCustom>();
        ProductCustom prod = new ProductCustom();
        if(StringUtils.isBlank(json)){
            //查询语句
            SolrQuery query = QueryUtils.buildQueryByProdId(vo.getProductId());
            //产品查询
            SolrIndexQuery solrIndexQuery = new ProductSolrIndexQueryAdapter(factory.getProductServer()).query(query);
            prods = solrIndexQuery.asList(ProductCustom.class);
            //放在缓存
            prods = handlerSalesNum(prods);
            if (null != prods && 0 != prods.size()) {
                prod = prods.get(0);
            }
        }else{
            //从缓存中读取
            Type type = new TypeToken<ProductCustom>() {
            }.getType();
            prod = gson.fromJson(json.toString(), type);
        }
        if(vo.getCount() > prod.getProductNum())
            return FAIL(ResponseType.PARAMETER_ERROR,prod.getProductNum()+"");
        ShoppingCartProdCustom cartProdCustom=new ShoppingCartProdCustom();
        cartProdCustom.setCartId(vo.getCartId());
        cartProdCustom.setProductId(vo.getProductId());
        cartProdCustom.setAmount(vo.getCount());
        shoppingCartProdCustomMapper.updateCartAmout(cartProdCustom);
        return SUCCESS();
    }


    /**
     * 处理销售量
     *
     * @param list 产品列表
     * @return
     */
    private List<ProductCustom> handlerSalesNum(List<ProductCustom> list) {
        Gson gson = new Gson();
        if (null != list && 0 != list.size()) {
            for (int i = 0; i < list.size(); i++) {
                int prodId = list.get(i).getProdDetailId();
                String json = (String) redisTemplate.get("productId" + prodId);
                //redis中不存在，则代表该产品首次查询，放入redis中
                if (StringUtils.isBlank(json))
                    redisTemplate.set("productId" + prodId, gson.toJson(list.get(i)));
                else {   //redis中存在，就使用redis中的销售量
                    Type type = new TypeToken<ProductCustom>() {
                    }.getType();
                    ProductCustom prod = gson.fromJson(json.toString(), type);
                    list.set(i, prod);
                }

            }
        }
        return list;
    }

    @Override
    protected BaseMapper<ProductCustom, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }


    private ProductDetail getProductDetail(ProductCustom prods) {
        ProductDetail product = new ProductDetail();
        if (null == prods)
            return product;
        if (!StringUtils.isBlank(prods.getProductId().toString()))
            product.setProductId((Integer) prods.getProductId());
        if (!StringUtils.isBlank(prods.getProductName()))
            product.setProductName(prods.getProductName());
        if (!StringUtils.isBlank(prods.getCategoryId() + ""))
            product.setCategoryId(prods.getCategoryId());
        if (!StringUtils.isBlank(prods.getProductNum() + ""))
            product.setSaleNum(prods.getProductNum());
        if (!StringUtils.isBlank(prods.getKeyword()))
            product.setKeyword(prods.getKeyword());
        if (!StringUtils.isBlank(prods.getProductPrice() + ""))
            product.setProductPrice(prods.getProductPrice());
        if (!StringUtils.isBlank(prods.getShortDesc()))
            product.setShortDesc(prods.getShortDesc());
        if (!StringUtils.isBlank(prods.getStaticPage()))
            product.setStaticPage(prods.getStaticPage());
        if (!StringUtils.isBlank(prods.getStatus()))
            product.setStatus(prods.getStatus());
        if (!StringUtils.isBlank(prods.getCreateTime() + ""))
            product.setCreateTime(prods.getCreateTime());
        if (!StringUtils.isBlank(prods.getOnSaleTime() + ""))
            product.setOnSaleTime(prods.getOnSaleTime());
        if (!StringUtils.isBlank(prods.getOffSaleTime() + ""))
            product.setOffSaleTime(prods.getOffSaleTime());
        if (!StringUtils.isBlank(prods.getThumbnail()))
            product.setThumbnail(prods.getThumbnail());
        if (!StringUtils.isBlank(prods.getProdDetailId() + ""))
            product.setProdDetailId(prods.getProdDetailId());
        if (!StringUtils.isBlank(prods.getDetails()))
            product.setDetails(prods.getDetails());
        if (!StringUtils.isBlank(prods.getPhotos()))
            product.setPhotos(prods.getPhotos());
        if (!StringUtils.isBlank(prods.getProductNum() + ""))
            product.setProductNum(prods.getProductNum());
        return product;
    }

}
