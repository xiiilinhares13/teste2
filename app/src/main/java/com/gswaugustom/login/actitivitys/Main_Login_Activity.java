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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.gswaugustom.login.business.UsuarioBusiness;
import com.gswaugustom.login.conexao.Conexao;
import com.gswaugustom.login.R;
import com.gswaugustom.login.models.Perfil;
import com.gswaugustom.login.utils.toPost;


public class Main_Login_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private static final String LOG_TAG = "OnPOst";
    private ViewHolder mViewHolder = new ViewHolder();


    //GoogleLogin
    private SignInButton btnSignInButtonGoogle;
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    private EditText mEditEmail;
    private EditText mEditSenha;
    //FirebaseLogin
    private FirebaseAuth auth;

    private UsuarioBusiness mUsuarioBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.mViewHolder.mButtonEntrar = this.findViewById(R.id.btnLogar);
        this.mViewHolder.mCliqueAqui = this.findViewById(R.id.txtEsqueceuSenha);
        this.mEditEmail = this.findViewById(R.id.editEmailLogar);
        this.mEditSenha = this.findViewById(R.id.editSenhaLogar);

        this.mUsuarioBusiness = new UsuarioBusiness(this);

        // Botão Google
        this.btnSignInButtonGoogle = this.findViewById(R.id.btn_Google);


        this.mFirebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        this.clickButton();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void clickButton() {
        this.btnSignInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singnIn();
            }
        });


        this.mViewHolder.mButtonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = mEditEmail.getText().toString().trim();
                String senha = mEditSenha.getText().toString().trim();

                if (email == null || email.equals("")) {
                    mEditEmail.setError("Esse Campo Login é Obrigatório");
                } else if (senha == null || senha.equals("")) {
                    mEditSenha.setError("O Campo Senha é Obrigatório");
                    // login(email, senha);
                }
                String[] values = {mEditEmail.getText().toString().trim(), mEditSenha.getText().toString().trim()};
                new toPost(Main_Login_Activity.this).execute(values);

            }
        });

        this.mViewHolder.mCliqueAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.txtEsqueceuSenha) {
                    Intent intent = new Intent(Main_Login_Activity.this, Main_Cadastro_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }


    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Main_Login_Activity.this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Modo de testar se meu email e usuário estão corretos, entrando na activity desejada.
                            Intent i = new Intent(Main_Login_Activity.this, Main_BemVindo_Activity.class);
                            startActivity(i);
                            finish();
                        } else {

                            alert("Login ou Senha não foram encontrados!! ");
                        }
                    }
                });
    }

    private final void singnIn() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseLogin(account);
            }

        }


    }


    private void firebaseLogin(final GoogleSignInAccount account) {

        //sincronia conta google com firebase
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Main_Login_Activity.this, Main_BemVindo_Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            alert("Falha na autenticação");
                        }
                    }
                });


    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão");
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder {
        private Button mButtonEntrar;
        private TextView mCliqueAqui;


    }


}
