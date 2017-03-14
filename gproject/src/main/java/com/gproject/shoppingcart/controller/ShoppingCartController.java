package com.gproject.shoppingcart.controller;

import com.gproject.shoppingcart.facade.ShoppingCartFacade;
import com.gproject.shoppingcart.pojo.vo.ShoppingCartQueryVo;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/4.
 */
@Controller
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartFacade shoppingCartFacade;

    @ResponseBody
    @RequestMapping(value = "queryShoppingCartDetail",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryShoppingCartDetail(@RequestBody RequestMessage<ShoppingCartQueryVo> context){
        ShoppingCartQueryVo vo=context.getRequestContext();
        return shoppingCartFacade.queryShoppingCartDetail(vo);
    }

    @ResponseBody
    @RequestMapping(value = "insertShoppingCartAndShoppingProds",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insertShoppingCartAndShoppingProds(@RequestBody RequestMessage<ShoppingCartQueryVo> context){
        ShoppingCartQueryVo vo=context.getRequestContext();
        return shoppingCartFacade.insertShoppingCart(vo);
    }

}
