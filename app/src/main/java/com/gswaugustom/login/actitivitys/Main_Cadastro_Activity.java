package com.gswaugustom.login.actitivitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.gswaugustom.login.conexao.Conexao;
import com.gswaugustom.login.R;

public class Main_Cadastro_Activity extends AppCompatActivity {


    private ViewHolder mViewHolder = new ViewHolder();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_cadastro);


        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.mViewHolder.mButtonEnviaSenha = findViewById(R.id.button_save);
        this.mViewHolder.meditEmail = findViewById(R.id.editEmailCad);

        //this.setListener();
        this.eventoClick();
    }

    private void eventoClick() {
        this.mViewHolder.mButtonEnviaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mViewHolder.meditEmail.getText().toString().trim();
                if (email == null || email.equals("")) {
                    mViewHolder.meditEmail.setError("Campo Email é Obrigatório!!");
                } else {
                    resetSenha(email);
                }
            }
        });
    }

    private void resetSenha(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(Main_Cadastro_Activity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            alert("Um e-mail foi enviado para redefinir a senha!!");
                            Intent intent = new Intent(Main_Cadastro_Activity.this, Main_Login_Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            alert("E-mail não registrado");
                        }
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(Main_Cadastro_Activity.this, s, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }



    public static class ViewHolder {

        private Button mButtonEnviaSenha;
        private EditText meditEmail;

    }
}
