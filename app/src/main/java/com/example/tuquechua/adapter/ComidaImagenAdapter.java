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

public class ComidaImagenAdapter extends RecyclerView.Adapter<ComidaImagenAdapter.ComidaHolder> {
    List<Pregunta> listaComidas;

    public ComidaImagenAdapter(List<Pregunta> listaComidas) {
        this.listaComidas = listaComidas;
    }

    @NonNull
    @Override
    public ComidaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.frase_comida_lista, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ComidaHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull ComidaHolder holder, int position) {
        holder.txtPalabra.setText(String.valueOf(listaComidas.get(position).getPalabra()));
        holder.txtPalabraEspanol.setText(String.valueOf(listaComidas.get(position).getPalabraEsp()));

        if(listaComidas.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listaComidas.get(position).getImagen());
        }
        else
        {
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listaComidas.size();
    }


    public class ComidaHolder extends RecyclerView.ViewHolder {
        TextView txtPalabra, txtPalabraEspanol;
        ImageView imagen;
        public ComidaHolder(@NonNull View itemView) {
            super(itemView);
            txtPalabra=itemView.findViewById(R.id.fcl_palabra);
            txtPalabraEspanol=itemView.findViewById(R.id.fcl_palabraEspanol);

            imagen=itemView.findViewById(R.id.fcl_imagen);
        }
    }
}
