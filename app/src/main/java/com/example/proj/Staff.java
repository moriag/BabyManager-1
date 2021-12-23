package com.example.proj;

import android.hardware.lights.LightsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Staff {

    private String name;

    public Staff(String name) {

        this.name = name;

    }
    public Staff(String name, ArrayList<Kid> kids){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {

        this.name = name;

    }

}
