package com.gproject.category.pojo.vo;

import com.gproject.category.pojo.Category;

import java.util.List;

/**
 * CategoryResponseVo.java
 * describe:  分类结果返回
 * Author hezishan
 * Date 2017/2/24 11:19
 */
public class CategoryResponseVo {

    private int categoryId;
    private String categoryName;
    private int parentId;
    private String index;
    private List<Category> categories;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
