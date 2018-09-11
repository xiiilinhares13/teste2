package com.gswaugustom.login.actitivitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gswaugustom.login.R;

public class Main_Avaliacao_Activity extends AppCompatActivity implements View.OnClickListener{


    private ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__avaliacao);

        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.mViewHolder.mBtn_Cancelar = this.findViewById(R.id.btn_cancelar);
        this.mViewHolder.mBtn_Enviar = this.findViewById(R.id.btn_ok);
        this.mViewHolder.mRatingBar = this.findViewById(R.id.ratingBar);



        this.setListeners();


    }

    private void setListeners() {
        this.mViewHolder.mBtn_Enviar.setOnClickListener(this);
        this.mViewHolder.mBtn_Cancelar.setOnClickListener(this);
        this.mViewHolder.mRatingBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_cancelar){
           // Intent intent = new Intent(this,Main_Home_Activity.class);
            finish();

        }else if (id == R.id.btn_ok){
            double stars = this.mViewHolder.mRatingBar.getRating();
            Toast.makeText(this,"Sua avaliação Foi enviada com Sucesso!!! ", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public static class ViewHolder{

        private Button mBtn_Cancelar;
        private Button mBtn_Enviar;
        private RatingBar mRatingBar;


    }
}
