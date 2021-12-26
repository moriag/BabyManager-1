package com.example.proj;

public class KidInfo {

    String name;
    boolean attendance;
    Inventory inventory;

    public KidInfo(String name, boolean attendance, Inventory inventory) {
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

