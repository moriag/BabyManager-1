package com.example.proj;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataHandlerCreateUser {
    private String UID;
//    private DatabaseReference dbr;
//    public DataHandlerCreateUser()
    public void newUser(String email, String password,CallBack callBack) {
        FirebaseAuth db=FirebaseAuth.getInstance();
//        db=FirebaseAuth.getInstance();
        db.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UID= db.getUid();
                            callBack.run();
                        }
                    }
                });

    }
//    public static void SignUpParent(String email, String password,CallBack callBack) {
//
//        db=FirebaseAuth.getInstance();
//        FirebaseUser user=db.getCurrentUser();//Current user is staff member
//        db.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//
//                            db.updateCurrentUser(user);
//                            callBack.run();
//                        }
//                    }
//                });
//
//    }

    public void CreateUserEntry(UserInfo info,String userType, CallBack callBack) {
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference dbr = fdb.getReference();
        dbr.child("EmailToUid").child(info.email).setValue(UID);
        dbr=dbr.child(userType).child(UID);
        dbr.child("info").setValue(info);
//        dbr.child("Kids")
//        if(userType.equals("Staff"))

//        dbr.child("Info");
//        dbr.child("Attendance");
//        dbr.child("Inventory");
        callBack.run();
    }
//    public static void CreateParentEntry(UserInfo info, CallBack callBack) {
//        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
//        DatabaseReference dbr = fdb.getReference("Parent").child(db.getUid());
//        dbr.child("Name").setValue(name);
//        dbr.child("Kids");
//        dbr.child("StaffID");
//        callBack.run();
//    }

}
