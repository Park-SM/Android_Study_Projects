package com.example.androidpractice09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<ContextItem> {

    public ItemAdapter(Context context, ArrayList<ContextItem> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ContextItem tempItem = getItem(position);

        TextView tvNumber = (TextView)convertView.findViewById(R.id.tvNumber);
        TextView tvContext = (TextView)convertView.findViewById(R.id.tvContext);

        tvNumber.setText(tempItem.getNo());
        tvContext.setText(tempItem.getContext());

        return convertView;
    }
}
