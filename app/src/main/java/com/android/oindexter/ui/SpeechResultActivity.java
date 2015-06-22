package com.android.oindexter.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.oindexter.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SpeechResultActivity extends ActionBarActivity {

    ListView listSpeechResult;
    private Toolbar toolbar;
    private String[] receivedArr;
    private ArrayList<String> finalArr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_result);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("Speech Result Page");
            setSupportActionBar(toolbar);
        }

        receivedArr = getIntent().getStringArrayExtra("list");

        listSpeechResult = (ListView)findViewById(R.id.listSpeechResult);
        finalArr = new ArrayList<>(Arrays.asList(receivedArr));

        ArrayAdapter adapter = new ArrayAdapter(SpeechResultActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,finalArr);
        listSpeechResult.setAdapter(adapter);


    }

}
