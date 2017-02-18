package com.example.xl.ejemplojson;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xl.ejemplojson.adapter.AdaptadorCategoria;
import com.example.xl.ejemplojson.clases.Categoria;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Categoria> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listViewCategoria);
        new OptenerCategoria().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categoria item= lista.get(i);
                Toast.makeText(getApplicationContext(),item.getDescripcion(),Toast.LENGTH_LONG).show();
                Log.d("datos",""+item.getId_categoria());
            }
        });


    }
    public class OptenerCategoria extends AsyncTask<Void, Integer, Boolean> {
        ProgressDialog pDialog;
        // products JSONArray
        JSONArray products = null;
        //que va hacer antes de ejecutar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando Lista de Dispositivos");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        //que es lo que va hacer de  durante la ejecucion
        @Override
        protected Boolean doInBackground(Void... params) {
            List parametros = new ArrayList();
            JSONObject json;
            JSON jParser1 = new JSON();
           // parametros.add(new BasicNameValuePair("id_usuario", Integer.toString(Usuario.getId_usuario())));//solo par q vea datos de usuario logueado
            //json = jParser1.makeHttpRequest("http://findme.webcindario.com/vista/usuario_dispositivo_listar.php", "GET", parametros);
            json = jParser1.makeHttpRequest(getString(R.string.s_categoria_listar), "GET", parametros);
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt("success");
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray("categorias");
                    // looping through All Products
                    //Log.i("ramiro", "produtos.length" + products.length());
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        // Storing each json item in variable
                        Categoria item = new Categoria();
                        item.setId_categoria(c.getInt("id_categoria"));
                        item.setNombre(c.getString("nombre"));
                        item.setDescripcion(c.getString("descripcion"));
                        lista.add(item);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        //cuando se termine
        @Override
        protected void onPostExecute(Boolean result) {
            if (result)// pregunta si se ejecuto bien
            {
                AdaptadorCategoria adapter= new AdaptadorCategoria(getApplicationContext(),lista);
                listView.setAdapter(adapter);
                pDialog.dismiss();
                // Toast.makeText(getContext(), "Tarea finalizada!",Toast.LENGTH_SHORT).show();
                Log.d("Tarea Finalizada","Final");
            }
        }
        //cuando se cancelada
        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
