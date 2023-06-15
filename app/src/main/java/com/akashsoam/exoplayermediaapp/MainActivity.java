package com.akashsoam.exoplayermediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private PlayerView mPlayerView;
    private SimpleExoPlayer mSimpleExoPlayer;
    private ProgressBar mProgressBar;

//    private Player.EventListener mEventListener;

//    private String music_url = "https://opengameart.org/sites/default/files/the_field_of_dreams.mp3";
    String music_list[] = new String[]{
            "https://pagalworlds.co/wp-content/uploads/Harry-Potter-Theme.mp3",
            "https://opengameart.org/sites/default/files/the_field_of_dreams.mp3",
            "https://opengameart.org/sites/default/files/Cyberpunk%20Moonlight%20Sonata_0.mp3",
            "https://opengameart.org/sites/default/files/battleThemeA.mp3",
            "https://opengameart.org/sites/default/files/Orbital%20Colossus_0.mp3",
            "https://opengameart.org/sites/default/files/Winds%20Of%20Stories_2.mp3",
            "https://opengameart.org/sites/default/files/fight.wav"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayerView = findViewById(R.id.my_player_view);
        mProgressBar = findViewById(R.id.my_progress_bar);

        mSimpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        mPlayerView.setPlayer(mSimpleExoPlayer);


        for (String music_url : music_list) {
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(music_url));
            mSimpleExoPlayer.addMediaItem(mediaItem);
        }
//        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(music_url));
//        mSimpleExoPlayer.setMediaItem(mediaItem);
        mSimpleExoPlayer.prepare();
        mSimpleExoPlayer.play();


        mSimpleExoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                Player.Listener.super.onPlaybackStateChanged(playbackState);
                if(playbackState ==Player.STATE_BUFFERING){
                    mProgressBar.setVisibility(View.VISIBLE);
                }else if(playbackState ==Player.STATE_READY){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSimpleExoPlayer != null) {
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }
}