package com.example.proj.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

public class Inventory {

    private Hashtable<String, KidInventory> inventory = new Hashtable<>();
//    public Inventory(Hashtable<String,KidInventory> inventory){
//        this.inventory=inventory;
//    }
    public Inventory(){}

    public void addKid(String name,KidInventory kidInventory){
        inventory.put(name,kidInventory);
    }
    public void setInventoryKidListeners(String name,String staffUID){
        FirebaseDatabase.getInstance().getReference().child("Staff").child(staffUID).child("Inventory").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inventory.put(name,snapshot.getValue(KidInventory.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
