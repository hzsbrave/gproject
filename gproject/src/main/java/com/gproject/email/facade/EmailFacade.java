package com.gproject.email.facade;

/**
 * modify by hezishan
 * <p/>
 * Description:
 * <p/>
 * time:2017/2/13
 */
public interface EmailFacade {
    /**
     * 发送验证码
     * @param email 邮箱
     * @param type 类型
     * @return
     */
    public Object sendEmail(String email,int type) throws Exception;

}
