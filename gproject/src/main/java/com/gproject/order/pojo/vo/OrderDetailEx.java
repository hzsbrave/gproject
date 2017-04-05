package com.gproject.order.pojo.vo;

import com.gproject.address.pojo.Address;
import com.gproject.order.pojo.Order;
import com.gproject.orderdetail.pojo.OrderDetailVo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public class OrderDetailEx extends Order {

    private Address address;

    private List<OrderDetailVo> orderDetailVos;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderDetailVo> getOrderDetailVos() {
        return orderDetailVos;
    }

    public void setOrderDetailVos(List<OrderDetailVo> orderDetailVos) {
        this.orderDetailVos = orderDetailVos;
    }
}
