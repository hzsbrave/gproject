package com.gproject.order.pojo.vo;

import com.gproject.order.pojo.Order;
import com.gproject.orderdetail.pojo.OrderDetail;
import com.gproject.orderdetail.pojo.OrderDetailCustom;
import com.gproject.orderdetail.pojo.OrderDetailVo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class OrderDetailAll extends Order {

    private List<OrderDetailVo> orderDetailVos;

    public List<OrderDetailVo> getOrderDetailVos() {
        return orderDetailVos;
    }

    public void setOrderDetailVos(List<OrderDetailVo> orderDetailVos) {
        this.orderDetailVos = orderDetailVos;
    }
}
