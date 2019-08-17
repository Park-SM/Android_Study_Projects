package com.example.androidpractice07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etNumber = (EditText)findViewById(R.id.etNumber);
    }

    public void submit(View view) {
        Intent i = new Intent(MainActivity.this, SubActivity.class);
        //i.putExtra("number", Integer.parseInt(this.etNumber.getText().toString()));
        startActivity(i);
    }
}
