package com.example.xl.ejemplojson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xl.ejemplojson.clases.Cliente;
import com.example.xl.ejemplojson.clasesEstaticas.ClienteLogueado;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    Cliente clienteLogueado= new Cliente();
    String userNow;
    String passNow;
    TextView txtcedula;
    TextView txtPassword;
    Button btnentrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtcedula=(TextView)findViewById(R.id.txtcedula);
        txtPassword=(TextView)findViewById(R.id.txtpassword);
        btnentrar =(Button)findViewById(R.id.email_sign_in_button);
        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             new LoguearAsyncTask().execute();
            }
        });
    }


    //asysntak para verificar Usuario
    class LoguearAsyncTask extends AsyncTask<String, String, String> {
        JSON json = new JSON();
        private ProgressDialog pDialog;

        public LoguearAsyncTask() {
            userNow=txtcedula.getText().toString();
            passNow=txtPassword.getText().toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Verificando ausuario ..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;


            try {
                // Building Parameters
                List params = new ArrayList();




                params.add(new BasicNameValuePair("nick", userNow));
                params.add(new BasicNameValuePair("password", passNow));
                String REGISTER_URL = getString(R.string.s_cliente_logear);//servio web Loguear

                //Posting user data to script
                JSONObject json = this.json.makeHttpRequest(REGISTER_URL, "GET", params);

                // full json response
                Log.d("Registering attempt", json.toString());

                // json success element
                success = json.getInt("success");
                if (success == 1) {
                    Log.d("Correct Login!", json.toString());
                    //cargar datos a la clase statica
                    JSONArray products = null;
                    products = json.getJSONArray("usuarios");
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        // Storing each json item in variable
                        clienteLogueado.setId_cliente(c.getInt("id_cliente"));
                        clienteLogueado.setNombre(c.getString("nombre"));
                        clienteLogueado.setApellido(c.getString("apellido"));
                        clienteLogueado.setCedula(c.getString("cedula"));
                        clienteLogueado.setPassword(c.getString("password"));
                        clienteLogueado.setBono(c.getString("bono"));
                        clienteLogueado.setDireccion(c.getString("direccion"));
                        clienteLogueado.setFecha_nacimiento(c.getString("fecha_nacimiento"));
                        clienteLogueado.setTelefono(c.getString("telefono"));
                        clienteLogueado.setEmail(c.getString("email"));
                        clienteLogueado.setFoto(c.getString("foto"));
                        ClienteLogueado.cliente=clienteLogueado;



                    }


                    finish();//si esta bn finaliza el asyncTask y ejecuta a abrir otra actividad
                    Intent i = new Intent(Login.this,MenuPrincipal.class);
                    startActivity(i);
                    //luego de abrir la actividad sigue el asynctask y retorna el mensaje que se mostrara en onPostExecute
                    return json.getString("message");



                } else {
                    Log.d("Fail Login!", json.getString("message"));
                    return json.getString("message");


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String mensage) {
            // dismiss the dialog once product deleted
            // aqui viene lo q se mando cuando e termino el doInBackground
            pDialog.dismiss();
            if (mensage != null) {
                Toast.makeText(Login.this, mensage, Toast.LENGTH_LONG).show();
            }
        }
    }
}
