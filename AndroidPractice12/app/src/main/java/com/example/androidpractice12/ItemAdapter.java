package com.example.androidpractice12;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Bitmap> {

    public ItemAdapter(Context context, ArrayList<Bitmap> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Bitmap tempBit = getItem(position);
        ImageView tempImageView = (ImageView)convertView.findViewById(R.id.ivItem);
        tempImageView.setImageBitmap(tempBit);

        return convertView;
    }
}
