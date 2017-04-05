package com.gproject.categoryrecommend.pojo.vo;

import com.gproject.product.pojo.Product;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
public class HotCategoryResp {

    private Integer categoryId;
    private String categoryName;
    private Object products;

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

    public Object getProducts() {
        return products;
    }

    public void setProducts(Object products) {
        this.products = products;
    }
}
