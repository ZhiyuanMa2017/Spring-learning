package com.squirrel.usermanagementdocker.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class UserParam {

    private String id;
    @NotEmpty(message = "name cannot be null")
    private String userName;
    @NotEmpty(message = "password cannot be null")
    @Length(min = 6, message = "password cannot be shorter than 6")
    private String passWord;
    @Email
    private String email;
    @Max(value = 100, message = "at most 100")
    @Min(value = 18, message = "at least 18")
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
