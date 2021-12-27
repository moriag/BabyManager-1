package com.example.proj.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

import javax.net.ssl.SSLEngineResult;

public class Attendance {
    private Hashtable<String,Integer> attendance =new Hashtable<>();
    public void addKid(String name,Integer status){
        attendance.put(name,status);
    }
    public void setKidAttendanceListener(String name,String staffUID){
        FirebaseDatabase.getInstance().getReference().child("Staff").child(staffUID).child("Attendance").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addKid(name,(Integer) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
