package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class StaffActivity extends AppCompatActivity {

    private FirebaseAuth db;
    private String group;
    static int id_gen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        db = FirebaseAuth.getInstance();
        Intent in = getIntent();
        group = in.getStringExtra("group");

    }

    public void new_kid(View v) {

        Intent i = new Intent(StaffActivity.this, AddKidActivity.class);
        i.putExtra("group", group);
        i.putExtra("id", id_gen++ + "");
        startActivity(i);

    }

    public void sign_out(View v) {

        db.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}
