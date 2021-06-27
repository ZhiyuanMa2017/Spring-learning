package com.squirrel.usermanagement.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class LoginParam {

    @NotEmpty(message = "name cannot be null")
    private String loginName;
    @NotEmpty(message = "password cannot be null")
    @Length(min = 6, message = "password cannot be shorter than 6")
    private String passWord;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "LoginParam{" +
                "loginName='" + loginName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
