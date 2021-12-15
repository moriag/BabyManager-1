package com.example.proj;

public class Kid {

    static int id_gen = 0;
    int id;
    String name;
    String remark;

    public Kid() {

        this.id = id_gen++;

    }

    public int getID(){

        return this.id;

    }
    public void setID(int id){
        this.id = id;
    }
    public String getName() {

        return this.name;

    }
    public void setName(String name){

        this.name = name;

    }
    public String getRemark(){

        return this.remark;

    }
    public void setRemark(String remark) {

        this.remark = remark;

    }

}
