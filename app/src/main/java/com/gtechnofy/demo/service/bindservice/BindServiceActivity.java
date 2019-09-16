package com.gtechnofy.demo.service.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gtechnofy.demo.service.R;

public class BindServiceActivity extends AppCompatActivity {

    private static final String TAG = BindServiceActivity.class.getSimpleName();
    private Button buttonBind;
    private Button buttonUnbind;
    private ServiceConnection serviceConnection;
    private boolean isServiceBound = false;
    private TextView textView;
    private BindService mService;
    private Intent mServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        textView = findViewById(R.id.data);
    }

    public void startMyService(View view) {
        Log.d(TAG, "startMyService() called with: view = [" + view + "]");
        startService(getServiceIntent());
    }

    private Intent getServiceIntent() {
        if (mServiceIntent == null) {
            mServiceIntent = new Intent(this, BindService.class);
        }
        return mServiceIntent;
    }

    public void bindMyService(View view) {
        Log.d(TAG, "bindMyService() called with: view = [" + view + "]");
        Intent intent = getServiceIntent();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
                isServiceBound = true;
                mService = ((BindService.MyServiceBinder)service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected() called with: name = [" + name + "]");
                isServiceBound = false;

            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }


    public void unbindMyService(View view) {
        Log.d(TAG, "unbindMyService() called with: view = [" + view + "]");
        if (isServiceBound) {
            unbindService(serviceConnection);
        } else {
            showText(" Service Not Bound");
        }
    }


    public void stopMyService(View view) {
        Log.d(TAG, "stopMyService() called with: view = [" + view + "]");
        stopService(getServiceIntent());

    }

    public void getRandomNumber(View view) {
        Log.d(TAG, "getRandomNumber() called with: view = [" + view + "]");
        if (isServiceBound) {
            showText("" + mService.getRandom());
        } else {
            showText(" Service Not Bound");
        }
    }

    private void showText(String s) {
        textView.setText(s);
    }
}
