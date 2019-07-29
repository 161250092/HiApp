package com.mwx.hiapp.service;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("MyIntentService onStart");
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
    }
}
