package com.smparkworld.androidpractice17;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private int notificationId;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_result);

        Bundle extra = getIntent().getExtras();
        if (extra == null) {
            this.notificationId = 0;
            this.msg = "Failed to receive data of extra";
        } else {
            this.notificationId = extra.getInt("notificationId");
            this.msg = extra.getString("msg");
        }

        ((TextView)findViewById(R.id.tvResultText)).setText(msg);
        ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).cancel(this.notificationId);
    }
}
