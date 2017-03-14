package com.gproject.shoppingcart.pojo;

import com.gproject.shoppingcartprods.pojo.ShoppingCartProdCustom;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */
public class ShoppingCartCustom extends  ShoppingCart {

    private List<ShoppingCartProdCustom> shoppingCartProdCustoms;

    public List<ShoppingCartProdCustom> getShoppingCartProdCustoms() {
        return shoppingCartProdCustoms;
    }

    public void setShoppingCartProdCustoms(List<ShoppingCartProdCustom> shoppingCartProdCustoms) {
        this.shoppingCartProdCustoms = shoppingCartProdCustoms;
    }
}
