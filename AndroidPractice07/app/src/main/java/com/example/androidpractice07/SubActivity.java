package com.example.androidpractice07;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //Intent inte = getIntent();
        //int recevNumber = inte.getIntExtra("number", 0);
        int recevNumber = 10;

        LinearLayout llContext = (LinearLayout)findViewById(R.id.llContext);
        LayoutInflater li = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View itemView;
        for (int i = 0; i < recevNumber; i++) {
            itemView = li.inflate(R.layout.activity_item, llContext, true);

            //TextView tvNumber = (TextView)itemView.findViewById(R.id.tvNumber);
            //TextView tvContext = (TextView)itemView.findViewById(R.id.tvContext);
        }
    }
}
