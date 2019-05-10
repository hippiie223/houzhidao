package com.hippie.houzhidao.request;

/**
 * Created by reckywangbowen_i on 2019/03/05
 */
public class LoginRequestBody {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
