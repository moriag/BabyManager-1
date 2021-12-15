package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddKidActivity extends AppCompatActivity {

    private int id;
    private String group;
    private FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        group = intent.getStringExtra("group");
        db = FirebaseAuth.getInstance();
    }

    public void sign_up(View v) {

        EditText email = findViewById(R.id.text_view_email);
        EditText pass = findViewById(R.id.text_view_pass);
        EditText p_name = findViewById(R.id.text_view_p_name);
        EditText k_name = findViewById(R.id.text_view_k_name);
        EditText remark = findViewById(R.id.text_view_remark);

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference dbr = fdb.getReference("Groups");

        db.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            dbr.child(group).child("parent").child(db.getUid().toString()).child("name").setValue(p_name.getText().toString());
                            dbr.child(group).child("parent").child(db.getUid().toString()).child("Kid-id").setValue(id);
                            dbr.child(group).child("Kid").child(id+"").child("name").setValue(k_name.getText().toString());
                            dbr.child(group).child("Kid").child(id+"").child("remark").setValue(remark.getText().toString());
                            dbr.child(group).child("Kid").child(id+"").child("diapers").setValue(true);
                            dbr.child(group).child("Kid").child(id+"").child("food").setValue(true);
                            dbr.child(group).child("Kid").child(id+"").child("clothes").setValue(true);
                            Intent i = new Intent(AddKidActivity.this, StaffActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(AddKidActivity.this,"Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}