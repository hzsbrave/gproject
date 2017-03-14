package com.gproject.shoppingcartprods.pojo;

public class ShoppingCartProd {
    private Integer scpId;

    private Integer cartId;

    private Integer productId;

    private Integer amount;

    public Integer getScpId() {
        return scpId;
    }

    public void setScpId(Integer scpId) {
        this.scpId = scpId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}