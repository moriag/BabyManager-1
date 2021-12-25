package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser != null)
//        {
//            firebaseAuth.signOut();
//        }
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
        Log.d("START","starting");

        Activity act=new Activity();
        act.startActivity(email, password, new CallBack() {
            @Override
            public void run() {
                Intent intent;
                if(Activity.user.getUserType().equals("Parent"))
                    intent = new Intent(MainActivity.this, ParentActivity.class);
                else
                    intent = new Intent(MainActivity.this, StaffActivity.class);
                Log.d("End","ending");
                Log.d("UserType",Activity.user.getUserType());
                Log.d("KIDS",Activity.user.getKids().toString());
                startActivity(intent);
            }

            @Override
            public void fail(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });




    }

}