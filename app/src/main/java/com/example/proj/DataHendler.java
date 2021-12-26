package com.example.proj;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

interface StringCallBack{
    void run(String string);
    void fail(String string);
}
public class DataHendler {

    public static void LoginUser(String email,String password,CallBack callback){
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            callback.run();
                        }
                        else
                        {
                            callback.fail("Authentication failed.");
                        }
                    }
                });
    }

    public static void setUser(CallBack callback) {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference();
        database_ref.child("Staff").child(firebaseAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    Activity.user=new Staff(firebaseAuth.getUid(),callback);
                }
                else Activity.user=new Parent(firebaseAuth.getUid(),callback);;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        final String[] userType = new String[1];
//        userType[0] ="Staff";
//        DatabaseReference type_ref = database_ref.child(userType[0]);
//        type_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String uid=FirebaseAuth.getInstance().getUid();
//                for(DataSnapshot snap : snapshot.getChildren())
//                {
//                    //Toast.makeText(MainActivity.this, db.getUid(), Toast.LENGTH_LONG).show();
//                    if(uid.equals(snap.getKey()))
//                    {
//                        Activity.user=new Staff(uid,userType[0]);
//                        setUserKids(type_ref.child(uid).child("Kids"),callback);
//                        return;
//
//                    }
//                }
//                //if parent
//                userType[0] ="Parent";
//                Activity.user=new Parent(uid,userType[0]);
//                setUserKids(database_ref.child(userType[0]).child(uid).child("Kids"),callback);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                callback.fail("user type extraction failure");
//            }
//        });
    }

//    private static void setUserKids(DatabaseReference kids_ref, CallBack callback) {
//        kids_ref.addValueEventListener(new ValueEventListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot kid:snapshot.getChildren())
//                    Activity.user.AddKid(kid.getKey(), kid.getValue().toString());
//
////                Log.d("kids:\n",kids.toString());
//                callback.run();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                callback.fail("failed to get kids data");
//            }
//        });
//    }

    public static void getUidByEmail(String email,String userType,StringCallBack callBack) {
        DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference();
        database_ref.child(userType).child("EmailToUid").child(email.replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()==null)callBack.fail(email + " is not in database");
                else callBack.run(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void addKidToStaff(String name,String remark) {
        DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference().child("Staff").child(Activity.user.getUID());
        database_ref.child("Kids").child(name).setValue(remark);
        database_ref.child("Attendance").child(name).setValue(false);
        database_ref.child("Inventory").child(name).setValue(new Inventory());

    }

    public static void removeKidFromParent(String name, String email_1) {
    }

    public static void addKidToParent(String name, String uid) {
        DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference();
        database_ref.child("Parent").child(uid).child("Kids").child(name).setValue(Activity.user.getUID());
        database_ref.child("Staff").child(Activity.user.getUID()).child("Parents").child(name).push().setValue(uid);
    }

//    public static void setKidAttendanceListener(String name,String staffUID){
//        FirebaseDatabase.getInstance().getReference().child("Staff").child(staffUID).child("Attendance").child(name).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Activity.user.steInAttendance(name,(Boolean)snapshot.getValue());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//    public static void setInventoryKidListeners(String name,String staffUID){
//        DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference().child("Staff").child(staffUID);
//        database_ref.child("Inventory").child(name).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Activity.user.setInInventory(name,snapshot.getValue(Inventory.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//    public static void setUserInfo(String key,String staffUID){
//        FirebaseDatabase.getInstance().getReference().child("Staff").child(staffUID).child("Info").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Activity.user.setInInfo(key,snapshot.getValue(UserInfo.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    public static void setStaffParents(){
        FirebaseDatabase.getInstance().getReference().child("Staff").child(Activity.user.getUID()).child("Parents").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> parents;
                for (DataSnapshot kid:snapshot.getChildren()){
                    parents=new ArrayList<>(2);
                    for (DataSnapshot parent:kid.getChildren()){
                        parents.add((String) parent.getValue());
                    }
//                    ((Staff)Activity.user).addParents(kid.getKey(),parents);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
