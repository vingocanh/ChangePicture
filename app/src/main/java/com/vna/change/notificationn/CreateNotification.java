package com.vna.change.notificationn;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.vna.change.activity.MainActivity;

public class CreateNotification {

    public static void create(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String name = "Change Picture";
            NotificationChannel channel = new NotificationChannel(MainActivity.CHANEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
