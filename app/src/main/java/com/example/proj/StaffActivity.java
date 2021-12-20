package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    static int id_gen = 0;
    String s_id;
    String sign;

    RecyclerView rv;
    myAdapter adapter;
    ArrayList<Kid> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        db = FirebaseAuth.getInstance();
        Intent i = getIntent();

        s_id = i.getStringExtra("staff");
        sign = i.getStringExtra("sign");

        if(sign == null)
        {
            s_id = db.getUid();
        }

        rv = findViewById(R.id.kidList);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Kid>();
        adapter = new myAdapter(this, list);
        rv.setAdapter(adapter);

        show();

    }

    public void show() {

        list.clear();
        FirebaseDatabase dbd = FirebaseDatabase.getInstance();
        DatabaseReference ref = dbd.getReference("Staff/" +  s_id + "/kids");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot kidSnap : snapshot.getChildren())
                {
                    Kid k = kidSnap.getValue(Kid.class);
                    list.add(k);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StaffActivity.this, "Faillllll!!!!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void new_kid(View v) {

        Intent i = new Intent(StaffActivity.this, AddKidActivity.class);
        i.putExtra("id", id_gen++ + "");
        /*if(sign == null)
        {
            i.putExtra("staff_id", db.getUid());
        }
        else
        {
            i.putExtra("staff_id", s_id);
        }*/
        i.putExtra("staff_id", s_id);
        startActivity(i);
    }

    public void sign_out(View v) {

        db.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}
