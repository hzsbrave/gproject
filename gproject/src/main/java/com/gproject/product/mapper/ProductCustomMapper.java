package com.gproject.product.mapper;

import com.gproject.product.pojo.Product;

import java.util.List;

public interface ProductCustomMapper {

    /**
     * 批量更新产品
     * @param list
     */
    public void updateProductSaleNumByProductIdBatch(List<Product> list);


}