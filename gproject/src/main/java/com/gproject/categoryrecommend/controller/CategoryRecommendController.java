package com.gproject.categoryrecommend.controller;

import com.gproject.category.pojo.vo.CategoryExample;
import com.gproject.category.service.CategoryService;
import com.gproject.categoryrecommend.facade.CategoryRecommendFacade;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/4/2.
 */
@Controller
@RequestMapping("/categoryRecommend")
public class CategoryRecommendController {


    @Autowired
    private CategoryRecommendFacade categoryFacade;

    @ResponseBody
    @RequestMapping(value = "queryHotCateProds", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryHotCateProds() throws Exception {
        return categoryFacade.queryHotCategoryProduct(new Integer(2));
    }
}
