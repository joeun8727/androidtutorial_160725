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
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if(mp != null){
                        mp.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                            seekBar.setProgress(mp.getCurrentPosition());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
