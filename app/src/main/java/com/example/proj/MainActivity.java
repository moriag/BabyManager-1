package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = db.getCurrentUser();
        if(currentUser != null)
        {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Parent");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean staff = true;
                    for(DataSnapshot snap : snapshot.getChildren())
                    {
                        //Toast.makeText(MainActivity.this, db.getUid(), Toast.LENGTH_LONG).show();
                        if(db.getUid().equals(snap.getKey()))
                        {
                            Intent i = new Intent(MainActivity.this, ParentActivity.class);
                            startActivity(i);
                            staff = false;
                            break;
                        }
                    }
                    if(staff)
                    {
                        Intent i = new Intent(MainActivity.this, StaffActivity.class);
                        startActivity(i);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void new_group(View v) {

        Intent i = new Intent(this, GroupActivity.class);
        startActivity(i);

    }

    public void log_in(View v) {


        EditText em = findViewById(R.id.editTextTextEmailAddress);
        EditText pw = findViewById(R.id.editTextNumberPassword3);


        String email = em.getText().toString();
        String password = pw.getText().toString();

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fdb.getReference("Parent");

        db.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    boolean staff = true;
                                    for(DataSnapshot snap : snapshot.getChildren())
                                    {
                                        //Toast.makeText(MainActivity.this, db.getUid(), Toast.LENGTH_LONG).show();
                                        if(db.getUid().equals(snap.getKey()))
                                        {
                                            Intent i = new Intent(MainActivity.this, ParentActivity.class);
                                            startActivity(i);
                                            staff = false;
                                            break;
                                        }
                                    }
                                    if(staff)
                                    {
                                        Intent i = new Intent(MainActivity.this, StaffActivity.class);
                                        startActivity(i);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

}