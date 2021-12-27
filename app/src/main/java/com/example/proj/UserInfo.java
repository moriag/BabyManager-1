package com.example.proj;

public class UserInfo {
    String phone_number;
    String email;
    String first_name;
    String last_name;


    public UserInfo(String first_name, String last_name, String phone, String email) {
        this.phone_number = phone;
        this.email = email;
        this.first_name = first_name;
        this.last_name=last_name;
    }


    public UserInfo() {

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

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String name){
        first_name=name;
    }

    public String getLast_name() {
        return last_name;
    }
}
