package com.gswaugustom.login.actitivitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gswaugustom.login.R;

import com.gswaugustom.login.business.UsuarioBusiness;
import com.gswaugustom.login.fragments.configFragment;
import com.gswaugustom.login.fragments.conteudoFragment;
import com.gswaugustom.login.fragments.inicioFragment;
import com.gswaugustom.login.fragments.microsservicosFragment;
import com.gswaugustom.login.repository.PerfilRepository;
import com.gswaugustom.login.tab.Main2Activity;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;


public class Main_Home_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private ProgressBar pbProfile;
    private ImageView mimageView;
    private TextView mtvEmail;
    private TextView mEditEmail;
    private Button mbtn_logout;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Firebase firebase;
    private FirebaseUser mauth;
    private UsuarioBusiness mUsuarioBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tela orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__home_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            /** Called when a drawer has settled in a completely open state. */
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                pbProfile = drawerView.findViewById(R.id.progessBar);
                mimageView = drawerView.findViewById(R.id.imageView);
                mtvEmail = drawerView.findViewById(R.id.tvEmail);
                mEditEmail = drawerView.findViewById(R.id.tvEmail);
                mbtn_logout = drawerView.findViewById(R.id.btn_logout);


                mbtn_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = v.getId();
                        if (id == R.id.btn_logout) {

                            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                                @Override
                                public void onResult(@NonNull Status status) {
                                    alert("Logged Out");
                                    Intent i = new Intent(getApplicationContext(), Main_Login_Activity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }
                    }
                });

                teste();

//                GoogleSignInAccount resultado = GoogleSignIn.getLastSignedInAccount(Main_Home_Activity.this);
//
//
//                //verifica se esta conectado com google ou oauth
//                if (resultado != null) {
//
//                    // coloca email no textview
//                    String email = resultado.getEmail();
//                    mtvEmail.setText(email);
//
//
//                    // coloca foto no imagem
//                    String imageUrl = Objects.requireNonNull(resultado.getPhotoUrl()).toString();
//                    Log.d("Script", "IMG: " + imageUrl);
//                    imageUrl = imageUrl.substring(0, imageUrl.length() - 2);
//                    //mimageView.setImageURI(Uri.parse(imageUrl));
//
//
//                    Picasso.get()
//                            .load(imageUrl)
//                            .resize(50, 50)
//                            .centerCrop()
//                            .into(mimageView);
//
//
//                } else if (resultado == null) {
//
//                    String name = "";
//                    PerfilRepository perfilRepository = new PerfilRepository(Main_Home_Activity.this);
//                    name = perfilRepository.getUsuarioByQuery();
//                    String email = "@spcbrasil.org.br";
//                    mtvEmail.setText(name + email);
//
//                }
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.startDefaultFragment();

    }

    private void teste() {

        GoogleSignInAccount resultado = GoogleSignIn.getLastSignedInAccount(Main_Home_Activity.this);


        //verifica se esta conectado com google ou oauth
        if (resultado != null) {

            // coloca email no textview
            String email = resultado.getEmail();
            mtvEmail.setText(email);


            // coloca foto no imagem
            String imageUrl = resultado.getPhotoUrl().toString();
            Log.d("Script", "IMG: " + imageUrl);
            imageUrl = imageUrl.substring(0, imageUrl.length() - 2);
            //mimageView.setImageURI(Uri.parse(imageUrl));


            Picasso.get()
                    .load(imageUrl)
                    .resize(50, 50)
                    .centerCrop()
                    .into(mimageView);


        } else if (resultado == null) {

            String name = "";
            PerfilRepository perfilRepository = new PerfilRepository(Main_Home_Activity.this);
            name = perfilRepository.getUsuarioByQuery();
            String email = "@spcbrasil.org.br";
            mtvEmail.setText(name + email);

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__home_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            setTitle("Setings Fragmento");
            configFragment config = new configFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, config).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            setTitle("Início");
            inicioFragment inicio = new inicioFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, inicio).commit();
        } else if (id == R.id.nav_conteudo) {
            setTitle("Conteúdo");
            conteudoFragment conteudoFragment = new conteudoFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, conteudoFragment).commit();

        } else if (id == R.id.nav_microsservicos) {
            setTitle("Microsserviços");
            microsservicosFragment microsservicos = new microsservicosFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, microsservicos).commit();


        } else if (id == R.id.nav_ranking) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startDefaultFragment() {
        Fragment fragment = null;
        Class fragmentClass = inicioFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();

    }

    @Override
    protected void onStart() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão");
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
