package com.gproject.paypal.controller;

import com.gproject.order.facade.OrderFacade;
import com.gproject.order.pojo.vo.OrderDetailAll;
import com.gproject.order.pojo.vo.OrderInsertVo;
import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.orderdetail.pojo.OrderDetailVo;
import com.gproject.paypal.facade.PaypalReqFacade;
import com.gproject.util.message.RequestMessage;
import com.gproject.util.message.ResponseMessage;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/10.
 */
@Controller
@RequestMapping("paypal")
public class PayPalController {

    @Autowired
    private PaypalReqFacade paypalReqFacade;


    @ResponseBody
    @RequestMapping(value = "setExpressCheckOut", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object setExpressCheckOut(@RequestBody RequestMessage<OrderQueryVo> vo) {
        try {
            OrderQueryVo example=vo.getRequestContext();
            return paypalReqFacade.setExpressCheckOut(example);
        }catch (Exception e){
            return new ResponseMessage(ResponseType.SYSTEM_ERROR,"exception error",null,false);
        }

    }

    @ResponseBody
    @RequestMapping(value = "doExpressCheckOut")
    @ResponseStatus(HttpStatus.OK)
    public Object doExpressCheckOut(HttpServletRequest request, HttpServletResponse response) {
        String token=request.getParameter("token");
        System.out.println(token);
        Object object=paypalReqFacade.doExpressCheckOut(token);
        return object;
    }


}
