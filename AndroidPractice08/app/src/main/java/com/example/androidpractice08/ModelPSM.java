package com.example.androidpractice08;

import java.util.ArrayList;

public class ModelPSM {
    private String no;
    private String context;

    public ModelPSM (String no, String context) {
        this.no = no;
        this.context = context;
    }

    public String getNo() {
        return this.no;
    }

    public String getContext() {
        return this.context;
    }

    public static ArrayList<ModelPSM> getItemData() {
        ArrayList<ModelPSM> data = new ArrayList<ModelPSM>();
        data.add(new ModelPSM("1: ", "ParkSM"));
        data.add(new ModelPSM("2: ", "ParkSangMin"));
        data.add(new ModelPSM("3: ", "ParkWorld"));
        data.add(new ModelPSM("4: ", "http://smparkworld.com"));
        data.add(new ModelPSM("5: ", "ParkLand"));
        data.add(new ModelPSM("6: ", "ParkLand"));
        data.add(new ModelPSM("7: ", "Parkworld"));
        data.add(new ModelPSM("8: ", "ParkLand"));
        data.add(new ModelPSM("9: ", "ParkTest"));
        data.add(new ModelPSM("10: ", "ParkLand"));
        data.add(new ModelPSM("11: ", "ParkSM"));
        data.add(new ModelPSM("12: ", "ParkSangMin"));
        data.add(new ModelPSM("13: ", "ParkWorld"));

        return data;
    }
}
