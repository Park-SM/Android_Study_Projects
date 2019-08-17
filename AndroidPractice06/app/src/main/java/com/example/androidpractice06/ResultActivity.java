package com.example.androidpractice06;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ll = findViewById(R.id.llResult);

        ReceiveActivity ra = new ReceiveActivity(ll, this);
        ra.execute();
    }

}
