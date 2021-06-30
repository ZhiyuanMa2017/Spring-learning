package com.squirrel.usermanagementdocker.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterParam {

    @NotEmpty(message = "name cannot be null")
    private String userName;
    @NotEmpty(message = "password cannot be null")
    @Length(min = 6, message = "password cannot be shorter than 6")
    private String passWord;
    @Email
    private String email;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterParam{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
