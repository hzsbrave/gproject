package com.gproject.user.service;


import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.common.facade.UploadFacade;
import com.gproject.email.facade.EmailFacade;
import com.gproject.user.facade.UserFacade;
import com.gproject.user.mapper.UserMapperCustom;
import com.gproject.user.pojo.User;
import com.gproject.user.pojo.vo.UserExample;
import com.gproject.util.common.UserUtil;
import com.gproject.util.message.ResponseMessage;
import com.gproject.util.message.ResponseType;
import com.gproject.util.redis.core.RedisTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * modify by hezishan
 * <p/>
 * Description:用户逻辑层
 * <p/>
 * time:2017/2/10
 */
@Service
public class UserService extends BaseService<User, Integer> implements UserFacade {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapperCustom userMapperCustom;
    @Autowired
    private EmailFacade emailFacade;
    @Autowired
    private UserUtil userUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UploadFacade uploadFacade;


    @Override
    public Object insertUser(User user) {
        if (null == user) {
            logger.info("the user is null.");
            return FAIL(ResponseType.PARAMETER_NULL, "the user is null");
        }
        String account = user.getAccount();
        if (StringUtils.isBlank(account)) {
            logger.info("the account is null.");
            return FAIL(ResponseType.PARAMETER_NULL, "the account is null");
        }
        if (userMapperCustom.queryByAccount(user) != null) {
            logger.info("the account is exist.");
            return FAIL(ResponseType.PARAMETER_EXIST, "the account is exist.");
        }
        userMapperCustom.insertUser(user);
        logger.info("insert user successfully!");
        return SUCCESS(user);
    }

    @Override
    public Object queryUserByName(User user) {
        if (null == user) {
            return FAIL(ResponseType.PARAMETER_NULL, "the user is null");
        }
        String account = user.getAccount();
        String password = user.getPassword();
        //参数为空
        if (StringUtils.isBlank(account)) {
            return FAIL(ResponseType.PARAMETER_NULL, "the account is null");
        }
        if (StringUtils.isBlank(password)) {
            return FAIL(ResponseType.PARAMETER_NULL, "the password is null");
        }
        User queryuser = userMapperCustom.queryByAccount(user);
        //账号不存在
        if (queryuser == null) {
            logger.info("the account is not exist.");
            return FAIL(ResponseType.PARAMETER_NOT_EXIST, "the account is not exist");
        }
        //密码不正确
        if (!queryuser.getPassword().equals(password)) {
            logger.info("the password is not error.");
            return FAIL(ResponseType.PARAMETER_ERROR, "the password is not error");
        }
        if (userUtil.isNullToken(account.trim() + "login")) {
            //用户token为空，产生token
            userUtil.generateUserToken(account.trim() + "login");
        }
        String userToken = (String) redisTemplate.get(account.trim() + "login");
        queryuser.setToken(userToken);
        System.out.println(userToken);
        return SUCCESS(queryuser);

    }

    @Override
    public Object queryAccountToEmail(String email, int type) throws Exception {
        User user = new User();
        user.setAccount(email);
        User queryuser = userMapperCustom.queryByAccount(user);
        if (type == 1) {
            //注册发送验证码
            if (null != queryuser) {
                return FAIL(ResponseType.PARAMETER_EXIST, "the account is exist");
            }
            //发送邮件
            emailFacade.sendEmail(email, type);
            return SUCCESS();
        } else {
            //找回密码发送验证码
            if (null == queryuser) {
                return FAIL(ResponseType.PARAMETER_NOT_EXIST, "the account is not exist");
            }
            //发送邮件
            emailFacade.sendEmail(email, type);
            return SUCCESS();
        }

    }

    @Override
    public Object queryVercodeByEmail(String email, String vercode) {
        //获取验证码
        String ver = (String) redisTemplate.get(email.trim() + "vercode");
        if (vercode.equals(ver)) {
            //删除验证码
            redisTemplate.delete(email.trim() + "vercode");
            return SUCCESS();
        }
        return FAIL(ResponseType.PARAMETER_ERROR, "Verification code is error");
    }

    @Override
    public Object updatePasswordByEmail(String email, String password) {
        User user = new User();
        user.setAccount(email);
        User queryuser = userMapperCustom.queryByAccount(user);
        if (null == queryuser)
            return FAIL(ResponseType.PARAMETER_NULL, "the user is null");
        if (queryuser.getPassword().equals(password)) {
            return FAIL(ResponseType.PARAMETER_SAME, "the password is same");
        }
        User usr = new User();
        usr.setAccount(email);
        usr.setPassword(password);
        userMapperCustom.updatePasswordByEmail(usr);
        return SUCCESS();
    }

    @Override
    public Object checkUserToken(String account, String usrToken) {
        String getToken = (String) redisTemplate.get(account + "login");
        if (StringUtils.isBlank(getToken) || !usrToken.equals(getToken)) {
            System.out.println("false");
            return FAIL(ResponseType.PARAMETER_ERROR, "user token is error");
        }
        System.out.println("true");
        return SUCCESS();
    }

    public Object updateUserInfoImage(User example, MultipartFile file) {
        if (null == example)
            return FAIL(ResponseType.PARAMETER_NULL, "update user info is null");
        try {
            if (file != null) {
                ResponseMessage response = (ResponseMessage) uploadFacade.uploadPicture(file);
                if (response.getCode() == 0) {
                    String path = (String) response.getResult();
                    example.setImage(path);
                }
            }
            userMapperCustom.updateByPrimaryKeySelective(example);
        } catch (Exception e) {

            throw new RuntimeException();
        }
        return SUCCESS(example);
    }

    public Object updateUserInfo(User example) {
        if (null == example)
            return FAIL(ResponseType.PARAMETER_NULL, "update user info is null");
        try {
            userMapperCustom.updateByPrimaryKeySelective(example);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return SUCCESS(example);
    }

    public Object queryUserById(Integer userId) {
        if (null == userId)
            return FAIL(ResponseType.PARAMETER_NULL," query user info is null");
        User user=userMapperCustom.selectByPrimaryKey(userId);
        if(user==null)
            return FAIL(ResponseType.PARAMETER_NULL," query user info is null");
        return SUCCESS(user);
    }

    @Override
    protected BaseMapper<User, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }
}
