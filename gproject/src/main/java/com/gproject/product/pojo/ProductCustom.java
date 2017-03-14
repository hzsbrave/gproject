package com.gproject.product.pojo;


import java.io.Serializable;

/**
 * ProductCustom.java
 * describe: 产品拓展类
 * Author hezishan
 * Date 2017/2/28 9:19
 */
public class ProductCustom extends Product implements Serializable {
    private Integer prodDetailId;

    private String photos;

    private String details;

    private Integer productNum;

    public Integer getProdDetailId() {
        return prodDetailId;
    }

    public void setProdDetailId(Integer prodDetailId) {
        this.prodDetailId = prodDetailId;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}
