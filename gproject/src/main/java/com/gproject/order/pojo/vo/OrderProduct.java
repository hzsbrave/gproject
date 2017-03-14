package com.gproject.order.pojo.vo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/3/13.
 */
public class OrderProduct {

    private Integer productId;

    private String productName;

    private Integer num;

    private BigDecimal productPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
