package com.smparkworld.androidpractice16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvMainMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvMainMessage = findViewById(R.id.tvMainMessage);

        Toolbar tb = findViewById(R.id.tbToolbar);
        setSupportActionBar(tb);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.omSearch :
                this.tvMainMessage.setText("Selected Search menu");
                return true;

            case R.id.omRefresh :
                this.tvMainMessage.setText("Selected Refresh menu");
                return true;

            case R.id.omAccount :
                this.tvMainMessage.setText("Selected Account menu");
                return true;

            case R.id.omTest1 :
                this.tvMainMessage.setText("Selected Test1 menu");
                return true;

            case R.id.omTest2 :
                this.tvMainMessage.setText("Selected Test2 menu");
                return true;

            case R.id.omTest3 :
                this.tvMainMessage.setText("Selected Test3 menu");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
