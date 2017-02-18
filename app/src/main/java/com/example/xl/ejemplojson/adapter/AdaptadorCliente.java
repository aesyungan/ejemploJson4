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

import java.util.List;

/**
 * Created by Angelo on 18/2/2017.
 */

public class AdaptadorCliente extends ArrayAdapter<Cliente> {
    public AdaptadorCliente(Context context,  List<Cliente> dato) {
        super(context, 0, dato);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_cliente, parent, false);
        }
        TextView id_cliente = (TextView) convertView.findViewById(R.id.textViewIDCliente);
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewnombre);
        TextView apellido = (TextView) convertView.findViewById(R.id.textViewapellido);
        TextView cedula = (TextView) convertView.findViewById(R.id.textViewcedula);
        TextView password = (TextView) convertView.findViewById(R.id.textViewpass);
        TextView bono = (TextView) convertView.findViewById(R.id.textViewbono);
        TextView direccion = (TextView) convertView.findViewById(R.id.textViewdireccion);
        TextView fecha_nacimiento = (TextView) convertView.findViewById(R.id.textViewfecha);
        TextView telefono = (TextView) convertView.findViewById(R.id.textViewtelefono);
        TextView email = (TextView) convertView.findViewById(R.id.textViewemail);
        ImageView foto = (ImageView) convertView.findViewById(R.id.textViewfoto);
        id_cliente.setText(Integer.toString(item.getId_cliente()));
        nombre.setText(item.getNombre());
        apellido.setText(item.getApellido());
        cedula.setText(item.getCedula());
        password.setText(item.getPassword());
        bono.setText(item.getBono());
        direccion.setText(item.getDireccion());
        fecha_nacimiento.setText(item.getFecha_nacimiento());
        telefono.setText(item.getTelefono());
        email.setText(item.getEmail());

        return convertView;
    }
}
