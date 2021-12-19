package com.example.proj;

import android.hardware.lights.LightsManager;

import java.util.ArrayList;
import java.util.List;

public class Staff extends User {

    public Staff(String userID,String userType) {
        super(userID,userType);

    }

    public Staff(String name) {
        super(name);
    }
}
