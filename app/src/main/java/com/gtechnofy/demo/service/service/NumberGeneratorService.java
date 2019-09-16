package com.gtechnofy.demo.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class NumberGeneratorService extends Service {

    private static final String TAG = NumberGeneratorService.class.getSimpleName();
    public static final String NUMBER_GENERATE_UPDATE = "new_number_generate_update";
    public static final int NUMBER_DISPLAY_INTERVAL = 2000;
    public static final int NUMBER_DISPLAY_STOP = 500000000;
    private int numberToDisplay;
    private Thread backgroundThread;
    private boolean shouldStopRunningThread = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
        numberToDisplay = NUMBER_DISPLAY_INTERVAL;
        backgroundThread = new Thread(getServiceRunnable());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        backgroundThread.start();
        return START_NOT_STICKY;
    }

    private Runnable getServiceRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int value = 0;
                while ( !shouldStopRunningThread && value != NUMBER_DISPLAY_STOP) {
                    Log.d(TAG, "onStartCommand: value: " + value++ + " on thread: " +
                            Thread.currentThread().getId() + " Main thread Id: " +
                            getMainLooper().getThread().getId());
                    if (value == numberToDisplay) {
                        LocalBroadcastManager.getInstance(NumberGeneratorService.this).sendBroadcast(getIntentForUpdate(value));
                        numberToDisplay += NUMBER_DISPLAY_INTERVAL;
                    }
                }
                stopSelf();
            }
        };
    }

    private Intent getIntentForUpdate(int value) {
        Intent intent = new Intent(NUMBER_GENERATE_UPDATE);
        intent.putExtra("NUMBER", value);
        return intent;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        shouldStopRunningThread = true;
        super.onDestroy();
    }
}
