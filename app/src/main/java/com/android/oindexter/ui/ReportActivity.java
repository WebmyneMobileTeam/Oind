package com.android.oindexter.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.oindexter.R;

import java.util.ArrayList;
import java.util.Locale;

public class ReportActivity extends ActionBarActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private LinearLayout linearSpeakTag;
    private LinearLayout linearEnterTag;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("Choose");
            setSupportActionBar(toolbar);
        }

        linearEnterTag = (LinearLayout)findViewById(R.id.linearEnterCarTag);
        linearSpeakTag = (LinearLayout)findViewById(R.id.linearSpeakScan);
        linearSpeakTag.setOnClickListener(this);
        linearEnterTag.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.linearSpeakScan:

                processSpeak();

                break;

            case R.id.linearEnterCarTag:

                Intent iEnterTag = new Intent(ReportActivity.this,EnterTagActivity.class);
                startActivity(iEnterTag);

                break;

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                   if(!result.isEmpty() || result.size()>0){

                       String[] stockArr = new String[result.size()];
                       stockArr = result.toArray(stockArr);

                       Intent iSpeechResult = new Intent(ReportActivity.this,SpeechResultActivity.class);
                       iSpeechResult.putExtra("list",stockArr);
                       startActivity(iSpeechResult);


                   }


                    //txtSpeechInput.setText(result.get(0));
                }
                break;
            }


        }
    }

    private void processSpeak() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
