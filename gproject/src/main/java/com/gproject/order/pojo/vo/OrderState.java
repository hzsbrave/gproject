package com.gproject.order.pojo.vo;

/**
 * Created by Administrator on 2017/3/13.
 */
public class OrderState {
   // 1:未付款、
    public static int NO_PAY=1;
    //2:待发货、
    public static int WAIT_SENDING=2;
    //3:待签收、
    public static int WAIT_RECIEVING=3;
    //7:完成、
    public static int COMPLETE=7;
    //9:申诉、
    public static int COMPLAINT=9;
    //11:退款成功
    public static int REFUND_SUCCESS=11;



}
