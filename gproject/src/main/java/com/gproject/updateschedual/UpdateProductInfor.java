package com.gproject.updateschedual;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gproject.product.mapper.ProductCustomMapper;
import com.gproject.product.pojo.Product;
import com.gproject.productdetail.mapper.ProductDetailCustomMapper;
import com.gproject.runningaccount.mapper.RunningAccountCustomMapper;
import com.gproject.runningaccount.pojo.RunningAccountCustom;
import com.gproject.solr.pojo.vo.ProductCustom;
import com.gproject.solr.pojo.vo.ProductDetail;
import com.gproject.util.redis.core.RedisTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */
@Component
public class UpdateProductInfor {

    @Autowired
    private RunningAccountCustomMapper runningAccountCustomMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductCustomMapper productCustomMapper;
    @Autowired
    private ProductDetailCustomMapper detailCustomMapper;

    public void updateProduct(){
        //获取前一天下单的商品数量
        List<Integer> list=runningAccountCustomMapper.selectYesterdayProductId();
        List<Product> products=new ArrayList<Product>();
        List<ProductDetail> productDetails=new ArrayList<ProductDetail>();
        if(list!=null&&list.size()!=0){
            Gson gson=new Gson();
            //从缓存中读取已下单商品
            for (Integer productId:list){
                String json = (String) redisTemplate.get("productId" + productId);
                if (!StringUtils.isBlank(json)) {
                    Type type = new TypeToken<ProductCustom>() {
                    }.getType();
                    ProductCustom custom = gson.fromJson(json.toString(), type);
                    Product product=getProductCustomToProduct(custom);
                    ProductDetail product1=getProductCustomToProductDetail(custom);
                    products.add(product);
                    productDetails.add(product1);
                }
            }
        }
        //批量跟新到数据库
        try{
            if(products!=null && products.size()!=0)
            productCustomMapper.updateProductSaleNumByProductIdBatch(products);
            detailCustomMapper.updateProductNumByProductIdBatch(productDetails);
            //将数据库数据导入solr
        }catch (Exception e){
            throw  new RuntimeException("批量更新失败");
        }

    }

    /**
     * 将产品拓展类转换成产品类
     * @param custom
     * @return
     */
    private Product getProductCustomToProduct(ProductCustom custom){
        Product product=new Product();
        if(custom!=null){
            product.setProductId(custom.getProductId());
            product.setSaleNum(custom.getSaleNum());
        }
        return product;
    }

    /**
     * 将产品拓展类转换成产品详情类
     * @param custom
     * @return
     */
    private ProductDetail getProductCustomToProductDetail(ProductCustom custom){
        ProductDetail product=new ProductDetail();
        if(custom!=null){
            product.setProductId(custom.getProductId());
            product.setProductNum(custom.getProductNum());
        }
        return product;
    }


}
