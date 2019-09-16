package com.gtechnofy.demo.service.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gtechnofy.demo.service.R;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = ServiceActivity.class.getSimpleName();
    private BroadcastReceiver broadcastReceiver;
    private TextView textView;
    private Button stopServiceButton;
    private Button startServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        textView = findViewById(R.id.unbind_service);
        startServiceButton = findViewById(R.id.start_service);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNumberGeneratorService();
            }
        });

        stopServiceButton = findViewById(R.id.stop_service);
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopNumberGeneratorService();
            }
        });
        stopServiceButton.setEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
                textView.setText(""+intent.getIntExtra("NUMBER", 0));
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(NumberGeneratorService.NUMBER_GENERATE_UPDATE));
    }

    private void stopNumberGeneratorService() {
        Intent intent = new Intent(this, NumberGeneratorService.class);
        stopService(intent);
        startServiceButton.setEnabled(true);
        stopServiceButton.setEnabled(false);
    }

    private void startNumberGeneratorService() {
        Intent intent = new Intent(this, NumberGeneratorService.class);
        startService(intent);
        startServiceButton.setEnabled(false);
        stopServiceButton.setEnabled(true);
    }
}
