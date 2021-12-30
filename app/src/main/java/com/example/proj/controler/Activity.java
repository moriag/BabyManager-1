package com.example.proj.controler;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.example.proj.model.CallBack;
import com.example.proj.model.DataHandler;
import com.example.proj.model.StringCallBack;
import com.example.proj.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity {
    public static User user;
    DatabaseReference database_ref=FirebaseDatabase.getInstance().getReference();
    public static void startActivity(String name, String pass, CallBack callback){

        DataHandler.LoginUser(name, pass, new CallBack() {
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
       DataHandler.setUser(callback);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void addKidToDatabase(String name, String email_1, String email_2, String remark, CallBack callBack) {
        if(email_1.isEmpty()){
            callBack.fail("Email for parent1 must be filled");
            return;
        }
        if (!user.contains(name)){
            callBack.fail("a child with this name is already added");
            return;
        }
        DataHandler.getUidByEmail(email_1,"Parent",new StringCallBack() {
            @Override
            public void run(String UID1) {

                if (!email_2.isEmpty()) {
                    DataHandler.getUidByEmail(email_2,"Parent", new StringCallBack() {
                        @Override
                        public void run(String UID2) {
                            Log.d("uid",UID2);
                            DataHandler.addKidToParent(name, UID2);
                            DataHandler.addKidToParent(name, UID1);
                            DataHandler.addKidToStaff(name, remark);
                            callBack.run();

                        }

                        @Override
                        public void fail(String error) {
                            callBack.fail(error);
                        }
                    });
                }
                else {
                    DataHandler.addKidToParent(name, UID1);
                    DataHandler.addKidToStaff(name, remark);
                    callBack.run();
                }

            }

            @Override
            public void fail(String error) {
                callBack.fail(error);
            }
        });
    }
}
