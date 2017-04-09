package com.gproject.user.controller;

import com.gproject.common.facade.UploadFacade;
import com.gproject.user.facade.UserFacade;
import com.gproject.user.pojo.User;
import com.gproject.user.pojo.vo.UserExample;
import com.gproject.user.pojo.vo.UserTokenVo;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * modify by hezishan
 * <p/>
 * Description:
 * <p/>
 * time:2017/2/10
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserFacade userService;
    @Autowired
    private UploadFacade uploadFacade;

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object register(@RequestBody RequestMessage<User> context) throws Exception{
        return userService.insertUser(context.getRequestContext());
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object login(@RequestBody RequestMessage<User> context) {
        User example=context.getRequestContext();
        return userService.queryUserByName(example);
    }


    @ResponseBody
    @RequestMapping(value = "sendmail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object sendmail(@RequestBody RequestMessage<UserExample> context)  throws  Exception {
        UserExample example=context.getRequestContext();
        return userService.queryAccountToEmail(example.getEmail(),example.getType());
    }

    @ResponseBody
    @RequestMapping(value = "checkVerificationCode", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object checkVerifitionCode(@RequestBody RequestMessage<UserExample> context)  throws  Exception {
        UserExample example=context.getRequestContext();
        return userService.queryVercodeByEmail(example.getEmail(),example.getVercode());
    }

    @ResponseBody
    @RequestMapping(value = "updatePasswordByEmail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object updatePasswordByEmail(@RequestBody RequestMessage<UserExample> context)  throws  Exception {
        UserExample example=context.getRequestContext();
        return userService.updatePasswordByEmail(example.getEmail(),example.getPassword());
    }

    @ResponseBody
    @RequestMapping(value = "checkUserToken", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object checkUserToken(@RequestBody RequestMessage<UserTokenVo> context)  throws  Exception {
        UserTokenVo example=context.getRequestContext();
        return userService.checkUserToken(example.getAccount(),example.getUserToken());
    }

    @ResponseBody
    @RequestMapping(value = "updateUserInfoImage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object updateUserInfoImage(User user, MultipartFile file)  throws  Exception {
        return userService.updateUserInfoImage(user,file);
    }

    @ResponseBody
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object updateUserInfo(@RequestBody RequestMessage<User>  user)  throws  Exception {
        User example=user.getRequestContext();
        return userService.updateUserInfo(example);
    }

    @ResponseBody
    @RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object queryUserInfo(@RequestBody RequestMessage<User>  user)  throws  Exception {
        User example=user.getRequestContext();
        return userService.queryUserById(example.getUserId());
    }


}
