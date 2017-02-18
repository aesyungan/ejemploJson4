package com.example.xl.ejemplojson.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xl.ejemplojson.R;
import com.example.xl.ejemplojson.clases.Categoria;
import com.example.xl.ejemplojson.clases.Cliente;
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
        TextView id_producto = (TextView) convertView.findViewById(R.id.textViewIdproducto);
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewnombre);
        TextView precio = (TextView) convertView.findViewById(R.id.textViewaprecio);
        TextView stock = (TextView) convertView.findViewById(R.id.textViewstock);
        TextView id_categoria = (TextView) convertView.findViewById(R.id.textViewIdCategoria);
        TextView format = (TextView) convertView.findViewById(R.id.textViewformat);
        TextView content = (TextView) convertView.findViewById(R.id.textViewcontent);
        id_producto.setText(Integer.toString(item.getId_producto()));
        nombre.setText(item.getNombre());
        precio.setText((int) item.getPrecio());
        stock.setText(item.getStock());
        id_categoria.setText(item.getId_categoria());
        format.setText(item.getFormat());
        content.setText(item.getContent());

        return convertView;
    }
}
