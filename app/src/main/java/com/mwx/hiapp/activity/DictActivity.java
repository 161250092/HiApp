package com.mwx.hiapp.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.mwx.hiapp.R;
import com.mwx.hiapp.Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictActivity extends Activity {
    ContentResolver contentResolver;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.dict_invoker);
        contentResolver  = getContentResolver();
    }

    public void addNewWord(View resource){
        String word = ((EditText)findViewById(R.id.input_word)).getText().toString();
        String detail =  ((EditText)findViewById(R.id.input_details)).getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Words.Word.WORD,word);
        contentValues.put(Words.Word.DETAIL,detail);
        contentResolver.insert(Words.Word.DICT_CONTENT_URI,contentValues);
        Toast.makeText(this,"添加生词成功",Toast.LENGTH_SHORT).show();
    }

    public void  checkDetails(View resource){

        String key = ((EditText)findViewById(R.id.input_check)).getText().toString();
        Cursor cursor = contentResolver.query(Words.Word.DICT_CONTENT_URI,null,"word like ? or detail like ?",
                new String[]{"%"+key+"%","%"+key+"%"},null);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",convertCursorToList(cursor));
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private ArrayList<Map<String,String>> convertCursorToList(Cursor cursor){
        ArrayList<Map<String,String>> result = new ArrayList<>();
        while(cursor.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put(Words.Word.WORD,cursor.getString(1));
            map.put(Words.Word.DETAIL,cursor.getString(2));
            result.add(map);
        }
        return result;
    }

}
