package com.gproject.order.facade;

import com.gproject.order.pojo.vo.OrderInsertVo;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface OrderFacade {

    public Object insertOrder(OrderInsertVo vo) throws Exception;

}
