package com.gproject.runningaccount.mapper;

import com.gproject.runningaccount.pojo.RunningAccountCustom;

import java.util.List;

public interface RunningAccountCustomMapper {

    public void insertRunningAccount(RunningAccountCustom custom);

    public RunningAccountCustom selectByOrderId(Integer orderId);

    /**
     * 查询上一天的下单商品
     * @return
     */
    public List<Integer> selectYesterdayProductId();

}