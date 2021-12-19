package com.example.proj;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

interface CallBack{
    void run();
    void fail(String error);
}
public class Activity {
    public static User user;


    DatabaseReference database_ref=FirebaseDatabase.getInstance().getReference();
    public static void startActivity(String name,String pass,CallBack callback){

        DataHendler.LoginUser(name, pass, new CallBack() {
            @Override
            public void run() {
                setUser(callback);
            }

            @Override
            public void fail(String error) {
                callback.fail(error);
            }
        });

    }

    private static void setUser(CallBack callback) {
       DataHendler.setUser(callback);
    }



}
