package com.example.proj;

public class Inventory {

    boolean diapers;
    boolean food;
    boolean clothes;
    boolean wipes;

    public Inventory() {}

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
