package com.gproject.popular.controller;

import com.gproject.popular.facade.PopularFacade;
import com.gproject.popular.pojo.vo.PopularVo;
import com.gproject.popular.service.PopularService;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/3.
 */
@Controller
@RequestMapping("popular")
public class PopularController {

    @Autowired
    private PopularFacade popularFacade;

    @ResponseBody
    @RequestMapping(value = "queryTopFiveSearch",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public  Object queryTopFiveSearch(@RequestBody  RequestMessage<PopularVo> context){
        PopularVo vo=context.getRequestContext();
        return popularFacade.queryPopularLimit(vo);
    }

}
