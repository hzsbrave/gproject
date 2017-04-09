package com.gproject.user.facade;


import com.gproject.user.pojo.User;
import org.springframework.web.multipart.MultipartFile;

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
     * 1.参数不为空判断，判断用户邮箱是否已经存在，若前台提示返回用户已经存在，若不为空，进行下面操作
     * 2.将用户信息保存到数据库，插入到user表，并返回自增主键
     * 3.用用户返回的自增主键和邮箱向融云获取token，若返回200，更新用户信息，将token保存在数据库
     * @param user 用户信息
     * @return
     */
    public Object insertUser(User user) throws Exception;

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


    public Object updateUserInfoImage(User example, MultipartFile file);

    public Object updateUserInfo(User example);

    public Object queryUserById(Integer userId);

}
