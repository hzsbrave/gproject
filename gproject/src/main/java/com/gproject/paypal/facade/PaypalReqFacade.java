package com.gproject.paypal.facade;

import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderQueryVo;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface PaypalReqFacade {
    /**
     * 设置订单信息到paypal
     * 1.根据订单编号查询订单及订单详情的信息
     * 2.将步骤1中或获取的订单信息设置到paypal中
     * 3.若成功，返回paypal成功的token和paypal登录路径，并在页面跳转paypal登录页面；若失败，则返回失败
     * @param vo
     * @return
     */
    public Object setExpressCheckOut(OrderQueryVo vo);

    /**
     * 进行paypal销售
     * 1.根据token获取保存在paypal中的订单信息，调用paypal getExpress 方法
     * 2.执行paypal销售即付款，调用paypal doExpressCheckOut
     * 3.若步骤2成功，paypal买家账号中扣钱，paypal买家账号中入钱，获取paypal销售成功返回的transactionId,则将订单付款信息保存在流水账running_account表中，并更新订单状态，页面调回setExpressCheckOut时设置的成功跳转的路径
     * 4.若步骤2失败，页面跳转到失败页面
     * @param token
     * @param orderCustom
     * @return
     */
    public Object doExpressCheckOut(String token, OrderCustom orderCustom);

}
