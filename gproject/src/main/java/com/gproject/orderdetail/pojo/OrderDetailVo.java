package com.gproject.orderdetail.pojo;

import com.gproject.product.pojo.Product;

/**
 * Created by Administrator on 2017/3/15.
 */
public class OrderDetailVo extends OrderDetailCustom {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
