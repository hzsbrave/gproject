package com.gproject.solr.pojo.vo;


import com.gproject.product.pojo.Product;

import java.io.Serializable;

/**
 * ProductCustom.java
 * describe: 产品拓展类
 * Author hezishan
 * Date 2017/2/28 9:19
 */
public class ProductDetail extends ProductCustom {
   private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
