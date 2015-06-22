package com.android.oindexter.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.oindexter.R;
import com.android.oindexter.helpers.PrefUtils;
import com.android.oindexter.ui.widget.HUD;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegistrationActivity extends ActionBarActivity implements View.OnClickListener {

    private Toolbar toolbar;

    //  private int[] icons = {R.drawable.ic_keyboard_alt, R.drawable.ic_local_post_office, R.drawable.ic_phone, R.drawable.ic_room, R.drawable.ic_room, R.drawable.ic_room, R.drawable.ic_directions_car};
    private int[] icons = {R.drawable.ic_keyboard_alt, R.drawable.ic_local_post_office};

    // private String[] names = {"name", "email", "phone", "address", "city", "state", "vehicle_id"};
    private String[] names = {"name", "email"};
    //  private int[] inputTypes = {InputType.TYPE_CLASS_TEXT, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, InputType.TYPE_CLASS_PHONE, InputType.TYPE_CLASS_TEXT, InputType.TYPE_CLASS_TEXT, InputType.TYPE_CLASS_TEXT, InputType.TYPE_CLASS_TEXT};
    private int[] inputTypes = {InputType.TYPE_CLASS_TEXT, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS};

    private LinearLayout linerRegistration;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        linerRegistration = (LinearLayout) findViewById(R.id.linearRegistration);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        if (toolbar != null) {
            toolbar.setTitle("");
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("Registration");
            setSupportActionBar(toolbar);
        }

        setupFields();

    }

    private void setupFields() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < names.length; i++) {

            View view = getLayoutInflater().inflate(R.layout.item_reg, null);

            ImageView img = (ImageView) view.findViewById(R.id.imgRegistration);
            EditText ed = (EditText) view.findViewById(R.id.edRegistration);

            img.setImageResource(icons[i]);
            img.setColorFilter(Color.parseColor("#ffffff"));
            ed.setHint(names[i]);
            ed.setInputType(inputTypes[i]);
            linerRegistration.addView(view, params);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRegister:

                taketoRegistrationProcess();

                break;

        }

    }

    private void taketoRegistrationProcess() {


        if (checkValidation() == true) {

            ParseUser currentUser = new ParseUser();

            for (int i = 0; i < linerRegistration.getChildCount(); i++) {

                View v = linerRegistration.getChildAt(i);
                EditText ed = (EditText) v.findViewById(R.id.edRegistration);
                String value = ed.getText().toString();

                switch (i) {
                    case 0://name
                        currentUser.setUsername(value);
                        currentUser.setPassword("");
                        break;

                    case 1://email
                        currentUser.setEmail(value);
                        break;


                  /*  case 2://phone
                        currentUser.put("phone", value);
                        break;


                    case 3://address
                        currentUser.put("address", value);
                        break;


                    case 4://city
                        currentUser.put("city", value);
                        break;


                    case 5://state
                        currentUser.put("state", value);
                        break;


                    case 6://vehicle id
                        currentUser.put("vehicle_id", value);
                        break;*/
                }
            }


            final HUD dialog = new HUD(RegistrationActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
            dialog.title("Wait...");
            dialog.show();


            currentUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        dialog.dismiss();

                        PrefUtils.setLogin(RegistrationActivity.this, true);

                        Intent i = new Intent(RegistrationActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();

                    } else {

                        dialog.dismiss();

                        Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();


                    }


                }
            });

        }


    }

    private boolean checkValidation() {

        boolean isPassed = false;

        for (int i = 0; i < linerRegistration.getChildCount(); i++) {

            View v = linerRegistration.getChildAt(i);
            EditText ed = (EditText) v.findViewById(R.id.edRegistration);

            switch (i) {
                case 0:
                case 1:
                case 2:
                case 6:
                    if (ed.getText().toString().isEmpty()) {
                        isPassed = false;
                        Toast.makeText(RegistrationActivity.this, String.format("Enter %s", names[i]), Toast.LENGTH_SHORT).show();
                        return isPassed;

                    } else {

                        isPassed = true;
                    }

                    break;
            }
        }
        return isPassed;

    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
    }*/
}
