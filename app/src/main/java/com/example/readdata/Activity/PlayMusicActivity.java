package com.example.readdata.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.readdata.Adapter.ListBaiHatAdapter;
import com.example.readdata.Fragment.FragmentBaiHat;
import com.example.readdata.Fragment.FragmentListBaiHat;
import com.example.readdata.Fragment.FragmentLoiBaiHat;
import com.example.readdata.Fragment.FragmentMixtapeOfTheWeek;
import com.example.readdata.Fragment.FragmentQuangCao;
import com.example.readdata.Fragment.FragmentRadioStation;
import com.example.readdata.Fragment.FragmentTopTrack;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.QuangCao;
import com.example.readdata.R;
import com.example.readdata.ViewPagerAdapter.MusicViewPagerAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {

    private BaiHat baiHat;
    public static BaiHat dataBaiHat;
    private ViewPager viewPager;
    private static TextView Sumtime;
    private static TextView time;
    private static SeekBar seekBar;
    private static ImageButton back;
    private static ImageButton play;
    private static ImageButton next;
    private ImageButton kindplay;
    private ImageButton download;
    private static MediaPlayer mediaPlayer;
    public static List<BaiHat> mangbaihat;
    private static int position = 0;
    private MusicViewPagerAdapter musicViewPagerAdapter;
    private static boolean checkrepeat = false;
    private static boolean checkrandom = false;
    private boolean checknormal = true;
    private static boolean checknext = false;
    public static String kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusic);
        mangbaihat = new ArrayList<>();
        mediaPlayer = new MediaPlayer();

        findView();
        getdata();
        init();
        eventClick();
    }
    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (musicViewPagerAdapter.getItem(1) != null){
                    if (mangbaihat.size() > 0){
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play.setBackgroundResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    play.setBackgroundResource(R.drawable.stop);
                }
            }
        });

        kindplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checknormal == true) {
                    checkrepeat = true;
                    checknormal = false;
                    kindplay.setBackgroundResource(R.drawable.repeat);
                }
                else if (checkrepeat == true) {
                    checkrandom = true;
                    checkrepeat = false;
                    kindplay.setBackgroundResource(R.drawable.random);
                }
                else if (checkrandom == true) {
                    checknormal = true;
                    checkrandom = false;
                    kindplay.setBackgroundResource(R.drawable.normal);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0) {
                    if ((mediaPlayer.isPlaying()) || (mediaPlayer != null)) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < mangbaihat.size()) {
                        play.setBackgroundResource(R.drawable.stop);
                        position++;
                        if (checkrepeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mangbaihat.get(position).getUrlmedia());
                        updateTime();
                        FragmentBaiHat.getData(mangbaihat.get(position));
                    }
                }

                next.setClickable(false);
                back.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setClickable(true);
                        back.setClickable(true);
                    }
                }, 3000);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0) {
                    if ((mediaPlayer.isPlaying()) || (mediaPlayer != null)) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < mangbaihat.size()) {
                        play.setBackgroundResource(R.drawable.stop);
                        position--;
                        if (checkrepeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position < 0) {
                            position = mangbaihat.size() - 1;
                        }
                        new playMp3().execute(mangbaihat.get(position).getUrlmedia());
                        updateTime();
                        FragmentBaiHat.getData(mangbaihat.get(position));
                    }
                }

                next.setClickable(false);
                back.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setClickable(true);
                        back.setClickable(true);
                    }
                }, 3000);
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMusicActivity.this, WaitUpdateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        Intent intent = getIntent();
        kind = new String();
        if (intent != null) {
            if (intent.hasExtra("bai hat")) {
                baiHat = (BaiHat) intent.getSerializableExtra("bai hat");
                dataBaiHat = baiHat;
            }
            if (intent.getSerializableExtra("fragment").equals("MixTapeOfTheWeek")) {
                mangbaihat = FragmentMixtapeOfTheWeek.mangbaihat;
                kind = "MixTapeOfTheWeek";
            }

            if (intent.getSerializableExtra("fragment").equals("TopTrack")) {
                mangbaihat = FragmentTopTrack.mangbaihat;
                kind = "TopTrack";
            }

            if (intent.getSerializableExtra("fragment").equals("QuangCao")) {
                mangbaihat = FragmentQuangCao.mangbaihat;
                kind = "QuangCao";
            }

            if (intent.getSerializableExtra("fragment").equals("RadioStation")) {
                mangbaihat = FragmentRadioStation.mangbaihat;
                kind = "RadioStation";
            }
        }
    }

    private void findView() {
        viewPager = findViewById(R.id.viewPager_playmusic);
        time = findViewById(R.id.time_playmusic);
        seekBar = findViewById(R.id.seekBar_playmusic);
        back = findViewById(R.id.back_playmusic);
        play = findViewById(R.id.play_playmusic);
        next = findViewById(R.id.next_playmusic);
        kindplay = findViewById(R.id.kindoflisten_playmusic);
        download = findViewById(R.id.download_playmusic);
        Sumtime = findViewById(R.id.timeSum_playmusic);
    }

    private void init() {
        musicViewPagerAdapter = new MusicViewPagerAdapter(getSupportFragmentManager());

        musicViewPagerAdapter.addFragment(new FragmentBaiHat());
        musicViewPagerAdapter.addFragment(new FragmentListBaiHat());
        musicViewPagerAdapter.addFragment(new FragmentLoiBaiHat());
        viewPager.setAdapter(musicViewPagerAdapter);
        new playMp3().execute(baiHat.getUrlmedia());
        play.setBackgroundResource(R.drawable.stop);
    }

    static class playMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            updateTime();
        }
    }

    private static void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Sumtime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private static void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    time.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            checknext = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checknext == true) {
                    if (position < mangbaihat.size()) {
                        play.setBackgroundResource(R.drawable.stop);
                        position++;
                        if (checkrepeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mangbaihat.get(position).getUrlmedia());
                        FragmentBaiHat.getData(mangbaihat.get(position));
                    }

                    next.setClickable(false);
                    back.setClickable(false);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            next.setClickable(true);
                            back.setClickable(true);
                        }
                    }, 3000);
                    checknext = false;
                    handler2.removeCallbacks(this);
                } else
                    handler1.postDelayed(this, 1000);
            }
        }, 1000);
    }

    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    public static void chuyenbaihat(int i){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        new playMp3().execute(mangbaihat.get(i).getUrlmedia());
        updateTime();
        FragmentBaiHat.getData(mangbaihat.get(i));
        position = i;
    }
}
