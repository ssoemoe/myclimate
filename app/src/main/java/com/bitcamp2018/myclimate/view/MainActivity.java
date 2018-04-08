package com.bitcamp2018.myclimate.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
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

public class MainActivity extends AppCompatActivity {
    private DatabaseAdapter adapter;
    VideoView vv;

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

        //current weather
        TextView weatherView = findViewById(R.id.curWeatherTxt);
        weatherView.setText(adapter.getCurrentWeather());
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

}
