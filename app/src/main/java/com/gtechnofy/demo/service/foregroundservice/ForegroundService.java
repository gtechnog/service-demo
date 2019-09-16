package com.gtechnofy.demo.service.foregroundservice;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.gtechnofy.demo.service.App;


public class ForegroundService extends Service {

    public static final String TAG = ForegroundService.class.getSimpleName();
    private boolean running = false;

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        prepareNotification();
        running = true;
        Thread thread = new Thread(getRunnable());
        thread.start();
        return START_NOT_STICKY;
    }

    private void prepareNotification() {

        Intent intent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, App.NOTIFICATION_CHANNEL_ID).setContentTitle("Foreground Service Demo")
                .setContentText(" hi this is playing music")
                .setSmallIcon(android.R.drawable.ic_delete)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(12, notification);
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                while (running) {
                    Log.d(TAG, "run: executing in background, thread id: " + Thread.currentThread().getId() + " , main thread id: " + getMainLooper().getThread().getId());
                    try {
                        Log.d(TAG, "run: sleeping: thread id: " + Thread.currentThread().getId());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        running = false;
        super.onDestroy();
    }
}
