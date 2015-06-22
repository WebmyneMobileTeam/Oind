package com.android.oindexter.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.oindexter.R;
import com.androidanimations.library.Techniques;
import com.androidanimations.library.Webmyne;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView imgCall;
    private ImageView imgCar;

    private LinearLayout linearCall;
    private LinearLayout linearReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("Home");
            setSupportActionBar(toolbar);
        }




        imgCall = (ImageView) findViewById(R.id.imgCall);
        imgCar = (ImageView) findViewById(R.id.imgCar);

        Webmyne.get(Techniques.Shake).duration(900).startOn(imgCall);
        // Webmyne.get(Techniques.Flash).duration(500).startOn(imgCar);

        linearCall = (LinearLayout) findViewById(R.id.linearCall911);
        linearReport = (LinearLayout) findViewById(R.id.linearReport);

        linearCall.setOnClickListener(this);
        linearReport.setOnClickListener(this);


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

        switch (v.getId()) {
            case R.id.linearCall911:

                //Intent dialIntent = new Intent(Intent.CALL_ACTION, Uri.parse("tel:911"));
                //startActivity(dialIntent);

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:911"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;

            case R.id.linearReport:

                Intent i = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(i);

                break;
        }


    }
}
