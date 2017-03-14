package com.gproject.category.controller;

import com.gproject.category.pojo.Category;
import com.gproject.category.pojo.vo.CategoryCondition;
import com.gproject.category.pojo.vo.CategoryExample;
import com.gproject.category.service.CategoryService;
import com.gproject.user.pojo.User;
import com.gproject.user.service.UserService;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * CategoryController.java
 * describe: 分类控制层
 * Author hezishan
 * Date 2017/2/23 10:25
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "queryHotCateIndex", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryHotCateIndex(@RequestBody RequestMessage<CategoryExample> context) {
        CategoryExample example=context.getRequestContext();
        return categoryService.queryHotCateIndex(example.getCondition());
    }

    @ResponseBody
    @RequestMapping(value = "querySecondAndThirdCat", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object querySecondAndThirdCat(@RequestBody RequestMessage<CategoryCondition> context) {
        CategoryCondition example=context.getRequestContext();
        return categoryService.querySecondAndThirdCat(example);
    }

}
