package com.example.androidpractice10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitActivity extends AppCompatActivity {

    private EditText etName, etContext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);


        this.etName = (EditText)findViewById(R.id.etName);
        this.etContext = (EditText)findViewById(R.id.etContext);
        this.btnBack = (Button)findViewById(R.id.btnBack);

        this.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void submit(View view) {
        SubmitService ss = new SubmitService(this, this.etName, this.etContext);
        ss.execute(this.etName.getText().toString(), this.etContext.getText().toString());
    }
}
