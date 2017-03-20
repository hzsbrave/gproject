package com.gproject.complaint.mapper;

import com.gproject.complaint.pojo.ComplaintCustom;

public interface ComplaintCustomMapper {

   public void  insertComplaint(ComplaintCustom custom);

   public int queryComplaintCondition(ComplaintCustom custom);

}