package com.gproject.orderdetail.mapper;

import com.gproject.orderdetail.pojo.OrderDetail;
import com.gproject.orderdetail.pojo.OrderDetailCustom;

import java.util.List;

public interface OrderDetailCustomMapper {

    public void insertOrderDetail(OrderDetailCustom custom);

    /**
     * 批量更新订单信息
     * @param list
     */
    public void insertOrderDetailBatch(List<OrderDetailCustom> list);

    /**
     * 根据订单id查询产品列表
     * @param orderId
     * @return
     */
    public List<OrderDetail> selectProductIdsByOrderId(Integer orderId);

}