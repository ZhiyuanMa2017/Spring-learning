package com.squirrel.basicWeb.model;

public class MyUser {

    private String name;
    private int age;
    private String pass;

    public MyUser(String name, int age, String pass) {
        this.name = name;
        this.age = age;
        this.pass = pass;
    }

    public MyUser() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
