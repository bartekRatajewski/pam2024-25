package edu.zsk.ratajewski;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;

public class LoggedInActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "2137";
    private static final String CHANNEL_NAME = "Zadanie podsumowujące";
    private String activeFragment = "first";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentHolder, new FirstFragment()).commit();

        Button changeFragmentButton = findViewById(R.id.changeFragmentButton);
        Button showNotificationButton = findViewById(R.id.showNotificationButton);

        changeFragmentButton.setOnClickListener(v -> changeFragment());
        showNotificationButton.setOnClickListener(v -> sendNotification());
    }

    private void changeFragment() {
        if ("first".equals(activeFragment)) {
            activeFragment = "second";
            fragmentManager.beginTransaction().replace(R.id.fragmentHolder, new SecondFragment()).commit();
        } else {
            activeFragment = "first";
            fragmentManager.beginTransaction().replace(R.id.fragmentHolder, new FirstFragment()).commit();
        }
    }
    @SuppressLint("MissingPermission")
    private void sendNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Powiadomienie")
                .setContentText("Wiadomość powiadomienia")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.notify(1, builder.build());
    }

    public void openDialog() {
        AppDialogFragment dialog = new AppDialogFragment();
        dialog.setCancelable(true);
        dialog.show(fragmentManager, "AppDialog");
    }
}
