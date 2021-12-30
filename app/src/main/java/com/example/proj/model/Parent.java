package com.example.proj.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class Parent extends User{


    public Parent(String uid, CallBack callBack) {
        super(uid,"Parent",callBack);
    }

    @Override
    public void addKid(String name, String staffUID) {
        super.addKid(name,staffUID);
        inventory.setInventoryKidListeners(name,staffUID);
        attendance.setKidAttendanceListener(name,staffUID);
    }

    @Override
    protected void setInfo(String name) {
        Vector<UserInfo> information=new Vector<>();
        info.put(name,information);
        database_ref.child("Staff").child(getKid(name)).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                information.add(snapshot.getValue(UserInfo.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
