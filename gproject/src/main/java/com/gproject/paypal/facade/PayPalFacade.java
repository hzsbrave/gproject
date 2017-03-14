package com.gproject.paypal.facade;

import com.gproject.order.pojo.vo.OrderInsertVo;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface PayPalFacade {

    /**
     *  1.快速支付
     * @return
     */
    public Object setExpressCheckout(OrderInsertVo vo) throws Exception;


}
