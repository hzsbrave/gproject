package com.gproject.popular.pojo;

import java.util.Date;

public class Popular {
    private Integer popularId;

    private String popularName;

    private Integer popularNo;

    private Date lastUpdateTime;

    public Integer getPopularId() {
        return popularId;
    }

    public void setPopularId(Integer popularId) {
        this.popularId = popularId;
    }

    public String getPopularName() {
        return popularName;
    }

    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

    public Integer getPopularNo() {
        return popularNo;
    }

    public void setPopularNo(Integer popularNo) {
        this.popularNo = popularNo;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "Popular{" +
                "popularId=" + popularId +
                ", popularName='" + popularName + '\'' +
                ", popularNo=" + popularNo +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}