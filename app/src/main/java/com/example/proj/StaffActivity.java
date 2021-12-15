package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StaffActivity extends AppCompatActivity {

    private FirebaseAuth db;
    private String group;
    static int id_gen = 0;
    private List<Kid> kids;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        db = FirebaseAuth.getInstance();
        Intent in = getIntent();
        group = in.getStringExtra("group");
        ll = findViewById(R.id.kidsList);

        /*FirebaseDatabase dbd = FirebaseDatabase.getInstance();

        DatabaseReference ref = dbd.getReference("Groups");
        ValueEventListener a = ref.addValueEventListener(new ValueEventListener() {
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
                    Intent i = new Intent(StaffActivity.this, MainActivity.class);
                    db.signOut();
                    Toast.makeText(StaffActivity.this, "WRONG GROUP OR USER TYPE!", Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref = dbd.getReference("Groups/" + group + "/staff");
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
                    Intent i = new Intent(StaffActivity.this, MainActivity.class);
                    db.signOut();
                    Toast.makeText(StaffActivity.this, "WRONG GROUP OR USER TYPE!", Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        show();

    }

    public void show() {

        FirebaseDatabase dbd = FirebaseDatabase.getInstance();
        DatabaseReference ref = dbd.getReference("Groups/" + group + "/Kid");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kids = new ArrayList<Kid>();
                for(DataSnapshot kidSnap : snapshot.getChildren())
                {
                    kids.add(kidSnap.getValue(Kid.class));
                }
                for(Kid k : kids)
                {
                    Toast.makeText(StaffActivity.this, k.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StaffActivity.this, "Faillllll!!!!", Toast.LENGTH_LONG).show();
            }
        });

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
