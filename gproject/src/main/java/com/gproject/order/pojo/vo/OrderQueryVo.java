package com.gproject.order.pojo.vo;

/**
 * Created by Administrator on 2017/3/15.
 */
public class OrderQueryVo {

    private Integer userId;
    private Integer orderId;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
