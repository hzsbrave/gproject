package com.gproject.shoppingcartprods.mapper;

import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;

public interface ShoppingCartProdMapper {
    int deleteByPrimaryKey(Integer scpId);

    int insert(ShoppingCartProd record);

    int insertSelective(ShoppingCartProd record);

    ShoppingCartProd selectByPrimaryKey(Integer scpId);

    int updateByPrimaryKeySelective(ShoppingCartProd record);

    int updateByPrimaryKey(ShoppingCartProd record);
}