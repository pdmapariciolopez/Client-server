package com.example.client_server.app;

import android.os.AsyncTask;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Paco on 27/04/14.
 */
public class client_server extends AsyncTask {

    private String url = "http://paco-granada.no-ip.biz";
    public HttpClient httpclient = new DefaultHttpClient(); // El tipo HttpClient está definido en la API de apache
    HttpGet httpget = new HttpGet(url); // El tipo HttpGet también está definido en la API
    public  HttpResponse response;
    public String line;
    @Override
    protected Void doInBackground(Object... objects) {

        try {

            //String url = "http://paco-granada.no-ip.biz";
            //String url="http://cmisabel.ugr.es";
            //HttpClient httpclient = new DefaultHttpClient(); // El tipo HttpClient está definido en la API de apache
            //HttpGet httpget = new HttpGet(url); // El tipo HttpGet también está definido en la API
            //HttpResponse response = httpclient.execute(httpget);
            response = httpclient.execute(httpget);


            if (response != null) { // Cuando se recibe la respuesta del servidor
                //String line = "";
                InputStream inputstream = response.getEntity().getContent(); // Recibo el flujo de datos
                line = convertStreamToString(inputstream); // Convierto el flujo de datos a stream
                //Toast.makeText(this, line, Toast.LENGTH_SHORT).show();

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        line="funciona";
        return null;
    }

        private String convertStreamToString(InputStream is) {
            // Método para convertir el flujo de datos a Stream
            String line = "";
            StringBuilder total = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            try {
                while ((line = rd.readLine()) != null) {
                    total.append(line);
                }
            } catch (Exception e) {
                //  Toast.makeText(this, "Stream Exception", Toast.LENGTH_SHORT).show();
            }
            return total.toString();
        }

}
