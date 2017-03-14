package com.gproject.shoppingcartprods.controller;

import com.gproject.shoppingcart.pojo.vo.ShoppingCartQueryVo;
import com.gproject.shoppingcartprods.facade.ShoppingCartProdFacade;
import com.gproject.shoppingcartprods.mapper.ShoppingCartProdCustomMapper;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;
import com.gproject.shoppingcartprods.service.ShoppingCartProdService;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/8.
 */
@Controller
@RequestMapping("shoppingCartProd")
public class ShoppingCartProdController {

    @Autowired
    private ShoppingCartProdFacade prodFacade;

    @ResponseBody
    @RequestMapping(value = "deleteShoppingProdByCartIdAndProdId",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object deleteShoppingProdByCartIdAndProdId(@RequestBody RequestMessage<ShoppingCartProd> context){
        ShoppingCartProd vo=context.getRequestContext();
        return prodFacade.deleteShoppingProdByCartIdAndProdId(vo);
    }

}
