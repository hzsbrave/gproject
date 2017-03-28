package com.gproject.orderdetail.pojo;

import java.math.BigDecimal;

public class OrderDetail {
    private Integer orderDetailId;

    private Integer orderId;

    private Integer productId;

    private Integer num;

    private BigDecimal productPrice;

    private BigDecimal sumFee;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public BigDecimal getSumFee() {
        return sumFee;
    }

    public void setSumFee(BigDecimal sumFee) {
        this.sumFee = sumFee;
    }

    @Override
    public String toString() {
        return "OrderDetailEx{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", num=" + num +
                ", productPrice=" + productPrice +
                ", sumFee=" + sumFee +
                '}';
    }
}