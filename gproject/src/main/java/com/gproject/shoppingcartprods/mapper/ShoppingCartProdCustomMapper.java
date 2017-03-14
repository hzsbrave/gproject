package com.gproject.shoppingcartprods.mapper;

import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProdCustom;

public interface ShoppingCartProdCustomMapper {

    void updateCartAmout(ShoppingCartProdCustom vo);

    void insertShoppingCartProd(ShoppingCartProd prod);

    ShoppingCartProdCustom queryShoppingCartProdByUserIdAndCartId(ShoppingCartProd prod);

    void deleteShoppingProdByCartIdAndProdId(ShoppingCartProd prod);
}