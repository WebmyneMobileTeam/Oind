package com.android.oindexter.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.oindexter.R;
import com.android.oindexter.helpers.PrefUtils;
import com.parse.ParseUser;


public class LauncherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new CountDownTimer(2500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                try{


                    if(PrefUtils.checkLogin(LauncherActivity.this) == false){

                        Intent iHome = new Intent(LauncherActivity.this,RegistrationActivity.class);
                        startActivity(iHome);
                        finish();
                    }else{

                        Intent iHome = new Intent(LauncherActivity.this,HomeActivity.class);
                        startActivity(iHome);
                        finish();

                    }


                }catch(Exception e) {

                }



            }
        }.start();

    }
}
