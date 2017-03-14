package com.gproject.user.pojo.vo;

/**
 * modify by hezishan
 * <p/>
 * Description:用户注册实例
 * <p/>
 * time:2017/2/13
 */
public class UserExample {

    private String email;
    private String vercode;
    private int type;
    private String password;

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
