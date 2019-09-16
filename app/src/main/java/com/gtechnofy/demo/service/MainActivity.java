package com.gtechnofy.demo.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gtechnofy.demo.service.bindservice.BindServiceActivity;
import com.gtechnofy.demo.service.intentservice.IntentServiceActivity;
import com.gtechnofy.demo.service.foregroundservice.ForegroundServiceActivity;
import com.gtechnofy.demo.service.service.ServiceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.service).setOnClickListener(getOnclickListener());
        findViewById(R.id.bind_service).setOnClickListener(getOnclickListener());
        findViewById(R.id.intent_service).setOnClickListener(getOnclickListener());
        findViewById(R.id.foreground_service).setOnClickListener(getOnclickListener());
        findViewById(R.id.job_service).setOnClickListener(getOnclickListener());
        findViewById(R.id.job_intent_service).setOnClickListener(getOnclickListener());
        findViewById(R.id.remote_bind_service).setOnClickListener(getOnclickListener());
    }

    private View.OnClickListener getOnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performClick(v);
            }
        };
    }

    private void performClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.service:
                intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.bind_service:
                intent = new Intent(this, BindServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.intent_service:
                intent = new Intent(this, IntentServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.foreground_service:
                intent = new Intent(this, ForegroundServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.job_service:
                break;
            case R.id.job_intent_service:
                break;
            case R.id.remote_bind_service:
                break;
        }
    }
}
