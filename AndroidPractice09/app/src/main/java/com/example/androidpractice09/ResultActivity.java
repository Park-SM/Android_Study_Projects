package com.example.androidpractice09;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private ListView lvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.lvResult = (ListView)findViewById(R.id.lvResult);

        ReceiveService rs = new ReceiveService(this, ContextItem.getList(), this.lvResult);
        rs.execute();
    }
}
