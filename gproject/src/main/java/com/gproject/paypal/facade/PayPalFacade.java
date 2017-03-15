package com.gproject.paypal.facade;

import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderInsertVo;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface PayPalFacade {

    /**
     *  1.快速支付
     * @return
     */
    public SetExpressCheckoutResponseType setExpressCheckout(OrderDetailAll vo) throws Exception;

    /**
     * 确认支付
     * getExpressCheckout(token)
     * doCheckOutExpress(token,amt,payid,paymentaction)
     * @param token
     * @return transactonId
     * @throws Exception
     */
    public String doCheckOutExpress(String token) throws Exception;

}
