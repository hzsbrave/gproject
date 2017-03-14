package com.gproject.category.pojo;

public class Category {
    private Integer categoryId;

    private String categoryName;

    private String categoryNameCn;

    private Integer index;

    private Integer parentId;

    private String thumbnail;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameCn() {
        return categoryNameCn;
    }

    public void setCategoryNameCn(String categoryNameCn) {
        this.categoryNameCn = categoryNameCn;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}