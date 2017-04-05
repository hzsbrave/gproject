package com.gproject.solr.pojo.query;

import java.util.ArrayList;
import java.util.List;

/**
 * SeachParam.java
 * describe: solr搜索参数类
 * Author hezishan
 * Date 2017/2/27 22:00
 */
public class SeachParam {

    private int productId;
    private Integer userId;
    private String keyword;
    private String productName;
    private List<Integer> categoryIds;
    private Integer categoryId;
    private String categoryName;
    private String shortDese;
    private int pageNo;
    private int pageSize;
    private boolean priceDes;
    private boolean saleNumDes;
    private boolean priceSet;
    private boolean saleNumSet;
    private ArrayList prodIds;
    /**
     * 价格排序标识位
     * 0：无排序
     * 1：升序
     * 2：降序
     */
    private int priceFlag;
    /**
     * 销售量排序标识位
     * 0：无排序
     * 1：升序
     * 2：降序
     */
    private int saleFlag;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getShortDese() {
        return shortDese;
    }

    public void setShortDese(String shortDese) {
        this.shortDese = shortDese;
    }

    public boolean isPriceDes() {
        return priceDes;
    }

    public void setPriceDes(boolean priceDes) {
        this.priceDes = priceDes;
    }

    public boolean isSaleNumDes() {
        return saleNumDes;
    }

    public void setSaleNumDes(boolean saleNumDes) {
        this.saleNumDes = saleNumDes;
    }

    public boolean isPriceSet() {
        return priceSet;
    }

    public void setPriceSet(boolean priceSet) {
        this.priceSet = priceSet;
    }

    public boolean isSaleNumSet() {
        return saleNumSet;
    }

    public void setSaleNumSet(boolean saleNumSet) {
        this.saleNumSet = saleNumSet;
    }

    public int getPriceFlag() {
        return priceFlag;
    }

    public void setPriceFlag(int priceFlag) {
        this.priceFlag = priceFlag;
    }

    public int getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(int saleFlag) {
        this.saleFlag = saleFlag;
    }

    public ArrayList getProdIds() {
        return prodIds;
    }

    public void setProdIds(ArrayList prodIds) {
        this.prodIds = prodIds;
    }

    @Override
    public String toString() {
        return "SeachParam{" +
                "productId=" + productId +
                ", keyword='" + keyword + '\'' +
                ", productName='" + productName + '\'' +
                ", categoryId=" + categoryIds +
                ", categoryName='" + categoryName + '\'' +
                ", shortDese='" + shortDese + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", priceDes=" + priceDes +
                ", saleNumDes=" + saleNumDes +
                ", priceSet=" + priceSet +
                ", saleNumSet=" + saleNumSet +
                ", priceFlag=" + priceFlag +
                ", saleFlag=" + saleFlag +
                '}';
    }
}
