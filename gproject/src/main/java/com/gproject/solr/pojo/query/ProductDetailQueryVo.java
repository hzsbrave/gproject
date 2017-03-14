package com.gproject.solr.pojo.query;

/**
 * Created by Administrator on 2017/3/7.
 */
public class ProductDetailQueryVo {

    private int productId;
    private int cartId;
    private int count;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
