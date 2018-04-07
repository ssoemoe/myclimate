package com.bitcamp2018.myclimate.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;


import com.bitcamp2018.myclimate.R;
import com.bitcamp2018.myclimate.model.DatabaseAdapter;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private DatabaseAdapter adapter;
    VideoView vv;

    int displayHeight = 500;
    int displayWidth = 500;
    int smallHeight = 300;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(menuToolbar);

        adapter = new DatabaseAdapter(this);
        adapter.loadWeatherData();

        // start the VideoView of the clouds
        vv = findViewById(R.id.cloudslove);
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cloudslove));
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mediaPlayer.setLooping(true);
            }
        });
        vv.start();

        TextView testView = findViewById(R.id.testView);
        testView.setText(adapter.getCurrentWeather());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
