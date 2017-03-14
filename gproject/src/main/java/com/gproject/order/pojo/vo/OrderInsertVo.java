package com.gproject.order.pojo.vo;

import com.gproject.order.pojo.OrderCustom;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class OrderInsertVo extends OrderCustom {

    private Integer orderDetailId;

    List<OrderProduct> prods;


    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public List<OrderProduct> getProds() {
        return prods;
    }

    public void setProds(List<OrderProduct> prods) {
        this.prods = prods;
    }
}
