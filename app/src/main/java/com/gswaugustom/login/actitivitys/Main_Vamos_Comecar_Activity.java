package com.gswaugustom.login.actitivitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.gswaugustom.login.R;

public class Main_Vamos_Comecar_Activity extends AppCompatActivity implements View.OnClickListener {


    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__vamos__comecar);

        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.mViewHolder.mImageButtonPlay = this.findViewById(R.id.img_play);
        this.mViewHolder.mImageButtonHome = this.findViewById(R.id.img_home);

        this.setListeners();

    }

    private void setListeners() {

        this.mViewHolder.mImageButtonPlay.setOnClickListener(this);
        this.mViewHolder.mImageButtonHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.img_play){

            Intent intent = new Intent(this,Main_Play_Activity.class);
            startActivity(intent);
            finish();

        }else if(id == R.id.img_home){

            Intent intent = new Intent(this,Main_Home_Activity.class);
            startActivity(intent);
            finish();

        }
    }

    public static class ViewHolder{

        private ImageButton mImageButtonPlay;
        private ImageButton mImageButtonHome;



    }
}
