package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proj.model.DataHandler;
import com.example.proj.model.KidInfo;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ParentActivity extends AppCompatActivity {

    private FirebaseAuth db;

    RecyclerView rv;
    myAdapter adapter;
    ArrayList<KidInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        db = FirebaseAuth.getInstance();

        rv = findViewById(R.id.kid_list1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<KidInfo>();
        adapter = new myAdapter(this, list);
        rv.setAdapter(adapter);

        //show();

    }

    public void show() {


//        List<String> kids = getKids();
//        for(String name : kids)
        {
//            list.add(new KidInfo(name, DataHandler.getInventory(name), DataHandler.getAttendance(name)));
        }
        adapter.notifyDataSetChanged();

        /*FirebaseDatabase dbd = FirebaseDatabase.getInstance();
        DatabaseReference ref = dbd.getReference("Parent/" +  db.getUid() + "/kids");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot kidSnap : snapshot.getChildren())
                {
                    String staff = kidSnap.getValue(String.class);
                    String k_id = kidSnap.getKey();
                    DatabaseReference r = dbd.getReference("Staff/" +  staff + "/kids");
                    r.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snap: snapshot.getChildren())
                            {
                                if(snap.getKey().equals(k_id))
                                {
                                    KidInfo k = snap.getValue(KidInfo.class);
                                    list.add(k);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ParentActivity.this, "errrroooorrrrr!", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ParentActivity.this, "Faillllll!!!!", Toast.LENGTH_LONG).show();
            }
        });*/

    }

    public void sign_out(View v) {

        db.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}
