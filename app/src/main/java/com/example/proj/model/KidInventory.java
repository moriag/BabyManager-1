package com.example.proj.model;

public class KidInventory {

    boolean diapers;
    boolean food;
    boolean clothes;
    boolean wipes;

    public KidInventory() {}

    public boolean isDiapers() {
        return diapers;
    }

    public void setDiapers(boolean diapers) {
        this.diapers = diapers;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isClothes() {
        return clothes;
    }

    public void setClothes(boolean clothes) {
        this.clothes = clothes;
    }

    public boolean isWipes() {
        return wipes;
    }

    public void setWipes(boolean wipes) {
        this.wipes = wipes;
    }
}
