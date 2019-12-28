package com.smparkworld.androidpractice14.dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smparkworld.androidpractice14.R;

import java.util.ArrayList;

public class MemoAdpater extends ArrayAdapter<Memo> {

    public MemoAdpater(Context context, ArrayList<Memo> modelList) {
        super(context, 0, modelList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false);
        }
        Memo model = getItem(position);

        ((TextView)convertView.findViewById(R.id.tvName)).setText(model.getName());
        ((TextView)convertView.findViewById(R.id.tvAddress)).setText(model.getAddress());
        ((TextView)convertView.findViewById(R.id.tvMemo)).setText(model.getMemo());


        /*
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(model.getName());

        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        tvAddress.setText(model.getAddress());

        TextView tvMemo = convertView.findViewById(R.id.tvMemo);
        tvMemo.setText(model.getMemo());
        */

        return convertView;
    }
}
