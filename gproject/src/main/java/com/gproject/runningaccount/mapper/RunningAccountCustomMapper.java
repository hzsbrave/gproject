package com.gproject.runningaccount.mapper;

import com.gproject.runningaccount.pojo.RunningAccountCustom;

public interface RunningAccountCustomMapper {

    public void insertRunningAccount(RunningAccountCustom custom);

    public RunningAccountCustom selectByOrderId(Integer orderId);

}