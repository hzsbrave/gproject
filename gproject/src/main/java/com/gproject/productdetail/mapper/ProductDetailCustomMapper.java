package com.gproject.productdetail.mapper;

import com.gproject.solr.pojo.vo.ProductDetail;

import java.util.List;


public interface ProductDetailCustomMapper {

    /**
     * 批量更新产品详情
     * @param list
     */
    public void updateProductNumByProductIdBatch(List<ProductDetail> list);

}