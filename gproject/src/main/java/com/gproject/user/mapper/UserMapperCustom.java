package com.gproject.user.mapper;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.user.pojo.User;
import org.mybatis.spring.annotation.MapperScan;

/**
 * modify by hezishan
 * <p/>
 * Description:
 * 用户mapper拓展类
 * <p/>
 * time:2017/2/10
 */
@MapperScan
public interface UserMapperCustom extends BaseMapper<User, Integer> {

    /**
     * 根据账号查找用户
     * @param user
     * @return
     */
     User queryByAccount(User user);

     int insertUser(User user);

     int updatePasswordByEmail(User user);

}
