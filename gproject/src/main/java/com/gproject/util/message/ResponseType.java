package com.gproject.util.message;

/**
 * ResponseType.java
 * describe: response类型枚举类
 * Author hezishan
 * Date 2017/2/17 15:42
 */
public class ResponseType {

    /**
     * 参数为空
     */
    public static int PARAMETER_NULL = 1;

    /**
     * 参数存在
     */
    public static int PARAMETER_EXIST = 2;
    /**
     * 参数不存在
     */
    public static int PARAMETER_NOT_EXIST = 3;
    /**
     * 参数错误
     */
    public static int PARAMETER_ERROR= 4;

    /**
     * 参数相同
     */
    public static int PARAMETER_SAME= 5;
    /**
     * 系统出错
     */
    public static int SYSTEM_ERROR= 6;

    /**
     * 库存量不足
     */
    public static int PRODUCT_NUM_OVER= 7;

    /**
     * 用户token校验错误
     */
    public static int TOKEN_ERROR= 9;

}
