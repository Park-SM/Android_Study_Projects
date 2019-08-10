package com.example.androidpractice03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent i = getIntent();

        TextView tvPhone = (TextView)findViewById(R.id.tvPhone);
        tvPhone.setText(i.getStringExtra("PhoneName"));

        TextView tvNumber = (TextView)findViewById(R.id.tvNumber);
        tvNumber.setText(i.getStringExtra("PhoneNumber"));
    }
}
