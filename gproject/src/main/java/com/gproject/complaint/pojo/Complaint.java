package com.gproject.complaint.pojo;

import java.util.Date;

public class Complaint {
    private Integer complaintId;

    private Integer orderId;

    private Integer runningAccountId;

    private String complaintDesc;

    private Integer state;

    private String followDesc;

    private Date followTime;

    private Date createTime;

    private Integer orderDetailId;

    private String image;

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getRunningAccountId() {
        return runningAccountId;
    }

    public void setRunningAccountId(Integer runningAccountId) {
        this.runningAccountId = runningAccountId;
    }

    public String getComplaintDesc() {
        return complaintDesc;
    }

    public void setComplaintDesc(String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFollowDesc() {
        return followDesc;
    }

    public void setFollowDesc(String followDesc) {
        this.followDesc = followDesc;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}