package com.example.proj;

public class Kid {

    String name;
    String remark;
    boolean diapers;
    boolean food;
    boolean clothes;


    public Kid() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String toString() {

        return getName() + " " + getRemark();

    }

}
