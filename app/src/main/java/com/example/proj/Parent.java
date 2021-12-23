package com.example.proj;

public class Parent {

    String phone_number;
    String email;
    String name;

    public Parent(String phone_number, String email, String name) {

        this.phone_number = phone_number;
        this.email = email;
        this.name = name;

    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;

    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
