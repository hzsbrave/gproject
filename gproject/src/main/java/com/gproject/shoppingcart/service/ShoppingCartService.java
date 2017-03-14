package com.gproject.shoppingcart.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.shoppingcart.facade.ShoppingCartFacade;
import com.gproject.shoppingcart.mapper.ShoppingCartCustomMapper;
import com.gproject.shoppingcart.pojo.ShoppingCart;
import com.gproject.shoppingcart.pojo.ShoppingCartCustom;
import com.gproject.shoppingcart.pojo.vo.ShoppingCartQueryVo;
import com.gproject.shoppingcartprods.mapper.ShoppingCartProdCustomMapper;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProdCustom;
import com.gproject.solr.Util.QueryUtils;
import com.gproject.solr.base.ProductSolrIndexQueryAdapter;
import com.gproject.solr.base.SolrIndexQuery;
import com.gproject.solr.base.SolrServerFactory;
import com.gproject.solr.facade.ProductSolrFacade;
import com.gproject.solr.pojo.vo.ProductCustom;
import com.gproject.util.message.ResponseType;
import com.gproject.util.redis.core.RedisTemplate;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCartService.java
 * describe:
 * Author hezishan
 * Date 2017/3/4 19:23
 */
@Service
public class ShoppingCartService extends BaseService<ShoppingCartCustom, Integer> implements ShoppingCartFacade {


    @Autowired
    private ShoppingCartCustomMapper shoppingMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShoppingCartProdCustomMapper prodCustomMapper;
    @Autowired
    private SolrServerFactory factory;

    @Override
    public Object queryShoppingCartDetail(ShoppingCartQueryVo vo) {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "shopping vo is null");
        ShoppingCartCustom custom = shoppingMapper.queryShoppingCart(vo);
        String usrToken = vo.getToken();
        if (StringUtils.isBlank(usrToken))
            return FAIL(ResponseType.TOKEN_ERROR, "user token is null");
        String getToken = (String) redisTemplate.get(vo.getAccount().toString() + "login");
        if (!usrToken.equals(getToken) || StringUtils.isBlank(getToken))
            return FAIL(ResponseType.TOKEN_ERROR, "user token is error");
        return SUCCESS(custom);
    }

    @Override
    public Object insertShoppingCart(ShoppingCartQueryVo vo) {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "shopping vo is null");
        ShoppingCartCustom custom = shoppingMapper.queryShoppingCartByUserId(vo.getUserId());
        int cartId = 0;
        Gson gson = new Gson();
        ProductCustom product = new ProductCustom();
        //对应的用户的购物车不存在
        if (null == custom) {
            ShoppingCart cartCustom = new ShoppingCart();
            cartCustom.setUserId(vo.getUserId());
            cartCustom.setCartNum(1);
            //插入数据返回自增主键
            shoppingMapper.insertShoppingCart(cartCustom);
            cartId=cartCustom.getCartId();
        } else {
            //购物车存在
            cartId = custom.getCartId();
            ShoppingCartProd pro = new ShoppingCartProd();
            pro.setProductId(vo.getProductId());
            pro.setCartId(cartId);
            ShoppingCartProdCustom cus = prodCustomMapper.queryShoppingCartProdByUserIdAndCartId(pro);
            //加入购物车的商品存在，就将购物数+1
            if (null != cus) {
                int amount = cus.getAmount();
                //判断加入购物车的数量和库存量对比
                String json = (String) redisTemplate.get("productId" + vo.getProductId());
                //缓存不存在，去solr查询
                if (StringUtils.isBlank(json)) {
                    List<ProductCustom> prods = new ArrayList<ProductCustom>();
                    //查询语句
                    SolrQuery query = QueryUtils.buildQueryByProdId(vo.getProductId());
                    //产品查询
                    SolrIndexQuery solrIndexQuery = new ProductSolrIndexQueryAdapter(factory.getProductServer()).query(query);
                    prods = solrIndexQuery.asList(ProductCustom.class);
                    if (null != prods && 0 != prods.size()) {
                        product = prods.get(0);
                    }
                    if ((amount + 1) > product.getProductNum())
                        cus.setAmount(product.getProductNum());
                    else
                        cus.setAmount(amount + 1);
                    //放入缓存
                    redisTemplate.set("productId" + vo.getProductId(), gson.toJson(product));
                } else {
                    //缓存中存在，和缓存的库存量相比
                    Type type = new TypeToken<ProductCustom>() {
                    }.getType();
                    product = gson.fromJson(json.toString(), type);
                    if ((amount + 1) > product.getProductNum()) {
                        cus.setAmount(product.getProductNum());
                        cus.setCartId(cartId);
                        cus.setProductId(vo.getProductId());
                        //更新购物车的购买量
                        prodCustomMapper.updateCartAmout(cus);
                        return FAIL(ResponseType.PARAMETER_ERROR, "no enough product");
                    }
                    cus.setAmount(amount + 1);
                    cus.setCartId(cartId);
                    cus.setProductId(vo.getProductId());
                    //更新购物车的购买量
                    prodCustomMapper.updateCartAmout(cus);
                    return SUCCESS() ;
                }
            }
        }
        //购物车存在，添加的商品不存在
        ShoppingCartProd prod = new ShoppingCartProd();
        prod.setCartId(cartId);
        prod.setProductId(vo.getProductId());
        prod.setAmount(1);
        prodCustomMapper.insertShoppingCartProd(prod);
        return SUCCESS();
    }


    @Override
    protected BaseMapper<ShoppingCartCustom, Integer> getMapper() {
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


}
