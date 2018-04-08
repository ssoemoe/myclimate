package com.bitcamp2018.myclimate.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;

import com.bitcamp2018.myclimate.R;
import com.bitcamp2018.myclimate.model.DatabaseAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appmenu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar menuToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(menuToolbar);

        adapter = new DatabaseAdapter(this);
        adapter.loadWeatherData();

        WebView webview = findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        double lat = 38.994373;
        double lng = -77.029778;
        webview.loadUrl("https://openweathermap.org/weathermap?basemap=map&cities=true&layer=temperature&lat=38.9943&lon=-77.0298&zoom=50#");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI
                onPrefBtnClick();
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Preference button
    public void onPrefBtnClick() {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }

    public void sendTestNotification() {

    }

    public void sendFaceNotification() {


    }
}
