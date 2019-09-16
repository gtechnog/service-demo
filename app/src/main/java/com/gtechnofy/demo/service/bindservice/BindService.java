package com.gtechnofy.demo.service.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {
    private static final String TAG = BindService.class.getSimpleName();
    private boolean running;
    private IBinder mBinder = new MyServiceBinder();

    private int random;

    public BindService() {
    }

    class MyServiceBinder extends Binder {
        BindService getService() {
            return BindService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        running = true;
        Thread thread = new Thread(getRunnable());
        thread.start();
        return START_STICKY;
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d(TAG, "run: Thread Interrupted");
                    }
                    if (running) {
                        random = (int )( Math.random() * 1000 )  % 100;
                        Log.d(TAG, "run: random: " + random);
                    }
                }
            }
        };
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind() called with: intent = [" + intent + "]");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() called with: intent = [" + intent + "]");
        return false;
    }

    @Override
    public void onDestroy() {
        running = false;
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }

    public int getRandom() {
        return random;
    }
}
