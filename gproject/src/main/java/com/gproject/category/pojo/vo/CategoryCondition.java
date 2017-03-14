package com.gproject.category.pojo.vo;

/**
 * CategoryCondition.java
 * describe: 分类情况的查询类
 * Author hezishan
 * Date 2017/2/24 10:15
 */
public class CategoryCondition {

    private int index;
    private int parentId;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
