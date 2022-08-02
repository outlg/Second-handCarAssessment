package com.example.carevaluate.Activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.carevaluate.R;

public class IntroActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = findViewById(R.id.video);

        videoView.setMediaController(new MediaController(this));

        videoView.setVideoURI(Uri.parse("https://f.us.sinaimg.cn/0011LABvlx07tKgSYz8I01041200Kr4p0E010.mp4?label=mp4_720p&template=1280x720.20.0&media_id=4370448172169722&tp=8x8A3El:YTkl0eM8&us=0&ori=1&bf=4&ot=h&lp=00003zb72x&ps=mZ6WB&uid=6UsgPx&ab=7397-g1&Expires=1655733367&ssig=qSFQABjd%2Bt&KID=unistore,video"));
        videoView.start();
    }

}
