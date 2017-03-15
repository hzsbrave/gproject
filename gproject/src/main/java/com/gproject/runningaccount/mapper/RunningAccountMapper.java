package com.gproject.runningaccount.mapper;

import com.gproject.runningaccount.pojo.RunningAccount;

public interface RunningAccountMapper {
    int deleteByPrimaryKey(Integer runningAccountId);

    int insert(RunningAccount record);

    int insertSelective(RunningAccount record);

    RunningAccount selectByPrimaryKey(Integer runningAccountId);

    int updateByPrimaryKeySelective(RunningAccount record);

    int updateByPrimaryKey(RunningAccount record);
}