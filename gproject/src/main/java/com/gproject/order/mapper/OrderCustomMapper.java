package com.gproject.order.mapper;

import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderDetailEx;
import com.gproject.order.pojo.vo.OrderQueryVo;

import java.util.List;

public interface OrderCustomMapper {

    public void insertOrder(OrderCustom custom);

    public void updateByPrimaryKeySelective(OrderCustom custom);

    public OrderCustom selectByPrimaryKey(Integer orderId);

    public OrderDetailAll selectOrderDetailAll(OrderQueryVo vo);

    public List<OrderDetailAll> queryOrderForUser(OrderQueryVo vo);

    public List<OrderDetailAll> queryOrderCustomService(OrderQueryVo vo);

    public OrderDetailEx queryOrderDetailByOrderId(Integer orderId);

}