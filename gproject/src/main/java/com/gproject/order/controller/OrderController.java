package com.gproject.order.controller;

import com.gproject.order.facade.OrderFacade;
import com.gproject.order.pojo.vo.OrderInsertVo;
import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.util.message.RequestMessage;
import com.gproject.util.message.ResponseMessage;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/10.
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @ResponseBody
    @RequestMapping(value = "insertOrder", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insertOrder(@RequestBody RequestMessage<OrderInsertVo> vo) {
       try {
           OrderInsertVo example=vo.getRequestContext();
           return orderFacade.insertOrder(example);
       }catch (Exception e){
           return new ResponseMessage(ResponseType.SYSTEM_ERROR,"exception error",null,false);
       }

    }

    @ResponseBody
    @RequestMapping(value = "queryOrderForUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryOrderForUser(@RequestBody RequestMessage<OrderQueryVo> vo) throws Exception {
            OrderQueryVo example=vo.getRequestContext();
            return orderFacade.queryOrderForUser(example);
    }

    @ResponseBody
    @RequestMapping(value = "queryOrderForUserCustomerService", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryOrderForUserCustomerService(@RequestBody RequestMessage<OrderQueryVo> vo) throws Exception {
        OrderQueryVo example=vo.getRequestContext();
        return orderFacade.queryOrderForUserCustomerService(example);
    }

}
