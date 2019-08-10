package com.example.androidpractice03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIntent = (Button)findViewById(R.id.btnIntent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etPhone = (EditText)findViewById(R.id.etPhone);
                EditText etNumber = (EditText)findViewById(R.id.etNumber);

                Intent i = new Intent(MainActivity.this, TestActivity.class);
                i.putExtra("PhoneName", etPhone.getText().toString());
                i.putExtra("PhoneNumber", etNumber.getText().toString());
                startActivity(i);
            }
        });
    }
}
