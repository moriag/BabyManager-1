package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddKidActivity extends AppCompatActivity {

    private int id;
    private String group;
    private String staff_id;
    private FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));
        staff_id = intent.getStringExtra("staff_id");
        db = FirebaseAuth.getInstance();
    }

    public void sign_up(View v) {

        EditText email_p1 = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        EditText pass_p1 = (EditText)findViewById(R.id.editTextNumberPassword);
        EditText name_p1 = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText email_p2 = (EditText)findViewById(R.id.editTextTextEmailAddress3);
        EditText pass_p2 = (EditText)findViewById(R.id.editTextNumberPassword2);
        EditText name_p2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        EditText name_k = (EditText)findViewById(R.id.editTextTextPersonName3);
        EditText remark = (EditText)findViewById(R.id.editTextTextPersonName4);

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fdb.getReference("Staff");
        ref.child(staff_id).child("Kids").child(id + "").child("Inventory").child("diapers").setValue(true);
        ref.child(staff_id).child("Kids").child(id + "").child("Inventory").child("clothes").setValue(true);
        ref.child(staff_id).child("Kids").child(id + "").child("Inventory").child("food").setValue(true);
        ref.child(staff_id).child("Kids").child(id + "").child("Remark").setValue(remark.getText().toString());
        ref.child(staff_id).child("Kids").child(id + "").child("Name").setValue(name_k.getText().toString());

        db.createUserWithEmailAndPassword(email_p1.getText().toString(), pass_p1.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            DatabaseReference ref = fdb.getReference("Parent");
                            ref.child(db.getUid()).child("Kids").child(id + "").child("staff").setValue(staff_id);
                        }
                        else
                        {
                            Toast.makeText(AddKidActivity.this,"Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        db.createUserWithEmailAndPassword(email_p2.getText().toString(), pass_p2.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            DatabaseReference ref = fdb.getReference("Parent");
                            ref.child(db.getUid()).child("Kids").child(id + "").child("staff").setValue(staff_id);
                            Intent i = new Intent(AddKidActivity.this, StaffActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(AddKidActivity.this,"Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}