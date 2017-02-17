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

import java.util.List;

/**
 * Created by XL on 17/2/2017.
 */

public class AdaptadorCategoria extends ArrayAdapter<Categoria> {

    public AdaptadorCategoria(Context context,  List<Categoria> dato) {
        super(context, 0, dato);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Categoria item=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.row_caregoria,parent,false);
        }

        TextView id_categoria=(TextView)convertView.findViewById(R.id.textViewIDCategoria);
        TextView nombre=(TextView)convertView.findViewById(R.id.textViewnombre);
        TextView descripcion=(TextView)convertView.findViewById(R.id.textViewDescripcion);
        id_categoria.setText(Integer.toString(item.getId_categoria()));
        nombre.setText(item.getNombre());
        descripcion.setText(item.getDescripcion());

        return convertView;
    }
}
