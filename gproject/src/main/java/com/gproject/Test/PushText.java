package com.gproject.Test;

import com.gproject.push.PushAPI;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class PushText {

    public static void main(String[] args) throws  Exception{
        PushAPI push=new PushAPI();
//        String token=push.getTokenByUserid("complaint","system","");
//        System.out.println(token);
        push.publishSystemMessage("complaint",new String[]{"8"},"jkkl","jlk","hello","" );
    }



}
