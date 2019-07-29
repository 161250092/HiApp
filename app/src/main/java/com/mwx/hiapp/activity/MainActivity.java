package com.mwx.hiapp.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mwx.hiapp.R;
import com.mwx.hiapp.util.HttpUtil;
import com.mwx.hiapp.util.Utility;
import com.mwx.hiapp.util.gson.Weather;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void forwardDict(View Resource){
        Intent intent = new Intent(this,DictActivity.class);
        startActivity(intent);
    }

    public void forwardService(View Resource){
        Intent intent = new Intent(this,BindActivity.class);
        startActivity(intent);
    }

    public void forwardWeather(View resource){
        Intent intent = new Intent(this,SharedPreferencesActivity.class);
        startActivity(intent);
    }

    public void localRequest(View resource){
            requestWeather();
    }

    public void requestWeather() {
        String ip = "172.22.217.238:80";
        String weatherUrl = "http://"+ip+"/weather/getWeather?w_id=mwx";

        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ( !responseText.equals("") ) {
                            Toast.makeText(MainActivity.this, responseText, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "本地获取天气信息失败1", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "本地获取天气信息失败2", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}
