package com.gproject.paypal.facade;

import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.paypal.pojo.PayPalVo;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface PaypalReqFacade {

    public Object setExpressCheckOut(OrderQueryVo vo);

    public Object doExpressCheckOut(String token, OrderCustom orderCustom);

}
