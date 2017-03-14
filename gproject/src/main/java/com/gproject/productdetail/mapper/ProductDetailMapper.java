package com.gproject.productdetail.mapper;

import com.gproject.productdetail.pojo.ProductDetail;

public interface ProductDetailMapper {
    int deleteByPrimaryKey(Integer prodDetailId);

    int insert(ProductDetail record);

    int insertSelective(ProductDetail record);

    ProductDetail selectByPrimaryKey(Integer prodDetailId);

    int updateByPrimaryKeySelective(ProductDetail record);

    int updateByPrimaryKey(ProductDetail record);
}