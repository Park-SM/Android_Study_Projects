package com.smparkworld.androidpractice14.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.smparkworld.androidpractice14.R;
import com.smparkworld.androidpractice14.dao.MemoAsynTask;
import com.smparkworld.androidpractice14.dto.MemoAdpater;

public class MainActivity extends AppCompatActivity {

    private ListView lvMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvMainList = findViewById(R.id.lvMainList);
        new MemoAsynTask(this, this.lvMainList).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "readList");

        Button btnGoToWrite = findViewById(R.id.btnGoToWrite);
        btnGoToWrite.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, WriteActivity.class);
                    startActivity(i);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        new MemoAsynTask(this, this.lvMainList).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "readList");
    }
}
