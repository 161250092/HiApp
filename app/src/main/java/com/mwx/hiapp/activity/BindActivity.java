package com.mwx.hiapp.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mwx.hiapp.R;
import com.mwx.hiapp.service.BindService;
import com.mwx.hiapp.service.MyIntentService;
import com.mwx.hiapp.service.MyService;


public class BindActivity extends Activity {
    BindService.MyBinder binder;
    Button bind,unbind,getServiceState;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("---service connected-----");
            binder = (BindService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("------service disconnected-----");
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_activity);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);

        final Intent intent = new Intent(this,BindService.class);
        intent.setAction("com.example.game.BIND_SERVICE");

        getServiceState = (Button) findViewById(R.id.getCount);
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(intent,connection, Service.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
            }
        });

        getServiceState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BindActivity.this,
                        "service count值为:"+binder.getCount(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void startCommonService(View resource){
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
    }
    public void startIntentService(View resource){
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }



}
