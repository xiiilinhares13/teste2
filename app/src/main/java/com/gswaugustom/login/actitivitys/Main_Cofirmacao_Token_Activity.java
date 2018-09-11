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

public class Main_Cofirmacao_Token_Activity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__cofirmacao__token);

        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.mViewHolder.mbtn_Doc = this.findViewById(R.id.btn_documentacao);
        this.mViewHolder.mbtn_Primeiros_Passos = this.findViewById(R.id.btn_primeiros_Passos);

        this.setListeners();
    }

    private void setListeners() {
        this.mViewHolder.mbtn_Doc.setOnClickListener(this);
        this.mViewHolder.mbtn_Primeiros_Passos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_documentacao) {
            Intent intent = new Intent(this, Main_Documentacao_Activity.class);
            startActivity(intent);

        } else if (id == R.id.btn_primeiros_Passos) {
            Intent intent = new Intent(this, Main_MeuPrimeiro_Servico_Activity.class);
            startActivity(intent);

        }
    }

    public static class ViewHolder {

        private ImageButton mbtn_Doc;
        private ImageButton mbtn_Primeiros_Passos;

    }
}
