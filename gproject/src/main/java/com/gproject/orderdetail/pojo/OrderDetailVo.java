package com.gproject.orderdetail.pojo;

import com.gproject.complaint.pojo.Complaint;
import com.gproject.product.pojo.Product;

/**
 * Created by Administrator on 2017/3/15.
 */
public class OrderDetailVo extends OrderDetailCustom {

    private Product product;


    private Complaint complaint;

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
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
