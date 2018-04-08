package com.bitcamp2018.myclimate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.bitcamp2018.myclimate.R;

import java.util.ArrayList;

/**
 * Created by shane on 4/7/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //setting the weather conditions
        Spinner spinner = findViewById(R.id.weatherSpinner);

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

}
