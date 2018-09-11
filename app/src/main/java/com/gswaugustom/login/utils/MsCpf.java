package com.gswaugustom.login.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class MsCpf extends AsyncTask<Void, Void, String> {
    private Context httpContext;
    ProgressDialog progressBar;
    public String resultadoapi = "";
    public String linkrequestAPI = "";
    public String numeroCPF = "";

    public MsCpf(Context ctx, String linkAPI) {
        this.httpContext = ctx;
        this.linkrequestAPI = linkAPI;
        this.numeroCPF = numeroCPF;
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = null;
        String wsURL = linkrequestAPI;
        URL url = null;
        try {
            //crio conexão com a api
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //crio objeto para enviar o post
            JSONObject parametrosPost = new JSONObject();
            parametrosPost.put("numeroCPF", numeroCPF);

            //PARAMETROS DE CONEXÃO
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTER O RESULTADO DA REQUEST
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();


            int responseCode = urlConnection.getResponseCode();//conexão ok??

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String linha = "";
                while ((linha = in.readLine()) != null) {
                    sb.append(linha);
                    break;

                }
                in.close();
                result = sb.toString();
            } else {
                result = new String("Error: " + responseCode);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;

    }

    protected void onPreExecute() {
        super.onPreExecute();
        //progressBar = ProgressDialog.show(httpContext, "Carregando", "Estamos trabalhando");
        Toast.makeText(httpContext, resultadoapi, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
       // progressBar.dismiss();
        resultadoapi = s;

    }

    public String getPostDataString(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }


}
