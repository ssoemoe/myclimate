package com.bitcamp2018.myclimate.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.bitcamp2018.myclimate.R;

import java.util.ArrayList;

/**
 * Created by shane on 4/7/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    String nID = "MCTestID";
    int nIDint = 290;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //setting the weather conditions
        Spinner spinner = findViewById(R.id.weatherSpinner);

        Button testNotificationButton = (Button) findViewById(R.id.testNotificationButton);
        testNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTestNotification();
            }
        });

        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Sunny");
        conditions.add("Rainy");
        conditions.add("Cloudy");
        conditions.add("Snowy");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conditions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setAdapter(arrayAdapter);
    }

    public void sendTestNotification() {
        // sends a test notification to the user
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, nID)
                .setSmallIcon(android.support.v4.R.drawable.notification_template_icon_bg)
                .setContentTitle("myClimate Test Notification")
                .setContentText("This is a test of the Android Notification API.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // bullshit notification id, for now
        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(nIDint, nBuilder.build());
    }



}
