package com.example.myapplication;

import android.os.Bundle;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity1 extends AppCompatActivity {

    private static final String CHANNEL_ID_HIGH = "high_priority_channel";
    private static final String CHANNEL_ID_LOW = "low_priority_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel highPriorityChannel = new NotificationChannel(
                    CHANNEL_ID_HIGH,
                    "High Priority Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationChannel lowPriorityChannel = new NotificationChannel(
                    CHANNEL_ID_LOW,
                    "Low Priority Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(highPriorityChannel);
            notificationManager.createNotificationChannel(lowPriorityChannel);
        }

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> sendNotification(1));
        button2.setOnClickListener(v -> sendNotification(2));
    }
    private void sendNotification(int type) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent;
        Notification.Builder builder;

        if (type == 1) {
            intent = new Intent(this, Activity2.class);
            builder = new Notification.Builder(this, CHANNEL_ID_HIGH)
                    .setContentTitle("High Priority")
                    .setContentText("Kliknij, aby przejść do Aktywności 2")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                    .setPriority(Notification.PRIORITY_HIGH);
        } else {
            intent = new Intent(this, Activity3.class);
            builder = new Notification.Builder(this, CHANNEL_ID_LOW)
                    .setContentTitle("Low Priority")
                    .setContentText("Kliknij, aby przejść do Aktywności 3")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                    .setPriority(Notification.PRIORITY_LOW);
        }

        notificationManager.notify(type, builder.build());
    }
}