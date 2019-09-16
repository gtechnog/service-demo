package com.gtechnofy.demo.service.intentservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gtechnofy.demo.service.R;

import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK1;
import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK2;
import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK3;
import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK4;
import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK5;
import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK6;
import static com.gtechnofy.demo.service.intentservice.MyIntentService.ACTION_TASK_COMPLETE_STATUS;

public class IntentServiceActivity extends AppCompatActivity {

    private BroadcastReceiver mReceiver;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        textView = findViewById(R.id.textView);
    }

    public void submitTask1(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_TASK1);
        startService(intent);
    }
    public void submitTask2(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_TASK2);
        startService(intent);
    }
    public void submitTask3(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_TASK3);
        startService(intent);
    }
    public void submitTask4(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_TASK4);
        startService(intent);
    }
    public void submitTask5(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_TASK5);
        startService(intent);
    }
    public void submitTask6(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_TASK6);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(getReceiver(), new IntentFilter(ACTION_TASK_COMPLETE_STATUS));
    }

    private BroadcastReceiver getReceiver() {
        if (mReceiver == null) {
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    textView.setText(intent.getStringExtra("DATA"));
                }
            };
        }
        return mReceiver;
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(getReceiver());
    }
}
