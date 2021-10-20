package com.example.tuquechua.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Pregunta;

import java.util.List;

public class NumeroImagenAdapter extends RecyclerView.Adapter<NumeroImagenAdapter.NumeroHolder> {
    List<Pregunta> listaNumeros;

    public NumeroImagenAdapter(List<Pregunta> listaNumeros) {
        this.listaNumeros = listaNumeros;
    }

    @NonNull
    @Override
    public NumeroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.frase_numero_lista, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new NumeroHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NumeroHolder holder, int position) {
        holder.txtPalabra.setText(String.valueOf(listaNumeros.get(position).getPalabra()));
        holder.txtPalabraEspanol.setText(String.valueOf(listaNumeros.get(position).getPalabraEsp()));

        if(listaNumeros.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listaNumeros.get(position).getImagen());
        }
        else
        {
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listaNumeros.size();
    }

    public class NumeroHolder extends RecyclerView.ViewHolder {
        TextView txtPalabra, txtPalabraEspanol;
        ImageView imagen;
        public NumeroHolder(@NonNull View itemView) {
            super(itemView);
            txtPalabra=itemView.findViewById(R.id.fcl_palabra);
            txtPalabraEspanol=itemView.findViewById(R.id.fcl_palabraEspanol);

            imagen=itemView.findViewById(R.id.fcl_imagen);
        }
    }
}
