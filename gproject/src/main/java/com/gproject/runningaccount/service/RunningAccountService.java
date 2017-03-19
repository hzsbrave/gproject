package com.gproject.runningaccount.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.order.mapper.OrderCustomMapper;
import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.paypal.facade.PayPalFacade;
import com.gproject.paypal.facade.PaypalReqFacade;
import com.gproject.paypal.pojo.PayPalVo;
import com.gproject.runningaccount.facade.RunningAccountFacade;
import com.gproject.runningaccount.mapper.RunningAccountCustomMapper;
import com.gproject.runningaccount.pojo.RunningAccountCustom;
import com.gproject.runningaccount.pojo.vo.PaymentMethod;
import com.gproject.runningaccount.pojo.vo.RunningAccountType;
import com.gproject.runningaccount.pojo.vo.RunningAccountVo;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class RunningAccountService extends BaseService<RunningAccountCustom,Integer> implements RunningAccountFacade {

    @Autowired
    private RunningAccountCustomMapper runningAccountCustomMapper;
    @Autowired
    private OrderCustomMapper orderCustomMapper;


    @Override
    public Object updateOrInsertRunningAccount(RunningAccountVo vo) {
        if(null==vo)
           return FAIL(ResponseType.PARAMETER_NULL,"custom is null.");
        RunningAccountCustom accountCustom=getRunningAccountCustom(vo);
        OrderCustom orderCustom=new OrderCustom();
        //设置订单id
        orderCustom.setOrderId(vo.getEntityId());

        try {
            //支付方式为paypal,（set-login-pay）--transactionid
            if (vo.getPaymentMethod() == PaymentMethod.PAYMENT_ON_PAYPAL) {
                //数据插入数据库成功，将数据设置到paypal
               // PayPalVo payPalVo=payPalReqFacade.setExpressCheckOut(orderDetailAll);


            } else {
                //支付方式为货到付款 ,runningaccount的transactionid 为000_时间错 order支付方式
                accountCustom.setTransactionId("000_" + System.currentTimeMillis());
                orderCustom.setPaymentMethod(PaymentMethod.PAYMENT_ON_DELIVERY);
            }
            //支付-插入runningaccount数据库，修改order表支付方式
            runningAccountCustomMapper.insertRunningAccount(accountCustom);
            orderCustomMapper.updateByPrimaryKeySelective(orderCustom);


        }catch (Exception e){

        }
        return null;
    }
    @Override
    protected BaseMapper<RunningAccountCustom, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }

    private RunningAccountCustom getRunningAccountCustom(RunningAccountVo vo){
        RunningAccountCustom custom=new RunningAccountCustom();
        custom.setAmount(vo.getAmount());
        custom.setEntityId(vo.getEntityId());
        custom.setType(vo.getType());
        custom.setUserId(vo.getUserId());
        return custom;
    }

}
