package com.gproject.runningaccount.controller;

import com.gproject.order.pojo.vo.OrderQueryVo;
import com.gproject.runningaccount.mapper.RunningAccountCustomMapper;
import com.gproject.runningaccount.pojo.RunningAccountCustom;
import com.gproject.util.message.RequestMessage;
import com.gproject.util.message.ResponseMessage;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("runningAccount")
public class RunningAccountController {

    @Autowired
    private RunningAccountCustomMapper runningAccountCustomMapper;

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insert(@RequestBody RequestMessage<RunningAccountCustom> vo) {
        try {
            RunningAccountCustom example=vo.getRequestContext();
            runningAccountCustomMapper.insertRunningAccount(example);
            return null;
        }catch (Exception e){
            return new ResponseMessage(ResponseType.SYSTEM_ERROR,"exception error",null,false);
        }
    }


}
