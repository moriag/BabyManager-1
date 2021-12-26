package com.example.proj;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Parent extends User{


    public Parent(String uid, CallBack callBack) {
        super(uid,"Parent",callBack);
    }

    @Override
    public void setInfo(String name, CallBack callBack) {
        database_ref.child("Staff").child(getKid(name)).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<UserInfo> staffinfo=new ArrayList<UserInfo>(1);
                staffinfo.add(snapshot.getValue(UserInfo.class));
                info.put(name,staffinfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
