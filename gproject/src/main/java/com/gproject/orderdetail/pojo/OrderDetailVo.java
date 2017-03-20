package com.gproject.orderdetail.pojo;

import com.gproject.product.pojo.Product;

/**
 * Created by Administrator on 2017/3/15.
 */
public class OrderDetailVo extends OrderDetailCustom {

    private Product product;


    private int complaint;

    public int isComplaint() {
        return complaint;
    }

    public void setComplaint(int complaint) {
        this.complaint = complaint;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetailVo{" +
                "product=" + product +
                ", complaint=" + complaint +
                '}';
    }
}
