package com.gswaugustom.login.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.gswaugustom.login.actitivitys.Main_BemVindo_Activity;
import com.gswaugustom.login.business.UsuarioBusiness;
import com.gswaugustom.login.models.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class toPost extends AsyncTask<String, Void, String> {
    private Context context;
//    private UsuarioBusiness mUsuarioBusiness = new UsuarioBusiness(context);
//    private Usuario mUsuario = new Usuario();


    public toPost(Context context) {
        this.context = context;
    }


    private String use = "";
    private Boolean autenticado = false;

    private static final String LOG_TAG = "OnPOst";

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public Boolean getAutenticado(boolean b) {
        return autenticado;
    }


    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... values) {

        String email1 = values[0];
        String senha1 = values[1];

        //String email1 = mEditEmail.getText().toString().trim();
        //String senha1 = mEditSenha.getText().toString().trim();
        String address = "http://10.162.9.166:8085/spc-autorizacao-oauth/oauth/token";
        HttpURLConnection urlConnection;
        String requestBody;
        Uri.Builder builder = new Uri.Builder();
        Map<String, String> params = new HashMap<>();
        params.put("username", email1);
        params.put("password", senha1);
        params.put("grant_type", "password");

        //Enconde parameters
        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            builder.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
            entries.remove();
        }
        requestBody = builder.build().getEncodedQuery();

        try {
            URL url = new URL(address);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Authorization", "Basic Y2xpZW50QXNzb2NpYWRvOnNlY3JldA==");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            writer.write(requestBody);
            writer.flush();
            writer.close();
            outputStream.close();

            JSONObject jsonObject = new JSONObject();

            InputStream inputStream;

            //get stream
            if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = urlConnection.getInputStream();
                setAutenticado(true);


            } else {
                inputStream = urlConnection.getErrorStream();
                setAutenticado(false);

            }

            //parse stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";

            while ((temp = bufferedReader.readLine()) != null) {

                response += temp;


            }


            return response;


            //put into JSONObject
            // jsonObject.put("Content", response);
//            jsonObject.put("Message", urlConnection.getResponseMessage());
//            jsonObject.put("Length", urlConnection.getContentLength());
//            jsonObject.put("Type", urlConnection.getContentType());


//            return jsonObject.toString();

        } catch (IOException e) {
            return e.toString();
        }


    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            String username = jsonObject.getString("username");
            String nomeoperador = jsonObject.getString("nomeOperador");
            //int refresh_token = Integer.parseInt(jsonObject.getString("refresh_token"));

            Usuario muUsuario = new Usuario();
            UsuarioBusiness mUsuarioBusiness = new UsuarioBusiness(this.context);


            muUsuario.setUsuario(username);
            muUsuario.setNome(nomeoperador);
           // muUsuario.setReverse_token(refresh_token);

            mUsuarioBusiness.insert(muUsuario);


            Log.i(LOG_TAG, "UserName:\n" + username);
            Log.i(LOG_TAG, "NomeOperador:\n" + nomeoperador);
            //Log.i(LOG_TAG, "Refresh_Token:\n" + refresh_token);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(LOG_TAG, e.getMessage());
        }


        Log.i(LOG_TAG, "POST\n" + result);
        if (getAutenticado(true)) {
            Toast.makeText(context, "Bem Vindo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Main_BemVindo_Activity.class);
            context.startActivity(intent);
            ((Activity) context).finish();


        } else if (getAutenticado(false)) {
            Toast.makeText(context, "Algo de errado aconteceu", Toast.LENGTH_SHORT).show();

        }


    }


}

