package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = db.getCurrentUser();
        if(currentUser != null)
        {
            db.signOut();
        }
    }

    public void new_group(View v) {

        Intent i = new Intent(this, GroupActivity.class);
        startActivity(i);

    }

    public void log_in(View v) {


        EditText em = findViewById(R.id.editTextTextEmailAddress);
        EditText pw = findViewById(R.id.editTextTextPassword);
        EditText gr = findViewById(R.id.group);

        Switch p = findViewById(R.id.isParent);
        Switch s = findViewById(R.id.isStaff);

        String group = gr.getText().toString();
        String email = em.getText().toString();
        String password = pw.getText().toString();

        boolean parent = p.isChecked();
        boolean staff = s.isChecked();

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fdb.getReference("Groups");


        if(!parent && !staff)
        {
            Toast.makeText(this, "Must check one of the options!", Toast.LENGTH_LONG).show();
            return;
        }
        if(parent && staff)
        {
            p.setChecked(false);
            s.setChecked(false);
            Toast.makeText(this, "Can't check both options!", Toast.LENGTH_LONG).show();
            return;
        }


        db.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent i = new Intent(MainActivity.this, StaffActivity.class);
                            if(parent)
                            {
                                i = new Intent(MainActivity.this, ParentActivity.class);
                            }
                            i.putExtra("group", group);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

}