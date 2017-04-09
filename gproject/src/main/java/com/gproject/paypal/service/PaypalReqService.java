package com.gproject.paypal.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.order.mapper.OrderCustomMapper;
import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.order.pojo.vo.OrderState;
import com.gproject.paypal.facade.PayPalFacade;
import com.gproject.paypal.facade.PaypalReqFacade;
import com.gproject.paypal.pojo.PayPalVo;
import com.gproject.runningaccount.mapper.RunningAccountCustomMapper;
import com.gproject.runningaccount.pojo.RunningAccountCustom;
import com.gproject.runningaccount.pojo.vo.PaymentMethod;
import com.gproject.runningaccount.pojo.vo.RunningAccountType;
import com.gproject.util.message.ResponseType;
import com.rabbitmq.tools.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class PaypalReqService extends BaseService<PayPalVo,Integer>  implements PaypalReqFacade {

    private static final Logger log = LoggerFactory.getLogger(PayPalService.class);
    @Autowired
    private PayPalFacade payPalFacade;
    @Value("${paypal.login.url}")
    private String LOGIN_URL;
    @Autowired
    private OrderCustomMapper orderCustomMapper;
    @Autowired
    private RunningAccountCustomMapper runningAccountCustomMapper;


    public Object setExpressCheckOut(OrderQueryVo vo) {
        PayPalVo payPalVo=new PayPalVo();
        try{
            //根据订单id获取订单详情
            OrderDetailAll orderDetailAll=orderCustomMapper.selectOrderDetailAll(vo);
            SetExpressCheckoutResponseType setExpressCheckoutResponseType=payPalFacade.setExpressCheckout(orderDetailAll);
            String token=setExpressCheckoutResponseType.getToken();
            payPalVo.setToken(token);
            payPalVo.setUrl(LOGIN_URL+token);
            log.info("[PaypalReqService] setExpressCheckOut :success"+payPalVo.toString());
        }catch (Exception e){
            log.info("[PaypalReqService] setExpressCheckOut :fail");
            e.printStackTrace();
            return FAIL(ResponseType.SYSTEM_ERROR,"setExpressCheckOut :fail");
        }
        return SUCCESS(payPalVo);
    }


    public Object doExpressCheckOut(String token, OrderCustom orderCustom) {
        PayPalVo payPalVo=new PayPalVo();
        try{
            String transactionId=payPalFacade.doCheckOutExpress(token);
            payPalVo.setTransactionId(transactionId);
            //支付-插入runningaccount数据库，修改order表支付方式
            RunningAccountCustom custom=new RunningAccountCustom();
            custom.setUserId(orderCustom.getUserId());
            custom.setAmount(orderCustom.getTotalFee());
            custom.setTransactionId(transactionId);
            custom.setType(RunningAccountType.PAYMENT);
            custom.setEntityId(orderCustom.getOrderId());
            orderCustom.setPaymentMethod(PaymentMethod.PAYMENT_ON_PAYPAL);
            orderCustom.setState(OrderState.COMPLETE);
            runningAccountCustomMapper.insertRunningAccount(custom);
            orderCustomMapper.updateByPrimaryKeySelective(orderCustom);
            log.info("[PaypalReqService] doExpressCheckOut :success"+payPalVo.toString());
        }catch (Exception e){
            log.info("[PaypalReqService] doExpressCheckOut :fail");
            e.printStackTrace();
            return FAIL(ResponseType.SYSTEM_ERROR,"doExpressCheckOut :fail");
        }
        return SUCCESS(payPalVo);
    }

    @Override
    protected BaseMapper<PayPalVo, Integer> getMapper() {
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


}
