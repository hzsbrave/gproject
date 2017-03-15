package com.gproject.paypal.pojo;

/**
 * Created by Administrator on 2017/3/15.
 */
public class PayPalVo {

    private String token;
    private String url;
    private String transactionId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
