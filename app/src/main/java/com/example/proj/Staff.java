package com.example.proj;

import android.hardware.lights.LightsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Staff {

    static int id_gen = 0;
    private String name;
    private List<Kid> kids;


    public Staff() {
        kids = new ArrayList<Kid>();
    }
    public Staff(String name) {

        this.name = name;
        kids = new ArrayList<Kid>();

    }
    public Staff(String name, ArrayList<Kid> kids){
        this.name = name;
        this.kids = kids;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {

        this.name = name;

    }
    public List<Kid> getKids() {

        return this.kids;
    }
    public void setKids(ArrayList<Kid> kids) {

        this.kids = kids;

    }

    /*public void addKid(String name, String remark) {

        this.kids.add(new Kid(id_gen, name, remark));
        id_gen++;

    }*/


}
