package com.gproject.order.pojo;

import java.util.Date;

public class Order {
    //订单id,自增
    private Integer orderId;
    //订单对应的用户Id
    private Integer userId;
    //收货地址
    private Integer addressId;
    //订单号，随机生成
    private String orderNumber;
    //订单产品数量
    private Integer prodNum;
    //订单总价：商品总价格+运费
    private Long totalFee;
   //运费
    private Long expressFee;
   //创建日期
    private Date createTime;
    /**
     * 订单状态：
     *1:未付款、
     * 2:待发货、
     * 3:待签收、
     * 7:完成、
     * 9:申诉、
     * 11:退款成功
     */
    private Integer state;

    private String paymentMethod;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getProdNum() {
        return prodNum;
    }

    public void setProdNum(Integer prodNum) {
        this.prodNum = prodNum;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Long expressFee) {
        this.expressFee = expressFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}