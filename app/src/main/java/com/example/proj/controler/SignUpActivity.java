package com.example.proj.controler;

import com.example.proj.model.CallBack;
import com.example.proj.model.DataHandlerCreateUser;
import com.example.proj.model.UserInfo;

public class SignUpActivity {

    public static void CreateUser(UserInfo info, String password, String userType, CallBack callBack) {
        DataHandlerCreateUser dhc = new DataHandlerCreateUser();
        dhc.newUser(info.email, password,new CallBack() {
            @Override
            public void run() {
                dhc.CreateUserEntry(info, userType, callBack);
            }

            @Override
            public void fail(String error) {

            }
        });

    }




//    public static void CreateStaffUser(String name, String email, String password,CallBack callBack) {
//        DataHandlerCreateUser.SignUp(email, password,new CallBack() {
//            @Override
//            public void run() {
//                DataHandlerCreateUser.CreateParentEntry(name,callBack);
//            }
//
//            @Override
//            public void fail(String error) {
//
//            }
//        });
//
//    }



//    public static SignIn
}
