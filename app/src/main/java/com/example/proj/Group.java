package com.example.proj;

import java.util.List;
import java.util.Vector;

public class Group {

    private List<Staff> staffList;
    private List<Integer> kids;
    private List<Parent> parentes;

    public Group(Staff s) {

        staffList = new Vector<Staff>();
        staffList.add(s);
        kids = new Vector<Integer>();
        parentes = new Vector<Parent>();

    }

    public void addStaff(Staff s) {

        staffList.add(s);

    }
    public void removeStaff(Staff s) {

        staffList.remove(s);

    }
    public void addKid(int id) {

        kids.add(id);

    }
    public void removeKid(int id) {

        kids.remove(id);

    }
    public void addParent(Parent p) {

        parentes.add(p);

    }
    public void removeParent(Parent p) {

        parentes.remove(p);

    }

}
