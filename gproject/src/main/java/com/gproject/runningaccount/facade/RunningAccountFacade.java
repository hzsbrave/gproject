package com.gproject.runningaccount.facade;

import com.gproject.runningaccount.pojo.RunningAccountCustom;
import com.gproject.runningaccount.pojo.vo.RunningAccountVo;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface RunningAccountFacade {

    public Object updateOrInsertRunningAccount(RunningAccountVo custom);

}
