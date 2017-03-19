package com.gproject.complaint.controller;

import com.gproject.complaint.facade.ComplaintFacade;
import com.gproject.complaint.pojo.ComplaintCustom;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
@Controller
@RequestMapping("complaint")
public class ComplaintController {

    @Autowired
    private ComplaintFacade complaintFacade;

    @ResponseBody
    @RequestMapping(value = "insertComplaint",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object insertComplaint(@RequestBody RequestMessage<ComplaintCustom> context){
        ComplaintCustom vo=context.getRequestContext();
        Object obj=complaintFacade.insertComplaint(vo);
        return obj;
    }

}
