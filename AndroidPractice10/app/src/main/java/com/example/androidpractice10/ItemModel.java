package com.example.androidpractice10;

public class ItemModel {
    private String dataNo;
    private String dataName;
    private String dataContext;

    public ItemModel(String no, String name, String context){
        this.dataNo = no;
        this.dataName = name;
        this.dataContext = context;
    }

    public String getDataNo() {
        return this.dataNo;
    }

    public String getDataName() {
        return this.dataName;
    }

    public String getDataContext() {
        return this.dataContext;
    }

}
