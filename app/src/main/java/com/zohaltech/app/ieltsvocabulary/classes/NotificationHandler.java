package com.zohaltech.app.ieltsvocabulary.classes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.data.SystemSettings;
import com.zohaltech.app.ieltsvocabulary.entities.SystemSetting;

public class NotificationHandler {

    private static NotificationManager notificationManager;

    static {
        notificationManager = (NotificationManager) App.context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void displayUpdateNotification(Context context, int notificationId, String title, String text) {
        notificationManager.notify(notificationId, getUpdateNotification(context, title, text));
    }

    private static Notification getUpdateNotification(Context context, String title, String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notification_update)
                        .setLargeIcon(BitmapFactory.decodeResource(App.context.getResources(), R.mipmap.ic_launcher))
                        .setContentTitle(title)
                        .setContentText(text)
                        .setShowWhen(true)
                        .setOngoing(false)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setColor(App.context.getResources().getColor(R.color.primary))
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(Intent.ACTION_VIEW);
        resultIntent.setData(Uri.parse(App.marketUri));
        resultIntent.setPackage(App.marketPackage);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 3, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        SystemSetting setting = SystemSettings.getCurrentSettings();
        if (setting.getRingingToneUri() == null) {
            builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);
        }
        Notification notification = builder.build();
        if (setting.getRingingToneUri() != null) {
            notification.sound = Uri.parse(setting.getRingingToneUri());
        }

        return builder.build();
    }
}