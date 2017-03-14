package com.gproject.product.controller;

import com.gproject.product.service.ProductService;
import com.gproject.user.pojo.User;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
@RequestMapping("pro")
public class ProductTextController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "queryproduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryproduct() {
        return productService.queryProduct();
    }

}
