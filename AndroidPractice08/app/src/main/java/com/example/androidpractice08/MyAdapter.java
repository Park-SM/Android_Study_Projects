package com.example.androidpractice08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<ModelPSM> {
    public MyAdapter (Context context, ArrayList<ModelPSM> ModelPSM) {
        super(context, 0, ModelPSM);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, parent, false);
        }

        ModelPSM i = getItem(position);

        TextView tvNumber = (TextView)convertView.findViewById(R.id.tvNumber);
        TextView tvContext = (TextView)convertView.findViewById(R.id.tvContext);

        tvNumber.setText(i.getNo());
        tvContext.setText(i.getContext());

        return convertView;
    }
}
