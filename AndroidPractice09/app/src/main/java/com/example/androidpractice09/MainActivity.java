package com.example.androidpractice09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private EditText etContext;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etContext = (EditText) findViewById(R.id.etContext);
        this.btnResult = (Button) findViewById(R.id.btnResult);
        this.btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(i);
            }
        });
    }

    public void submit(View view) {
        SubmitService ss = new SubmitService(this, this.etContext);
        ss.execute(this.etContext.getText().toString());
    }
}
