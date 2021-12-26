<<<<<<< HEAD
package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.AndroidException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddKidActivity extends AppCompatActivity {

    private String staff_id;
    private FirebaseAuth db;
    FirebaseUser user;
    FirebaseDatabase fdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);
        Intent intent = getIntent();
        staff_id = intent.getStringExtra("staff_id");
        db = FirebaseAuth.getInstance();
        user = db.getCurrentUser();
        fdb = FirebaseDatabase.getInstance();
    }
    public void onStart() {
        super.onStart();
        user = db.getCurrentUser();
    }


    public void sign_up(View v) {

        /*EditText email_p1 = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        EditText pass_p1 = (EditText)findViewById(R.id.editTextNumberPassword);
        EditText name_p1 = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText email_p2 = (EditText)findViewById(R.id.editTextTextEmailAddress3);
        EditText pass_p2 = (EditText)findViewById(R.id.editTextNumberPassword2);
        EditText name_p2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        EditText name_k = (EditText)findViewById(R.id.editTextTextPersonName3);
        EditText remark = (EditText)findViewById(R.id.editTextTextPersonName4);

        DatabaseReference ref = fdb.getReference("Staff/" + staff_id);

        ref.child("Kids").child(name_k.getText().toString()).setValue(remark.getText().toString());
        ref.child("Inventory").child(name_k.getText().toString()).setValue(new Inventory());
        ref.child("Attendance").child(name_k.getText().toString()).setValue(false);


        db.createUserWithEmailAndPassword(email_p1.getText().toString(), pass_p1.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            DatabaseReference ref = fdb.getReference("Staff/" + staff_id + "/Info/" + name_k.getText().toString());
                            ref.child(db.getUid()).setValue(new Parent("053******", email_p1.getText().toString(), name_p1.getText().toString()));
                            ref = fdb.getReference("Parent");
                            ref.child(db.getUid()).child("kids").child(name_k.getText().toString()).setValue(staff_id);
                        }
                        else
                        {
                            Toast.makeText(AddKidActivity.this,"Failed(1111111)!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        db.createUserWithEmailAndPassword(email_p2.getText().toString(), pass_p2.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            DatabaseReference ref = fdb.getReference("Staff/" + staff_id + "/Info/" + name_k.getText().toString());
                            ref.child(db.getUid()).setValue(new Parent("053******", email_p2.getText().toString(), name_p2.getText().toString()));
                            ref = fdb.getReference("Parent");
                            ref.child(db.getUid()).child("kids").child(name_k.getText().toString()).setValue(staff_id);


                            //Toast.makeText(AddKidActivity.this,db.getUid(), Toast.LENGTH_LONG).show();
                            //db.signOut();
                            Intent i = new Intent(AddKidActivity.this, MainActivity.class);
                            //i.putExtra("sign", "t");
                            //i.putExtra("staff", staff_id + "");
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(AddKidActivity.this,"Failed(222222)!", Toast.LENGTH_LONG).show();
                        }
                    }
                });*/


    }


=======
package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddKidActivity extends AppCompatActivity {
    private EditText first_name;
    private EditText last_name;
    private EditText email1;
    private EditText email2;
    private EditText remark;


    private String staff_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);
        Intent intent = getIntent();
        first_name = (EditText) findViewById(R.id.editTextFirstName);
        last_name = (EditText) findViewById(R.id.editTextLastName);
        email1 = (EditText) findViewById(R.id.editTextEmailAddress1);
        email2 = (EditText) findViewById(R.id.editTextEmailAddress2);
        remark = (EditText) findViewById(R.id.editTextRemark);

        staff_id = intent.getStringExtra("staff_id");


    }
    public void onStart() {
        super.onStart();
    }


    @SuppressLint("NewApi")
    public void addKid(View v) {
        Log.d("start","start");
        String firstName=first_name.getText().toString();
        String lastName=last_name.getText().toString();
        String fullName=firstName+" "+lastName;
        String email_1=email1.getText().toString();
        String email_2=email2.getText().toString();
        String rmrk=remark.getText().toString();
        Activity.addKidToDatabase(fullName, email_1, email_2, rmrk, new CallBack() {
            @Override
            public void run() {
                startActivity(new Intent(AddKidActivity.this, StaffActivity.class));

            }

            @Override
            public void fail(String error) {
                Toast.makeText(AddKidActivity.this,error,Toast.LENGTH_LONG).show();
            }
        });

    }


>>>>>>> b81bc5ee7d7d45fbf4bf1a42f8ff37c2434efde6
}