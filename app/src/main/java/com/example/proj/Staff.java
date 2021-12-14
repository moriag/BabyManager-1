package com.example.proj;

import android.hardware.lights.LightsManager;

import java.util.ArrayList;
import java.util.List;

public class Staff {

    static int id = 0;
    private String name;
    private List<Integer> kids;

    public Staff() {

    }
    public Staff(String name) {

        this.name = name;
        kids = new ArrayList<Integer>();

    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {

        this.name = name;

    }
    public List<Integer> getKids() {

        return this.kids;
    }
    public void setKids(List<Integer> kids) {

        this.kids = kids;

    }

    public int addKid() {

        kids.add(id++);
        return 4;

    }

}
