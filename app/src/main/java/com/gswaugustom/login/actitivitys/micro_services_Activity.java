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

public class micro_services_Activity extends AppCompatActivity implements View.OnClickListener {


    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_micro_services);

        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.mViewHolder.mImageButtonMicrosService = this.findViewById(R.id.img_MicrosService);

        this.setListeners();

    }

    private void setListeners() {

        this.mViewHolder.mImageButtonMicrosService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.img_MicrosService){

            Intent intent = new Intent(this,Main_Vamos_Comecar_Activity.class);
            startActivity(intent);
            finish();

        }
    }


    private static class ViewHolder {

        private ImageButton mImageButtonMicrosService;

    }


}
