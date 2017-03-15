package com.gproject.runningaccount.pojo;

import java.math.BigDecimal;

public class RunningAccount {
    private Integer runningAccountId;

    private Integer userId;

    private String transactionId;

    private Integer type;

    private BigDecimal amount;

    private Integer entityId;

    public Integer getRunningAccountId() {
        return runningAccountId;
    }

    public void setRunningAccountId(Integer runningAccountId) {
        this.runningAccountId = runningAccountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}