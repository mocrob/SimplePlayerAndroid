package com.example.simpleplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPauseButton;
    SeekBar seekBar;
    boolean flag = false;
    ArrayList song = new ArrayList();
    ArrayList songNames = new ArrayList();
    int curSong = 0;
    TextView songName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        song.add(R.raw.radiance);
        song.add(R.raw.kuku);
        song.add(R.raw.reik);
        songNames.add("Radiance");
        songNames.add("Кукушка");
        songNames.add("Рейкьявик");
        mediaPlayer = MediaPlayer.create(getApplicationContext(),(int)song.get(curSong));
        songName = findViewById(R.id.trackTextView);
        songName.setText(songNames.get(curSong).toString());
        playPauseButton = findViewById(R.id.playIImageView);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    flag = true;
                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(!mediaPlayer.isPlaying()&&flag)
                {
                    mediaPlayer.start();
                    flag = false;
                }

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1000);
    }

    public void prev(View view) {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            playPauseButton.setImageResource(R.drawable.ic_play_circle_outline_yellow_24dp);
        }
        seekBar.setProgress(0);
        curSong--;
        if(curSong<0)
        {
            curSong = song.size()-1;
        }
        mediaPlayer = MediaPlayer.create(getApplicationContext(),(int)song.get(curSong));
        songName.setText(songNames.get(curSong).toString());
    }

    public void next(View view) {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            playPauseButton.setImageResource(R.drawable.ic_play_circle_outline_yellow_24dp);
        }
        seekBar.setProgress(0);
        curSong++;
        if(curSong>song.size()-1)
        {
            curSong = 0;
        }
        mediaPlayer = MediaPlayer.create(getApplicationContext(),(int)song.get(curSong));
        songName.setText(songNames.get(curSong).toString());
    }

    public void play_pause(View view) {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            playPauseButton.setImageResource(R.drawable.ic_play_circle_outline_yellow_24dp);
        }
        else
        {
            mediaPlayer.start();
            playPauseButton.setImageResource(R.drawable.ic_pause_circle_outline_yellow_24dp);
        }

    }
}
