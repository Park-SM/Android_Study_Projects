package com.example.androidpractice01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.tvTarget);
                tv.setText("Changed!");
            }
        });

        Button btnRevert = (Button) findViewById(R.id.btnRevert);
        btnRevert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.tvTarget);
                tv.setText("Hello, world!");
            }
        });
    }
}
