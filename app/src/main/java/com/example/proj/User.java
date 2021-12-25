package com.example.proj;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Hashtable;

public class User {
    private String UID;
    private String name;
    private String UserType;
    private Hashtable<String,String> kids= new Hashtable<>();



    public User(String UID, String userType) {
        this.UID = UID;
        UserType = userType;
    }

    public User(String name) {
        this.name=name;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean AddKid(String key, String value){
        return(kids.putIfAbsent(key,value)==null);
    }

    public String getUID() {
        return UID;
    }

    public String getUserType() {
        return UserType;
    }

    public Hashtable<String, String> getKids() {
        return kids;
    }

    public void removeKid(String name) {
        kids.remove(name);
    }
}
