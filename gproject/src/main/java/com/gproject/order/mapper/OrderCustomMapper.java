package com.gproject.order.mapper;

import com.gproject.order.pojo.Order;
import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderQueryVo;

public interface OrderCustomMapper {

    public void insertOrder(OrderCustom custom);

    public void updateByPrimaryKeySelective(OrderCustom custom);

    public OrderCustom selectByPrimaryKey(Integer orderId);

    public OrderDetailAll selectOrderDetailAll(OrderQueryVo vo);

}