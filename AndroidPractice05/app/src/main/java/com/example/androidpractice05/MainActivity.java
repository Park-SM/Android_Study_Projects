package com.example.androidpractice05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogStatus;
    private TextView tvRoleStatus;
    private EditText etName;
    private EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvLogStatus = (TextView)findViewById(R.id.tvLogStatus);
        this.tvRoleStatus = (TextView)findViewById(R.id.tvRoleStatus);

        this.etName = (EditText)findViewById(R.id.etName);
        this.etPass = (EditText)findViewById(R.id.etPass);
    }

    public void login(View view) {
        SignInActivity sa = new SignInActivity(this, this.tvLogStatus, this.tvRoleStatus);
        sa.execute(this.etName.getText().toString(), this.etPass.getText().toString());
    }
}
