package com.mwx.hiapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("Intent onStart");
        while(System.currentTimeMillis()<endTime){
            synchronized (this){
                try{
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("耗时任务完成");
        return START_STICKY;
    }


}
