package com.example.androidpractice10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvResult;
    private ArrayList<ItemModel> itemList;
    private Button btnMakeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.itemList = new ArrayList<ItemModel>();
        this.lvResult = (ListView)findViewById(R.id.lvResult);
        this.btnMakeMsg = (Button)findViewById(R.id.btnMakeMsg);

        this.btnMakeMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.itemList.clear();
        ReceiveService ra = new ReceiveService(this, this.itemList, this.lvResult);
        ra.execute();
    }

    public void refresh(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
