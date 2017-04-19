package com.gproject.email.service;

import com.gproject.email.facade.EmailFacade;
import com.gproject.email.util.SendEmailUtil;
import com.gproject.email.util.TemplateConfig;
import com.gproject.email.util.VerCodeuUtil;
import com.gproject.util.redis.core.RedisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * modify by hezishan
 * <p/>
 * Description:发送邮件工具类
 * <p/>
 * time:2017/2/13
 */
@Service
public class EmailService implements EmailFacade {

    private static Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Object sendEmail(String email, int type) throws Exception {
        String content = "";
        String vercode = VerCodeuUtil.getVerificationCode();
        Map<String, String> root = new HashMap<String, String>();
        root.put("vercode", vercode);
        // type=1 ---注册  2----忘记密码
        if (type == 1) {
            content = TemplateConfig.getTemplate("textRegister", root);
        } else {
            content = TemplateConfig.getTemplate("textForpwd", root);
        }
        boolean bool = SendEmailUtil.SendEmail(email,content);
        // 验证码保存在缓存
        redisTemplate.set(email.trim()+"vercode", vercode, 600);
        log.info("从缓存中获取的验证码：" + redisTemplate.get(email.trim()+"vercode"));
        return bool;
    }
}
