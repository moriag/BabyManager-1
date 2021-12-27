package com.example.proj.model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void setInfo(String name, CallBack callBack) {
        database_ref.child("Staff").child(getUID()).child("Parents").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                Map<String, UserInfo> td = ;
                ArrayList<String> parents=new ArrayList<String>(((HashMap<String,String>) snapshot.getValue()).values());
                database_ref.child("Parent").child(parents.get(0)).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<UserInfo> parentinfo= new ArrayList<UserInfo>(2);
                        parentinfo.add(snapshot.getValue(UserInfo.class));
                        if(parents.size()==1){
                            info.put(name,parentinfo);
                            callBack.run();
                        }
                        else {
                            database_ref.child("Parent").child(parents.get(1)).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    parentinfo.add(snapshot.getValue(UserInfo.class));
                                    info.put(name, parentinfo);
                                    callBack.run();
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
//                    ((Staff)Activity.user).addParents(kid.getKey(),parents);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean AddKid(String key, String value){
        if(super.AddKid(key,value))return false;
        return true;
    }

//    public void addParents(String key, ArrayList<String> parents) {
//        this.parents.put(key,parents);
//    }
}
