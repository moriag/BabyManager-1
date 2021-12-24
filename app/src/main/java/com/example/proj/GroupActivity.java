package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GroupActivity extends AppCompatActivity {
    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText phone;
    private EditText password;
    private UserInfo info=new UserInfo();
//    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_group);
        first_name = (EditText) findViewById(R.id.firstName);
        last_name = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.editTextPhone);
        password = (EditText) findViewById(R.id.password);
    }
    private void nextActivity(){
        Log.d("end","next");
        Intent i = new Intent(GroupActivity.this, MainActivity.class);
        startActivity(i);
    }
    public void parent(View v){

        Log.d("start","parent");
        create(v,"Parent");
//        Log.d("email",info.email);

    }
    public void staff(View v){
        Log.d("start","Staff");
        create(v,"Staff");
//        Log.d("email",info.email);

    }
    private void create(View v,String userType) {


//        info.first_name = first_name.getText().toString();
//        info.last_name = last_name.getText().toString();
//        info.email =email.getText().toString();
//        info.phone_number = phone.getText().toString();
        String pass = password.getText().toString();
        UserInfo info=new UserInfo(first_name.getText().toString(),last_name.getText().toString(),phone.getText().toString(),email.getText().toString());
        Log.d("email",info.email);
        SignUpActivity.CreateUser(info, pass,userType, new CallBack() {
            @Override
            public void run() {
                nextActivity();
            }

            @Override
            public void fail(String error) {

            }
        });
        }

//        SignUpActivity.CreateUser(email, password,)


//        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
//        DatabaseReference dbr = fdb.getReference(userType);
//
//        db.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful())
//                        {
//                            dbr.child(db.getUid().toString()).child("Name").setValue(first_name.getText().toString());
//                            db.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                                    .addOnCompleteListener(GroupActivity.this, new OnCompleteListener<AuthResult>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<AuthResult> task) {
//                                            if (task.isSuccessful())
//                                            {
//                                                Intent i = new Intent(GroupActivity.this, StaffActivity.class);
//                                                startActivity(i);
//                                            }
//                                            else
//                                            {
//                                                Toast.makeText(GroupActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
//                                            }
//                                        }
//                                    });
//                        }
//                        else
//                        {
//                            Toast.makeText(GroupActivity.this,"Failed!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }

}
