package com.example.androidpractice10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<ItemModel> {

    private Context context;

    public ItemAdapter(Context context, ArrayList<ItemModel> list) {
        super(context, 0, list);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, parent, false);
        }
        ItemModel im = getItem(position);

        TextView tvName = (TextView)convertView.findViewById(R.id.tvID);
        TextView tvContext = (TextView)convertView.findViewById(R.id.tvContext);
        Button btnDelete = (Button)convertView.findViewById(R.id.btnDelete);

        btnDelete.setTag(im.getDataNo());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteService ds = new DeleteService(context);
                ds.execute(view.getTag().toString());
            }
        });
        tvName.setText(im.getDataName());
        tvContext.setText(im.getDataContext());

        return convertView;
    }

}
