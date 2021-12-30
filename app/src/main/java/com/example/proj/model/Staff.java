package com.example.proj.model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class Staff extends User {

//    public Staff(String userID, String userType) {
//        super(userID,userType);
//    }

//    public Staff(String name) {
//        super(name);
//    }

    public Staff(String uid, CallBack callback) {
        super(uid,"Staff",callback);
    }

    @Override
    protected void setInfo(String name) {
        Vector<UserInfo> parentsInfo= new Vector<>(2);
        info.put(name,parentsInfo);
        database_ref.child("Staff").child(getUID()).child("Parents").child(name).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                ArrayList<String> parents=new ArrayList<String>(((HashMap<String,String>) snapshot.getValue()).values());
                for (DataSnapshot parent:snapshot.getChildren()){
                    database_ref.child("Parent").child(parent.getValue().toString()).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            parentsInfo.add(snapshot.getValue(UserInfo.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void addKid(String name, String remark){
        super.addKid(name,remark);
        inventory.setInventoryKidListeners(name,getUID());
        attendance.setKidAttendanceListener(name,getUID());
    }

//    public void addParents(String key, ArrayList<String> parents) {
//        this.parents.put(key,parents);
//    }
}
