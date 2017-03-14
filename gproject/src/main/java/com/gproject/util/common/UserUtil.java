package com.gproject.util.common;

import com.gproject.util.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * UserUtil.java
 * describe: 用户工具类
 * Author hezishan
 * Date 2017/2/17 10:43
 */
@Service
public class UserUtil {
    @Autowired
    private  RedisTemplate redisTemplate;

    /**
     * 验证token
     *
     * @param key  key
     * @param toke token
     * @return
     */
    public  boolean checkToken(String key, String toke) {
        String token = (String) redisTemplate.get(key);
        if (toke.equals(token)) {
            return true;
        }
        return false;
    }

    /**
     * 产生token,并保存到redis中
     * @param key
     */
    public  void generateUserToken(String key) {
        String token = (String) redisTemplate.get(key);
        token = UuidUtils.getUuid();
        redisTemplate.set(key, token);
    }

    /**
     * 判断token是否为空
     * @param key
     * @return
     */
    public  boolean isNullToken(String key){
        System.out.println(redisTemplate);
        String token = (String) redisTemplate.get(key);
        if(StringUtils.isBlank(token)){
            return true;
        }
        return false;
    }


}
