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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = db.getCurrentUser();
        if(currentUser != null)
        {
            Intent i = new Intent(this, ParentActivity.class);
            startActivity(i);
        }
    }

    public void new_group(View v) {

        Intent i = new Intent(this, GroupActivity.class);
        startActivity(i);

    }

    public void log_in(View v) {

        EditText em = findViewById(R.id.editTextTextEmailAddress);
        EditText pw = findViewById(R.id.editTextTextPassword);
        CheckBox p = findViewById(R.id.checkParent);
        CheckBox s = findViewById(R.id.checkStaff);
        String email = em.getText().toString();
        String password = pw.getText().toString();
        boolean parent = p.isChecked();
        boolean staff = s.isChecked();

        db.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser user = db.getCurrentUser();
                            Intent i = new Intent(MainActivity.this, ParentActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        /*if(!parent && !staff)
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
        if(parent)
        {
            Intent i = new Intent(this, ParentActivity.class);
            startActivity(i);
        }
        if(staff)
        {
            Intent i = new Intent(this, StaffActivity.class);
            startActivity(i);
        }*/

    }

}