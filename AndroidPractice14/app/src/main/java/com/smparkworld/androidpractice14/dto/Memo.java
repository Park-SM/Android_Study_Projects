package com.smparkworld.androidpractice14.dto;

public class Memo {

    private String name;
    private String address;
    private String memo;

    public Memo (String name, String address, String memo) {
        this.name = name;
        this.address = address;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
