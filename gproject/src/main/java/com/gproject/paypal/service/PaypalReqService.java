package com.gproject.paypal.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.order.mapper.OrderCustomMapper;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.paypal.facade.PayPalFacade;
import com.gproject.paypal.facade.PaypalReqFacade;
import com.gproject.paypal.pojo.PayPalVo;
import com.gproject.util.message.ResponseType;
import com.rabbitmq.tools.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;

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

    @Override
    public Object setExpressCheckOut(OrderQueryVo vo) {
        PayPalVo payPalVo=new PayPalVo();
        try{
            //根据订单id获取订单详情
            OrderQueryVo orderQueryVo=new OrderQueryVo();
            OrderDetailAll orderDetailAll=orderCustomMapper.selectOrderDetailAll(orderQueryVo);
            SetExpressCheckoutResponseType setExpressCheckoutResponseType=payPalFacade.setExpressCheckout(orderDetailAll);
            String token=setExpressCheckoutResponseType.getToken();
            payPalVo.setToken(token);
            payPalVo.setUrl(LOGIN_URL+token);
            log.info("[PaypalReqService] setExpressCheckOut :success"+payPalVo.toString());
        }catch (Exception e){
            log.info("[PaypalReqService] setExpressCheckOut :fail");
            return FAIL(ResponseType.SYSTEM_ERROR,"setExpressCheckOut :fail");
        }
        return SUCCESS(payPalVo);
    }

    @Override
    public PayPalVo doExpressCheckOut(String token) {
        return null;
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
