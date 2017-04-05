package com.gproject.categoryrecommend.pojo;

import java.util.Date;

public class CategoryRecommend {
    private Integer caterecomId;

    private Integer categoryId;

    private Integer count;

    private Date time;

    public Integer getCaterecomId() {
        return caterecomId;
    }

    public void setCaterecomId(Integer caterecomId) {
        this.caterecomId = caterecomId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}