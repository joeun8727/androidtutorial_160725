package com.example.a.a15_mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = null;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

    }

    public void onBtnPlayClick(View v){
        String path = Environment.getExternalStorageDirectory() + "/Samsung/Music/Over_the_horizon.mp3";
        mp = new MediaPlayer();
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            seekBar.setMax(mp.getDuration());
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(mp != null){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        seekBar.setProgress(mp.getCurrentPosition());
                    }
                }
            });
            th.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBtnStopClick(View v){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
