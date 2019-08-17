package com.example.androidpractice06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etContext;
    private Button btnSubmit, btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etContext = (EditText)findViewById(R.id.etContext);
        this.btnSubmit = (Button)findViewById(R.id.btnSubmit);
        this.btnResult = (Button)findViewById(R.id.btnResult);

        this.btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(i);
            }
        });

    }

    public void submit(View view) {
        SubmitActivity sa = new SubmitActivity(this, this.etContext);
        sa.execute(this.etContext.getText().toString());
    }
}
