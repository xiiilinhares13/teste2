package com.gswaugustom.login.actitivitys;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gswaugustom.login.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Main_Consulta_Cnpj_Activity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_consulta_cnpj);


        //tela
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        this.mViewHolder.mBtn_cnpj = this.findViewById(R.id.btn_cnpj);
        this.mViewHolder.mEdit_cnpj = this.findViewById(R.id.edit_cnpj);

        this.setListener();
    }

    private void setListener() {
        this.mViewHolder.mBtn_cnpj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_cnpj) {
            String cnpj = mViewHolder.mEdit_cnpj.getText().toString().trim();
            if (cnpj == null || cnpj.equals("")) {
                mViewHolder.mEdit_cnpj.setError("Este campo é obrigatório");
            } else {
                cnpj();
            }
        }

    }

    private void cnpj() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String cnpj = mViewHolder.mEdit_cnpj.getText().toString().trim();

        final String url = "http://10.162.9.166:8600/cnpj/validar?numeroCNPJ=" + cnpj;
        //Configura a requisição
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Mostra a resposta
                String conteudo = "";
                try {
                    conteudo = response.getString("conteudo");
                    Toast.makeText(Main_Consulta_Cnpj_Activity.this, conteudo, Toast.LENGTH_LONG).show();
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
        //adiciona na Fila de requisições
        queue.add(getRequest);

    }


    public static class ViewHolder {

        private Button mBtn_cnpj;
        private EditText mEdit_cnpj;

    }

}
