package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddKidActivity extends AppCompatActivity {
    private EditText first_name;
    private EditText last_name;
    private EditText email1;
    private EditText email2;
    private EditText remark;


    private String staff_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);
        Intent intent = getIntent();
        first_name = (EditText) findViewById(R.id.editTextFirstName);
        last_name = (EditText) findViewById(R.id.editTextLastName);
        email1 = (EditText) findViewById(R.id.editTextEmailAddress1);
        email2 = (EditText) findViewById(R.id.editTextEmailAddress2);
        remark = (EditText) findViewById(R.id.editTextRemark);

        staff_id = intent.getStringExtra("staff_id");


    }
    public void onStart() {
        super.onStart();
    }


    @SuppressLint("NewApi")
    public void addKid(View v) {
        Log.d("start","start");
        String firstName=first_name.getText().toString();
        String lastName=last_name.getText().toString();
        String fullName=firstName+" "+lastName;
        String email_1=email1.getText().toString();
        String email_2=email2.getText().toString();
        String rmrk=remark.getText().toString();
        Activity.addKidToDatabase(fullName, email_1, email_2, rmrk, new CallBack() {
            @Override
            public void run() {
                startActivity(new Intent(AddKidActivity.this, StaffActivity.class));

            }

            @Override
            public void fail(String error) {
                Toast.makeText(AddKidActivity.this,error,Toast.LENGTH_LONG).show();
            }
        });

    }


}