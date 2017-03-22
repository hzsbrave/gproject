package com.gproject.complaint.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.complaint.facade.ComplaintFacade;
import com.gproject.complaint.mapper.ComplaintCustomMapper;
import com.gproject.complaint.pojo.ComplaintCustom;
import com.gproject.complaint.pojo.vo.ComplaintResponse;
import com.gproject.runningaccount.mapper.RunningAccountCustomMapper;
import com.gproject.runningaccount.pojo.RunningAccountCustom;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
@Service
public class ComplaintService extends BaseService<ComplaintCustom, Integer> implements ComplaintFacade {

    @Autowired
    private ComplaintCustomMapper complaintCustomMapper;

    @Autowired
    private RunningAccountCustomMapper accountCustomMapper;

    @Override
    protected BaseMapper<ComplaintCustom, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }

    public Object insertComplaint(ComplaintCustom custom) {
        if (null == custom)
            return FAIL(ResponseType.PARAMETER_NULL, "插入参数为空");
        try {
            //获取订单对于的流水账id
            RunningAccountCustom accountCustom=accountCustomMapper.selectByOrderId(custom.getOrderId());
            custom.setRunningAccountId(accountCustom.getRunningAccountId());
            custom.setCreateTime(new Date());
            complaintCustomMapper.insertComplaint(custom);
        } catch (Exception e) {
            return FAIL(ResponseType.SYSTEM_ERROR, "插入失败");
        }
        return SUCCESS();
    }

    public Object queryComplaintResponse(Integer userId) {
        if (null == userId)
            return FAIL(ResponseType.PARAMETER_NULL, "用户id为空");
        List<ComplaintResponse> list=complaintCustomMapper.queryComplaintResponse(userId);
        return SUCCESS(list);
    }
}
