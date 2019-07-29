package com.mwx.hiapp.activity;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mwx.hiapp.R;

import java.util.List;
import java.util.Map;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        ListView listView = (ListView)findViewById(R.id.resultList);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        List<Map<String,String>> list = (List<Map<String,String>>)data.getSerializable("data");
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.word_list,new String[]{"word","detail"},new int[]{R.id._word,R.id._detail});
        listView.setAdapter(adapter);

    }



}
