package com.gproject.shoppingcart.pojo.vo;

/**
 * Created by Administrator on 2017/3/4.
 */
public class ShoppingCartQueryVo {

    private  int userId;

    private int productId;

    private String account;

    private String token;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
