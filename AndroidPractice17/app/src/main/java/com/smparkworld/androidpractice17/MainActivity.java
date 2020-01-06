package com.smparkworld.androidpractice17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final int NOTIFICATION_ID = 100;
    private final String NOTIFICATION_CHANNEL_ID = "1000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnMakeAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = ((EditText)findViewById(R.id.etAlarmContext)).getText().toString();
                showAlarm(msg);
            }
        });
    }

    private void showAlarm(String msg) {
        Intent alarmIntent = new Intent(this, ResultActivity.class);
        alarmIntent.putExtra("notificationId", NOTIFICATION_ID);
        alarmIntent.putExtra("msg", msg);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT );

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Alarm example")
                .setContentText(msg)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX);

        // Need chanel for upper OREO API 26 version
        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); // when using mipmap, bring about error at upper Oreo version
            String channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // Registration notification chanel to system.
            assert nm != null;
            nm.createNotificationChannel(channel);

        } else builder.setSmallIcon(R.mipmap.ic_launcher);

        assert nm != null;
        nm.notify(NOTIFICATION_ID, builder.build());
    }
}
