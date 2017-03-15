package com.gproject.runningaccount.pojo.vo;

import com.gproject.runningaccount.pojo.RunningAccount;

/**
 * Created by Administrator on 2017/3/15.
 */
public class RunningAccountVo extends RunningAccount {

    private String paymentMethod;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
