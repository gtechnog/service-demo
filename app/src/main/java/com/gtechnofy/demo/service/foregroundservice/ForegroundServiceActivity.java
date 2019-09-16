package com.gtechnofy.demo.service.foregroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gtechnofy.demo.service.R;

public class ForegroundServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foreground_service_actitity);
    }

    public void startForegroundService(View view) {
        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
    }

    public void stopForegroundService(View view) {
        Intent intent = new Intent(this, ForegroundService.class);
        stopService(intent);
    }
}
