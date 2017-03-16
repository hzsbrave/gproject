package com.gproject.shoppingcartprods.mapper;

import com.gproject.shoppingcart.pojo.vo.ShoppingProdVo;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProdCustom;

import java.util.List;

public interface ShoppingCartProdCustomMapper {

    void updateCartAmout(ShoppingCartProdCustom vo);

    void insertShoppingCartProd(ShoppingCartProd prod);

    ShoppingCartProdCustom queryShoppingCartProdByUserIdAndCartId(ShoppingCartProd prod);

    void deleteShoppingProdByCartIdAndProdId(ShoppingCartProd prod);
    /**
     * 批量删除购物车中的产品
     * @param vo
     */
    public void deleteShoppingProdBatch(List<ShoppingProdVo> vo);
}