package com.example.client_server.app;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.jcraft.jsch.*;



public class ftp extends ActionBarActivity {


    private static final String TAG = "MainActivity";
    private static final String TEMP_FILENAME = "test.txt";
    private Context cntx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftp);
        ftp_con();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ftp, menu);
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



    public void ftp_con() {
        // Método para establecer la conexión por ftp con el servidor


        final  String host="Introducir url del host";
        final String username = "Usuario";
        final String password = "Contraseña";
        final int port = 22; // Puerto estándar para SFTP


        final TextView texto_ftp= (TextView)findViewById(R.id.messages); // Para mostrar los mensajes

        View connecButton = findViewById(R.id.buttona); // Botón para iniciar la conexión
        connecButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                try
                {
                    JSch jsch = new JSch(); // Este objeto está declarado en la API
                    Session session = jsch.getSession(username,host,port); // Creo una sesión con usuario, dominio y puerto
                    // NOTA: la sesión creada es un túnel SSH
                    session.setPassword(password); // Establezco la contraseña para la sesión
                    session.setConfig("StrictHostKeyChecking","no");
                    System.out.println("Estableciendo conexión...");
                    texto_ftp.setText("Estableciendo conexión...");
                    session.connect(); // Inicio la conexión
                    System.out.println("Conexión establecida.");
                    texto_ftp.setText("Conexión establecida...");
                    System.out.println("Creando canal SFTP");
                    texto_ftp.setText("Creando canal SFTP...");
                    ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp"); // Creo un canal SFTP sobre el túnel
                    sftpChannel.connect(); // Me conecto al servidor por el canal establecido
                    System.out.println("Canal SFTP creado correctamente");
                    texto_ftp.setText("Canal SFTP creado correctamente");

                    sftpChannel.disconnect(); // Finalizo la conexión
                    session.disconnect(); // Finalizo la sesión

                }
                catch(JSchException e){
                    System.out.println(e);
                }

            };
        });


    }



}

