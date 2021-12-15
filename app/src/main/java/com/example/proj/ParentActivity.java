package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentActivity extends AppCompatActivity {

    private FirebaseAuth db;
    private String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        db = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        group = intent.getStringExtra("group");

        /*FirebaseDatabase dbd = FirebaseDatabase.getInstance();

        DatabaseReference ref = dbd.getReference("Groups");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean exist = false;
                for(DataSnapshot GroupSnap : snapshot.getChildren())
                {
                    if(GroupSnap.getKey() == group)
                    {
                        exist = true;
                    }
                }
                if(!exist)
                {
                    Intent i = new Intent(ParentActivity.this, MainActivity.class);
                    db.signOut();
                    Toast.makeText(ParentActivity.this, "WRONG GROUP OR USER TYPE!", Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref = dbd.getReference("Groups/" + group + "/parent");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean exist = false;
                for(DataSnapshot ParentSnap : snapshot.getChildren())
                {
                    if(ParentSnap.getKey() == db.getUid())
                    {
                        exist = true;
                    }
                }
                if(!exist)
                {
                    Intent i = new Intent(ParentActivity.this, MainActivity.class);
                    db.signOut();
                    Toast.makeText(ParentActivity.this, "WRONG GROUP OR USER TYPE!", Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }



    public void sign_out(View v) {

        db.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();


    }

}
