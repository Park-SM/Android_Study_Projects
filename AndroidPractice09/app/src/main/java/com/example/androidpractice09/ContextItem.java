package com.example.androidpractice09;

import android.content.Context;

import java.util.ArrayList;

public class ContextItem {
    private static ArrayList<ContextItem> list;
    private String no;
    private String context;

    public ContextItem(String no, String context) {
        this.no = no;
        this.context = context;
    }

    public String getNo() {
        return this.no;
    }

    public String getContext() {
        return this.context;
    }

    public static ArrayList<ContextItem> getList(){
        list = new ArrayList<ContextItem>();
        return list;
    }

}
