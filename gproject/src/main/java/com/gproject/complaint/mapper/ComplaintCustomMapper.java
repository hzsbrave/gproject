package com.gproject.complaint.mapper;

import com.gproject.complaint.pojo.ComplaintCustom;
import com.gproject.complaint.pojo.vo.ComplaintResponse;

import java.util.List;

public interface ComplaintCustomMapper {

   public void  insertComplaint(ComplaintCustom custom);

   public int queryComplaintCondition(ComplaintCustom custom);

   public List<ComplaintResponse> queryComplaintResponse(Integer userId);


}