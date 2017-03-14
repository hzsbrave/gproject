package com.gproject.email.util;

public class VerCodeuUtil {

    public static String getVerificationCode() {
        String vaccount = String.valueOf((int) (Math.random() * 1000000));
        boolean isOk = true;
        while (isOk) {
            if (vaccount.length() != 6) {
                vaccount = String.valueOf((int) (Math.random() * 1000000));
            } else {
                isOk = false;
            }
        }
        return vaccount;
    }


}
