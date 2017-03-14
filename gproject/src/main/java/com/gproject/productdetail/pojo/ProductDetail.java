package com.gproject.productdetail.pojo;

public class ProductDetail {
    private Integer prodDetailId;

    private Integer productId;

    private String photos;

    private String details;

    private Integer productNum;

    public Integer getProdDetailId() {
        return prodDetailId;
    }

    public void setProdDetailId(Integer prodDetailId) {
        this.prodDetailId = prodDetailId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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