package com.gproject.orderdetail.mapper;

import com.gproject.orderdetail.pojo.OrderDetail;
import com.gproject.orderdetail.pojo.OrderDetailCustom;

import java.util.List;

public interface OrderDetailCustomMapper {

    public void insertOrderDetail(OrderDetailCustom custom);

    public void insertOrderDetailBatch(List<OrderDetailCustom> list);

}