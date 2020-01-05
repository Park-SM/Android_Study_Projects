package com.smparkworld.androidpractice15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends ArrayAdapter<MemoModel> {

    public MainAdapter(Context context, ArrayList<MemoModel> modelList) {
        super(context, 0, modelList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_memo, parent, false);

        MemoModel model = getItem(position);

        ((TextView)convertView.findViewById(R.id.tvMemoItemWriter)).setText(model.getWriter());
        ((TextView)convertView.findViewById(R.id.tvMemoItemTime)).setText(model.getTime());
        ((TextView)convertView.findViewById(R.id.tvMemoItemContext)).setText(model.getContext());

        return convertView;
    }

}
