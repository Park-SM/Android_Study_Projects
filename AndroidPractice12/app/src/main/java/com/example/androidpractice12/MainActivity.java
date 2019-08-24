package com.example.androidpractice12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvContext;
    private ArrayList<Bitmap> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvContext = (ListView)findViewById(R.id.lvContext);
        this.dataList = new ArrayList<Bitmap>();

        RecevieService rs = new RecevieService(this, this.lvContext, this.dataList);
        rs.execute(3);
    }
}
