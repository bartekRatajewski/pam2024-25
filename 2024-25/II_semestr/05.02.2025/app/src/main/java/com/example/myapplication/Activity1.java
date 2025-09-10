package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Activity1 extends AppCompatActivity {

    private static final String CHANNEL_ID_HIGH = "high_priority_channel";
    private static final String CHANNEL_ID_LOW = "low_priority_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            NotificationChannel highPriorityChannel = new NotificationChannel(
                    CHANNEL_ID_HIGH, "High Priority Notifications", NotificationManager.IMPORTANCE_HIGH);
            highPriorityChannel.setDescription("Kanał dla ważnych powiadomień");

            NotificationChannel lowPriorityChannel = new NotificationChannel(
                    CHANNEL_ID_LOW, "Low Priority Notifications", NotificationManager.IMPORTANCE_LOW);
            lowPriorityChannel.setDescription("Kanał dla mniej ważnych powiadomień");

            notificationManager.createNotificationChannel(highPriorityChannel);
            notificationManager.createNotificationChannel(lowPriorityChannel);
        }

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> sendNotification(1));
        button2.setOnClickListener(v -> sendNotification(2));
    }

    @SuppressLint("MissingPermission")
    private void sendNotification(int type) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        if (type == 1) {
            intent = new Intent(this, Activity2.class);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(
                    this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );
            builder = new NotificationCompat.Builder(this, CHANNEL_ID_HIGH)
                    .setContentTitle("Wysoki priorytet")
                    .setContentText("Kliknij, aby przejść do Aktywności 2")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        } else {
            intent = new Intent(this, Activity3.class);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(
                    this, 1, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );
            builder = new NotificationCompat.Builder(this, CHANNEL_ID_LOW)
                    .setContentTitle("Niska priorytet")
                    .setContentText("Kliknij, aby przejść do Aktywności 3")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_LOW);
        }

        notificationManager.notify(type, builder.build());
    }
}
