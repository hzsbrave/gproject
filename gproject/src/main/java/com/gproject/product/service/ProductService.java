package com.gproject.product.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.product.mapper.ProductMapper;
import com.gproject.product.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/1.
 */
@Service
public class ProductService extends BaseService<Product,Integer> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    protected BaseMapper<Product, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }

    public Object queryProduct(){
        return SUCCESS(productMapper.selectByPrimaryKey(1));
    }

}
