package com.gproject.favorite.pojo.vo;

import com.gproject.favorite.pojo.FavoriteCustom;
import com.gproject.product.pojo.Product;

/**
 * Created by Administrator on 2017/4/7.
 */
public class FavoriteResponse extends FavoriteCustom {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
