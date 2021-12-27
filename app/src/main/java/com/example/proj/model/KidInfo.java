package com.example.proj.model;

public class KidInfo {

    String name;
    boolean attendance;
    KidInventory inventory;

    public KidInfo(String name, boolean attendance, KidInventory inventory) {
        this.name = name;
        this.attendance = attendance;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public KidInventory getInventory() {
        return inventory;
    }

    public void setInventory(KidInventory inventory) {
        this.inventory = inventory;
    }
}

