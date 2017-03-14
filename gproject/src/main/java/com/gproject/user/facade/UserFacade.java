package com.gproject.user.facade;


import com.gproject.user.pojo.User;

/**
 * modify by hezishan
 * <p/>
 * Description:
 * 用户逻辑层接口类
 * <p/>
 * time:2017/2/10
 */
public interface UserFacade {

    /**
     * 添加一条用户记录
     *
     * @param user 用户信息
     * @return
     */
    public Object insertUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param user
     * @return
     */
    public Object queryUserByName(User user);

    /**
     * 根据邮箱发送验证码
     * @param email 邮箱
     * @param type 类型 1--注册 2--找回密码
     * @return
     */
    public Object queryAccountToEmail(String email,int type) throws Exception;

    /**
     * 根据邮件校验验证码
     * @param email
     * @param vercode
     * @return
     */
    public Object queryVercodeByEmail(String email,String vercode);

    /**
     * 根据邮件校验验证码
     * @param email
     * @param vercode
     * @return
     */
    public Object updatePasswordByEmail(String email,String vercode);

    /**
     * 检查用户token值
     * @param account
     * @param usrToken
     * @return
     */
    public Object checkUserToken(String account,String usrToken);

}
