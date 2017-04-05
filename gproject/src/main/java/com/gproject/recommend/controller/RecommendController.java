package com.gproject.recommend.controller;

import com.gproject.categoryrecommend.facade.CategoryRecommendFacade;
import com.gproject.recommend.facade.RecommendFacade;
import com.gproject.recommend.pojo.RecommendCustom;
import com.gproject.solr.pojo.query.SeachParam;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/4/5.
 */
@Controller
@RequestMapping("productRecommend")
public class RecommendController {

    @Autowired
    private RecommendFacade productRecommendFacade;

    @ResponseBody
    @RequestMapping(value = "insertRecommenProduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insertRecommenProduct(@RequestBody RequestMessage<RecommendCustom> context) throws Exception {
        RecommendCustom param=context.getRequestContext();
        return productRecommendFacade.insertRecommenProduct(param);
    }
}
