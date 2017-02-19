package com.example.xl.ejemplojson;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xl.ejemplojson.adapter.AdaptadorProducto;
import com.example.xl.ejemplojson.clases.Producto;
import com.example.xl.ejemplojson.clasesEstaticas.ClienteLogueado;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Compas extends Fragment {
    double TotalCalulado=0;
    List<Producto> listaProductos=new  ArrayList<Producto>();
    TextView txtTotalCalcular;
    ListView listViewProductos;
   private FloatingActionButton scanBtn;
   // private TextView formatTxt, contentTxt;

    public Compas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_compas, container, false);
        scanBtn = (FloatingActionButton)view.findViewById(R.id.scan_button);
       // formatTxt = (TextView)view.findViewById(R.id.scan_format);
       // contentTxt = (TextView)view.findViewById(R.id.scan_content);
        listViewProductos=(ListView)view.findViewById(R.id.listProductoScaner);
        txtTotalCalcular=(TextView)view.findViewById(R.id.txtTotalCalcular);
        txtTotalCalcular.setText("0");
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(getActivity()) {
                    @Override
                    protected void startActivityForResult(Intent intent, int code) {
                        Compas.this.startActivityForResult(intent, IntentIntegrator.REQUEST_CODE); // REQUEST_CODE override
                       // IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                       // scanIntegrator.initiateScan();

                    }
                };
                integrator.initiateScan();


            }
        });
        return  view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
//we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
          //  formatTxt.setText("FORMAT: " + scanFormat);
           /// contentTxt.setText("CONTENT: " + scanContent);
            Toast.makeText(getContext(),scanContent,Toast.LENGTH_LONG).show();
            new CargarProducto(scanContent).execute();
        }else{
            Toast toast = Toast.makeText(ClienteLogueado.context,
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    //cargar

    public class CargarProducto extends AsyncTask<Void, Integer, Boolean> {

        String id_producto="";
        ProgressDialog pDialog;
        // products JSONArray
        JSONArray products = null;
        //que va hacer antes de ejecutar
        AdaptadorProducto adaptadorProducto;

        public CargarProducto(String id_producto) {
            this.id_producto = id_producto;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             pDialog = new ProgressDialog(getContext());
             pDialog.setMessage("Cargando Mapa...");
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
            parametros.add(new BasicNameValuePair("id_producto", id_producto));
            json = jParser1.makeHttpRequest("http://13.92.130.144/innovacion/services/s_producto_buscar_id.php", "GET", parametros);
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt("success");

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray("productos");

                    // looping through All Products
                    //Log.i("ramiro", "produtos.length" + products.length());
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        // Storing each json item in variable
                        Producto item = new Producto();
                        item.setId_producto(c.getInt("id_producto"));
                        item.setNombre(c.getString("nombre"));
                        item.setPrecio(c.getDouble("precio"));
                        item.setStock(c.getInt("stock"));
                        item.setId_categoria(c.getInt("id_categoria"));
                        item.setFormat(c.getString("format"));
                        item.setContent(c.getString("content"));
                        TotalCalulado +=item.getPrecio();

                        listaProductos.add(item);
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
                txtTotalCalcular.setText(Double.toString(TotalCalulado));
                adaptadorProducto= new AdaptadorProducto(getContext(),listaProductos);
                listViewProductos.setAdapter(adaptadorProducto);
                pDialog.dismiss();
                 Toast.makeText(getContext(), "Producto Agregado..!",Toast.LENGTH_SHORT).show();
                Log.d("Tarea final ","producto agregado");
            }
        }

        //cuando este en progreso
        @Override
        protected void onProgressUpdate(Integer... values) {

            // int progreso = values[0].intValue();

            //  pDialog.setProgress(progreso);
        }

        //cuando se cancelada
        @Override
        protected void onCancelled() {
            Toast.makeText(getContext(), "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
