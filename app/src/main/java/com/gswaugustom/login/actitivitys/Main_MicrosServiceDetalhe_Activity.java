package com.gswaugustom.login.actitivitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gswaugustom.login.R;
import com.gswaugustom.login.utils.MsCpf;

import org.json.JSONException;
import org.json.JSONObject;


public class Main_MicrosServiceDetalhe_Activity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main__micros_service_detalhe);

        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        this.mViewHolder.mBtn_cpf = this.findViewById(R.id.btn_consulta_cpf);
        this.mViewHolder.mEditText_cpf = this.findViewById(R.id.edit_cpf);
        this.mViewHolder.mBtn_Solicitar_Uso = this.findViewById(R.id.btn_solicitarUso);
        this.mViewHolder.mBtn_Falar_Owner = this.findViewById(R.id.btn_falarcomTime);
        this.mViewHolder.mBtn_Estatisticas = this.findViewById(R.id.btn_estatisticacard);
        this.mViewHolder.mBtn_Documentacao = this.findViewById(R.id.btn_documentacaocard);

        this.setListeners();
        this.alertDialog();

    }


    private void setListeners() {
        this.mViewHolder.mBtn_cpf.setOnClickListener(this);
        this.mViewHolder.mBtn_Solicitar_Uso.setOnClickListener(this);
        this.mViewHolder.mBtn_Falar_Owner.setOnClickListener(this);
        this.mViewHolder.mBtn_Estatisticas.setOnClickListener(this);
        this.mViewHolder.mBtn_Documentacao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_solicitarUso) {
            alertDialog();
            alerta.show();
        } else if (id == R.id.btn_falarcomTime) {
            Intent intent = new Intent(this, Main_chat_login_Activity.class);
            startActivity(intent);

        } else if (id == R.id.btn_estatisticacard) {
            Intent intent = new Intent(this, Main_Estatistica_Activity.class);
            startActivity(intent);
        } else if (id == R.id.btn_documentacaocard) {
            Intent intent = new Intent(this, Main_Documentacao_Activity.class);
            startActivity(intent);

        } else if (id == R.id.btn_consulta_cpf) {

            String cpf = mViewHolder.mEditText_cpf.getText().toString().trim();
            if (cpf == null || cpf.equals("")) {
                assert mViewHolder.mEditText_cpf != null;
                mViewHolder.mEditText_cpf.setError("Este campo é obrigatório");

            } else {
                cnpj();
            }

        }
    }


    private void cnpj() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String numeroCPF = mViewHolder.mEditText_cpf.getText().toString();

        final String url = "http://10.162.9.166:8400/cpf/validar?numeroCPF=" + numeroCPF;
        //Configura a requisicao
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // mostra a resposta
                String conteudo = "";
                try {
                    conteudo = response.getString("conteudo");
                    Toast.makeText(Main_MicrosServiceDetalhe_Activity.this, conteudo, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Response", response.toString());


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                });

// Adiciona a Fila de requisições
        queue.add(getRequest);


    }

    private void alertDialog() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o icone
        builder.setIcon(R.drawable.token);
        //define o titulo
        builder.setTitle("Token de Acesso");
        //define a mensagem
        builder.setMessage("Deseja solicitar o Token de Acesso para utilização do Microsserviço?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(getBaseContext(), Main_Cofirmacao_Token_Activity.class);
                startActivity(intent);
                Toast.makeText(getApplication(), "Token de Acesso Enviado Para Seu E-Mail", Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe

    }


    public static class ViewHolder {
        private Button mBtn_cpf;
        private EditText mEditText_cpf;
        private ImageButton mBtn_Solicitar_Uso;
        private ImageButton mBtn_Falar_Owner;
        private ImageButton mBtn_Estatisticas;
        private ImageButton mBtn_Documentacao;

    }
}

