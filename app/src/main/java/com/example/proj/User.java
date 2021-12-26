package com.example.proj;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.proj.CallBack;
import com.example.proj.Inventory;
import com.example.proj.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public abstract class User {
    private String UID;
    private String name;
    private String UserType;
    private Hashtable<String,String> kids= new Hashtable<>();
    private Hashtable<String, Inventory> inventory =new Hashtable<>();
    private Hashtable<String,Boolean> attendance =new Hashtable<>();
    protected Hashtable<String,ArrayList<UserInfo>> info;


    protected DatabaseReference database_ref;
    public User(String UID, String userType, CallBack callBack) {
        database_ref= FirebaseDatabase.getInstance().getReference();
        this.UID = UID;
        UserType = userType;
        initKids(callBack);
    }


    private void initKids(CallBack callBack) {
        database_ref.child(UserType).child(UID).child("Kids").addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kids=new Hashtable<>( (HashMap<String,String>) snapshot.getValue());
//                ArrayList<UserInfo> parents=new ArrayList<UserInfo>(td.values());
//                for (DataSnapshot kid:snapshot.getChildren())
//                    AddKid(kid.getKey(), kid.getValue().toString());
                callBack.run();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.fail("failed to get kids data");
            }
        });
    }

//    public User(String name) {
//        this.name=name;
//    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean AddKid(String key, String value){
        return(kids.putIfAbsent(key,value)==null);
    }
    public String getKid(String name){
        return kids.get(name);
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

    public void setInInfo(String name,ArrayList<UserInfo> userinfo) {
        info.put(name,userinfo);
    }

    public void setInInventory(String name, Inventory value) {
        inventory.put(name,value);
    }

    private void steInAttendance(String name, Boolean value) {
        attendance.put(name,value);
    }
    public void setKidAttendanceListener(String name,String staffUID){
        database_ref.child("Staff").child(staffUID).child("Attendance").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                steInAttendance(name,(Boolean)snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void setInventoryKidListeners(String name,String staffUID){
        database_ref.child("Staff").child(staffUID).child("Inventory").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setInInventory(name,snapshot.getValue(Inventory.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public abstract void setInfo(String name,CallBack callBack);
    public ArrayList<UserInfo> getInfo(String name){
        return info.get(name);
//        return info;
    }


//    public abstract void addParents(String key, ArrayList<String> parents);
}
