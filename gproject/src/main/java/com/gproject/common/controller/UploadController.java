package com.gproject.common.controller;

import com.gproject.common.facade.UploadFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by Administrator on 2017/3/19 0019.
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadFacade uploadFacade;


    @ResponseBody
    @RequestMapping(value = "uploadPicture", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object uploadPicture(MultipartFile file) {
          return uploadFacade.uploadPicture(file);
    }

}
