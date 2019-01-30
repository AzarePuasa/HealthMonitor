package com.azare.app.healthmonitor.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class BPMorningAlarm extends BroadcastReceiver {

    //the method will be fired when the alarm is triggerred
    @Override
    public void onReceive(Context context, Intent intent) {

        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d(HMUtils.LOGTAG, "Morning Alarm just fired");

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default","Default Channel"
                    ,NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");

            notificationmanager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context,
                "default");

        builder.setContentTitle("Morning Alarm");
        builder.setContentText("Reminder to record your morning blood pressure reading");
        builder.setSmallIcon(android.R.drawable.ic_popup_reminder);

        Notification notif = builder.build();

        notificationmanager.notify(0,notif);
    }
}
