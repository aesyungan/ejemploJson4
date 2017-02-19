package com.example.xl.ejemplojson.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xl.ejemplojson.R;
import com.example.xl.ejemplojson.clases.Producto;

import java.util.List;

/**
 * Created by Angelo on 18/2/2017.
 */

public class AdaptadorProducto extends ArrayAdapter<Producto>{
    public AdaptadorProducto(Context context,  List<Producto> dato) {
        super(context, 0, dato);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Producto item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_producto, parent, false);
        }
        TextView nombre = (TextView) convertView.findViewById(R.id.txtnombreProducto);
        TextView precio = (TextView) convertView.findViewById(R.id.txtprecio);
        nombre.setText(item.getNombre());
        precio.setText(Double.toString(item.getPrecio()));
        return convertView;
    }
}
