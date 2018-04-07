package com.bitcamp2018.myclimate.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bitcamp2018.myclimate.R;
import com.bitcamp2018.myclimate.model.DatabaseAdapter;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private DatabaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new DatabaseAdapter(this);
        adapter.loadWeatherData();
        TextView testView = findViewById(R.id.testView);
        testView.setText(adapter.getCurrentWeather());
    }
}
