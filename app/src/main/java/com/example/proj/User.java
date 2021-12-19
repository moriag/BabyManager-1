package com.example.proj;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class User {
    private String UID;
    private String name;
    private String UserType;
    private Hashtable<String,String> kids= new Hashtable<>();



    public User(String UID,String userType) {
        this.UID = UID;
        UserType = userType;
    }

    public User(String name) {
        this.name=name;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void AddKid(String key, String value){
        kids.putIfAbsent(key,value);
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
}
