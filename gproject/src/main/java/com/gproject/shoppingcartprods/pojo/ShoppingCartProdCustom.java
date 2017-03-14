package com.gproject.shoppingcartprods.pojo;

import com.gproject.product.pojo.Product;

public class ShoppingCartProdCustom extends ShoppingCartProd {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}