package com.example.proj;

import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataHendler {

    public static void LoginUser(String email,String password,CallBack callback){
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            callback.run();
                        }
                        else
                        {
                            callback.fail("Authentication failed.");
                        }
                    }
                });
    }

    public static void setUser(CallBack callback) {

        DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference();
        final String[] userType = new String[1];
        userType[0] ="Staff";
        DatabaseReference type_ref = database_ref.child(userType[0]);
        type_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uid=FirebaseAuth.getInstance().getUid();
                for(DataSnapshot snap : snapshot.getChildren())
                {
                    //Toast.makeText(MainActivity.this, db.getUid(), Toast.LENGTH_LONG).show();
                    if(uid.equals(snap.getKey()))
                    {
                        Activity.user=new Staff(uid,userType[0]);
                        setUserKids(type_ref.child(uid).child("Kids"),callback);
                        return;

                    }
                }
                //if parent
                userType[0] ="Parent";
                Activity.user=new Parent(uid,userType[0]);
                setUserKids(database_ref.child(userType[0]).child(uid).child("Kids"),callback);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.fail("user type extraction failure");
            }
        });
    }

    private static void setUserKids(DatabaseReference kids_ref, CallBack callback) {
        kids_ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot kid:snapshot.getChildren())
                    Activity.user.AddKid(kid.getKey(), kid.getValue().toString());

//                Log.d("kids:\n",kids.toString());
                callback.run();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.fail("failed to get kids data");
            }
        });
    }
}