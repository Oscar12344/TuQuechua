package com.example.tuquechua.avanzado;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tuquechua.MainActivity;
import com.example.tuquechua.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;

import kotlin.Lazy;

public class comida_video1 extends AppCompatActivity {
    SimpleExoPlayer simplePlayer;
    PlayerView playerView;
    Button btnOp1, btnOp2, btnOp3, btnOp4;
    private boolean playWhenReady = true;
    private int currentWindow;
    private long playbackPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_video1);
        playerView = findViewById(R.id.player);

        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

        Uri vidUrl = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4");
        MediaItem vid = MediaItem.fromUri(vidUrl);
        simplePlayer = ExoPlayerFactory.newSimpleInstance(
                MainActivity.this,trac
        );
    }
}