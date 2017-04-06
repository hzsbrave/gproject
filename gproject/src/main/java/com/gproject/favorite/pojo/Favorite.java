package com.gproject.favorite.pojo;

import java.util.Date;

public class Favorite {
    private Integer favorId;

    private Integer userId;

    private Integer productId;

    private Date createTime;

    private Date lastUpdateTime;

    public Integer getFavorId() {
        return favorId;
    }

    public void setFavorId(Integer favorId) {
        this.favorId = favorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}