package com.gproject.complaint.facade;

import com.gproject.complaint.pojo.ComplaintCustom;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public interface ComplaintFacade {

    Object insertComplaint(ComplaintCustom custom);

    Object queryComplaintResponse(Integer userId);

}
