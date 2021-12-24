package com.example.proj;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

interface CallBack{
    void run();
    void fail(String error);
}

public class Activity {
    public static User user;
    DatabaseReference database_ref=FirebaseDatabase.getInstance().getReference();
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
    }


//    public static void addKidToDatabase(String name, String email_1, String email_2, String remark,CallBack callBack) {
//
//
//        DataHendler.addKidToParent(name,email_1,new CallBack(){
//
//            @Override
//            public void run() {
//
//                DataHendler.addKidToParent(name,email_2,new CallBack(){
//
//                    @Override
//                    public void run() {
//                        DataHendler.addKidToStaff(name,email_1,email_2,remark,callBack);
//
//                    }
//
//                    @Override
//                    public void fail(String error) {
//                        if(email_2.equals(""))run();
//                        else{
//                            DataHendler.removeKidFromParent(name,email_1);
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

//        DataHendler.addKidToStaff(firstName,lastName,email_1,email_2,remark);
//        callBack.run();
    }
}
