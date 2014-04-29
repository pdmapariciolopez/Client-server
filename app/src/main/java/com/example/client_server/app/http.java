package com.example.client_server.app;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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



public class http extends MainActivity {


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        client_server();

        //client_server();
        //run();

            /*
            new Thread(new Runnable() {
        public void run() {
            final Bitmap bitmap = loadImageFromNetwork("http://example.com/image.png");
            mImageView.post(new Runnable() {
                public void run() {
                    mImageView.setImageBitmap(bitmap);
                }
            });
        }
    }).start();
             */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.http, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



        public void client_server () {
            // Método para iniciar y establecer la conexión por HTTP

            String url="Introducir url aqui";
            HttpClient httpclient = new DefaultHttpClient(); // El tipo HttpClient está definido en la API de apache
            HttpGet httpget = new HttpGet(url); // El tipo HttpGet también está definido en la API
            TextView cliente = (TextView) findViewById(R.id.textView); // Para mostrar el texto

            try { // Capto las excepciones que se puedan producir
                HttpResponse response = httpclient.execute(httpget);

                if (response != null) { // Cuando se recibe la respuesta del servidor
                    String line = "";
                    InputStream inputstream = response.getEntity().getContent(); // Recibo el flujo de datos
                    line = convertStreamToString(inputstream); // Convierto el flujo de datos a stream
                    Toast.makeText(this, line, Toast.LENGTH_SHORT).show();
                    cliente.setText(line); // Muestro por pantalla el flujo de datos

                } else {
                    Toast.makeText(this, "Unable to complete your request", Toast.LENGTH_LONG).show();
                }
            } catch (ClientProtocolException e) {
                Toast.makeText(this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Caught IOException", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Caught Exception", Toast.LENGTH_SHORT).show();
            }

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
            Toast.makeText(this, "Stream Exception", Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }

}
