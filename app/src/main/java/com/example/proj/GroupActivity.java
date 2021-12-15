package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GroupActivity extends AppCompatActivity {

    private FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_group);
        db = FirebaseAuth.getInstance();
    }


    public void create(View v) {

        EditText group = (EditText) findViewById(R.id.group_name);
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference dbr = fdb.getReference("Groups");

        db.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            dbr.child(group.getText().toString()).child("staff").child(db.getUid().toString()).setValue(new Staff(name.getText().toString()));
                            db.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                    .addOnCompleteListener(GroupActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful())
                                            {
                                                Intent i = new Intent(GroupActivity.this, StaffActivity.class);
                                                i.putExtra("group", group.getText().toString());
                                                startActivity(i);
                                            }
                                            else
                                            {
                                                Toast.makeText(GroupActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                        else
                        {
                            Toast.makeText(GroupActivity.this,"Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}
