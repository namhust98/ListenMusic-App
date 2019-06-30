package com.example.readdata.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.readdata.Fragment.FragmentMixtapeOfTheWeek;
import com.example.readdata.Fragment.FragmentTopTrack;
import com.example.readdata.Model.BaiHat;
import com.example.readdata.Model.Video;
import com.example.readdata.R;

public class PlayVideoActivity extends AppCompatActivity {

    VideoView video;
    TextView name, title, luotxem;
    Video videoData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);

        findView();
        getdata();
    }

    private void findView() {
        video = findViewById(R.id.video);
        name = findViewById(R.id.name_video);
        title = findViewById(R.id.title_video);
        luotxem = findViewById(R.id.luotxem_video);
    }

    private void getdata() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("video")) {
                videoData = (Video) intent.getSerializableExtra("video");
                name.setText(videoData.getName());
                title.setText(videoData.getTitle());
                luotxem.setText(videoData.getLuotxem() + " Lượt xem");

                String videoPath = videoData.getUrlmedia();
                video.setVideoPath(videoPath);
                MediaController mediaController = new MediaController(this);
                video.setMediaController(mediaController);
                mediaController.setAnchorView(video);
                video.start();
            }
        }
    }
}
