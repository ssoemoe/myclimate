package com.bitcamp2018.myclimate.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Visibility;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bitcamp2018.myclimate.R;
import com.bitcamp2018.myclimate.controller.WeatherEvaluator;
import com.bitcamp2018.myclimate.model.Settings;
import com.bitcamp2018.myclimate.model.WeatherTable;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by shane on 4/7/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CheckBox checkBox = findViewById(R.id.notiCheck);
        checkBox.setChecked(true);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout layout = findViewById(R.id.notiLinear);
                if(!((CheckBox)view).isChecked()) {
                    for ( int i = 0; i < layout.getChildCount();  i++ ){
                        View child = layout.getChildAt(i);
                        child.setEnabled(false); // Or whatever you want to do with the view.
                    }
                }
                else {
                    for ( int i = 0; i < layout.getChildCount();  i++ ){
                        View child = layout.getChildAt(i);
                        child.setEnabled(true); // Or whatever you want to do with the view.
                    }
                }
            }
        });
        //setting the weather conditions
        Spinner weatherSpinner = findViewById(R.id.weatherSpinner);

        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Sunny");
        conditions.add("Rainy");
        conditions.add("Cloudy");
        conditions.add("Snowy");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conditions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weatherSpinner.setAdapter(arrayAdapter);

        //setting the weather conditions
        Spinner degreeSpinner = findViewById(R.id.degreeSpinner);

        ArrayList<String> degrees = new ArrayList<>();
        degrees.add("C (Celsius)");
        degrees.add("F (Fahrenheit)");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, degrees);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeSpinner.setAdapter(arrayAdapter);

        //click save preferences
        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref();
            }
        });

        // Test notifications button
        Button testNotify = findViewById(R.id.testNotificationsBtn);
        testNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testNotification();
            }
        });
    }


    public void savePref() {
        CheckBox notiCheck = findViewById(R.id.notiCheck);
        RadioGroup temps = findViewById(R.id.radioTemps);
        RadioButton radioButton = findViewById(temps.getCheckedRadioButtonId());
        EditText hour = findViewById(R.id.hours);
        Spinner conditionSpin = findViewById(R.id.weatherSpinner);
        Spinner degreeSpin = findViewById(R.id.degreeSpinner);

        //Determine temp_range
        Settings.temp_range = radioButton.getText().toString();
        System.out.println(Settings.temp_range);
        Settings.condition = conditionSpin.getSelectedItem().toString();
        System.out.println(Settings.condition);
        Settings.allowNoti = notiCheck.isChecked();
        System.out.println(Settings.allowNoti + "");

        if(!hour.getText().toString().equals("")) {
            double h = Double.parseDouble(hour.getText().toString());
            if(h < 1)
                Toast.makeText(this, "Hour must be greater than zero", Toast.LENGTH_LONG).show();
            else if(h > 24)
                Toast.makeText(this, "Hour cannot be greater than 24", Toast.LENGTH_LONG).show();
            else
                Settings.hours = h;

        }
        else {
            Toast.makeText(this, "Hours must be greater than zero" , Toast.LENGTH_LONG).show();
        }
        System.out.println(Settings.hours + "");

        if(degreeSpin.getSelectedItem().toString().equalsIgnoreCase("f (fahrenheit)")) {
            Settings.isCel = false;
        }
        else
            Settings.isCel = true;
    }

    public void testNotification() {
        int nID = 290;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        String nIDStr = "MCTestNotify";
        String textTitle = "MC Test Notification";
        String textContent = "This is a test of the Android Notification API...";
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, nIDStr)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setVisibility(Visibility.MODE_OUT)
                .setPriority(Notification.PRIORITY_MAX);

        notificationManager.notify(nID, mBuilder.build());
    }


}
