package com.smparkworld.androidpractice15;

public class MemoModel {

    private String writer;
    private String context;
    private String time;

    public MemoModel(String writer, String time, String context) {
        this.writer = writer;
        this.time = time;
        this.context = context;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
