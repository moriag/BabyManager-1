package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentActivity extends AppCompatActivity {

    private FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        db = FirebaseAuth.getInstance();

    }

    public void ret(View v) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void sign_out(View v) {

        db.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();


    }

}
