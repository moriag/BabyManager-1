package com.example.proj;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

interface CallBack{
    void run();
    void fail(String error);
}

public class Activity {
    public static User user;
    DatabaseReference database_ref = FirebaseDatabase.getInstance().getReference();
    public static void startActivity(String name,String pass,CallBack callback){

        DataHendler.LoginUser(name, pass, new CallBack() {
            @Override
            public void run() {
                setUser(callback);
            }

            @Override
            public void fail(String error) {
                callback.fail(error);
            }
        });

    }

    private static void setUser(CallBack callback) {
       DataHendler.setUser(callback);



<<<<<<< HEAD
//    public static void addKidToDatabase(String name, String email_1, String email_2, String remark,CallBack callBack) {
//
//
//        DataHandler.addKidToParent(name,email_1,new CallBack(){
//
//            @Override
//            public void run() {
//
//                DataHandler.addKidToParent(name,email_2,new CallBack(){
//
//                    @Override
//                    public void run() {
//                        DataHandler.addKidToStaff(name,email_1,email_2,remark,callBack);
//
//                    }
//
//                    @Override
//                    public void fail(String error) {
//                        if(email_2.equals(""))run();
//                        else{
//                            DataHandler.removeKidFromParent(name,email_1);
//                            callBack.fail(error);
//                        }
//
//                    }
//                });
//            }
//
//            @Override
//            public void fail(String error) {
//
//            }
//        });

//        DataHandler.addKidToStaff(firstName,lastName,email_1,email_2,remark);
//        callBack.run();
=======
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void addKidToDatabase(String name, String email_1, String email_2, String remark, CallBack callBack) {
        if(email_1.isEmpty()){
            callBack.fail("Email for parent1 must be filled");
            return;
        }
        if (!user.AddKid(name,remark)){
            callBack.fail("a child with this name is already added");
            return;
        }
        DataHendler.getUidByEmail(email_1,"Parent",new StringCallBack() {
            @Override
            public void run(String UID1) {

                if (!email_2.isEmpty()) {
                    DataHendler.getUidByEmail(email_2,"Parent", new StringCallBack() {
                        @Override
                        public void run(String UID2) {
                            Log.d("uid",UID2);
                            DataHendler.addKidToParent(name, UID2);
                            DataHendler.addKidToParent(name, UID1);
                            DataHendler.addKidToStaff(name, remark);
                            callBack.run();

                        }

                        @Override
                        public void fail(String error) {
                            user.removeKid(name);
                            callBack.fail(error);
                        }
                    });
                }
                else {
                    DataHendler.addKidToParent(name, UID1);
                    DataHendler.addKidToStaff(name, remark);
                    callBack.run();
                }

            }

            @Override
            public void fail(String error) {
                user.removeKid(name);
                callBack.fail(error);
            }
        });
>>>>>>> b81bc5ee7d7d45fbf4bf1a42f8ff37c2434efde6
    }
}
