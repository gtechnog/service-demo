package com.gtechnofy.demo.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static final String ACTION_TASK1 = "com.gtechnofy.demo.service.intentservice.action.task1";
    public static final String ACTION_TASK2 = "com.gtechnofy.demo.service.intentservice.action.task2";
    public static final String ACTION_TASK3 = "com.gtechnofy.demo.service.intentservice.action.task3";
    public static final String ACTION_TASK4 = "com.gtechnofy.demo.service.intentservice.action.task4";
    public static final String ACTION_TASK5 = "com.gtechnofy.demo.service.intentservice.action.task5";
    public static final String ACTION_TASK6 = "com.gtechnofy.demo.service.intentservice.action.task6";

    public static final String ACTION_TASK_COMPLETE_STATUS = "com.gtechnofy.demo.service.intentservice.action.task.status";
    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(ACTION_TASK1)) {
                handleActionTask1();
            } else if (intent.getAction().equals(ACTION_TASK2)) {
                handleActionTask2();
            } else if (intent.getAction().equals(ACTION_TASK3)) {
                handleActionTask3();
            } else if (intent.getAction().equals(ACTION_TASK4)) {
                handleActionTask4();
            } else if (intent.getAction().equals(ACTION_TASK5)) {
                handleActionTask5();
            } else if (intent.getAction().equals(ACTION_TASK6)) {
                handleActionTask6();
            }
        }
    }

    private void handleActionTask1() {
        Log.d(TAG, "handleActionTask1() called");
        sleepThread();
        updateUIwithTaskStatus(getUpdateIntent().putExtra("DATA", "WOW TASK1 DONE"));
    }

    private void handleActionTask2() {
        Log.d(TAG, "handleActionTask2() called");
        sleepThread();
        updateUIwithTaskStatus(getUpdateIntent().putExtra("DATA", "TASK 2 AWESOME"));
    }

    private void handleActionTask3() {
        Log.d(TAG, "handleActionTask3() called");
        sleepThread();
        updateUIwithTaskStatus(getUpdateIntent().putExtra("DATA", "COOL TASK 3 COMPLETED"));
    }
    private void handleActionTask4() {
        Log.d(TAG, "handleActionTask4() called");
        sleepThread();
        updateUIwithTaskStatus(getUpdateIntent().putExtra("DATA", "AWESOME TASK 4 COOL"));
    }
    private void handleActionTask5() {
        Log.d(TAG, "handleActionTask5() called");
        sleepThread();
        updateUIwithTaskStatus(getUpdateIntent().putExtra("DATA", "GREAT TASK 5 ALSO DONE"));
    }
    private void handleActionTask6() {
        Log.d(TAG, "handleActionTask6() called");
        sleepThread();
        updateUIwithTaskStatus(getUpdateIntent().putExtra("DATA", "GREAT !! TASK 6 ALSO COOL"));
    }

    private Intent getUpdateIntent() {
        return new Intent(ACTION_TASK_COMPLETE_STATUS);
    }

    private void updateUIwithTaskStatus(Intent intent) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sleepThread() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
}
