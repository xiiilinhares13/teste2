package com.gswaugustom.login.actitivitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.gswaugustom.login.R;
import com.gswaugustom.login.apis.YouTubeConfig;

public class Main_Play_Activity extends YouTubeBaseActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private static final String TAG = "Main_Play_Activity";

    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__play_);

        ///trava rotação de tela
      //  this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Log.d(TAG, "onCreate: Iniciando.");

        btnPlay = findViewById(R.id.playButton);
        mYouTubePlayerView = findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Tudo Pronto.");
                youTubePlayer.loadVideo("IRv4Hq5NpHw");


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Algo Deu Errado!!.");
            }
        };


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Carregando Youtube Player.");
                mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);


            }
        });

        this.mViewHolder.mImageButtonHome = this.findViewById(R.id.btn_Home);

        this.setListeners();

    }

    private void setListeners() {

        this.mViewHolder.mImageButtonHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btn_Home){

            Intent intent = new Intent(this, Main_Home_Activity.class);
            startActivity(intent);
            finish();
        }

    }


    public static class ViewHolder {


        private ImageButton mImageButtonHome;
    }


}
