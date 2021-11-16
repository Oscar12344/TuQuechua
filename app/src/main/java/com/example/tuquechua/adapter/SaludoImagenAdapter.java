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

public class SaludoImagenAdapter extends RecyclerView.Adapter<SaludoImagenAdapter.SaludoHolder>{
    List<Pregunta> listaSaludos;

    public SaludoImagenAdapter(List<Pregunta> listaSaludos) {
        this.listaSaludos = listaSaludos;
    }

    @NonNull
    @Override
    public SaludoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.frase_saludo_lista, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new SaludoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SaludoHolder holder, int position) {
        holder.txtPalabra.setText(String.valueOf(listaSaludos.get(position).getPalabra()));
        holder.txtPalabraEspanol.setText(String.valueOf(listaSaludos.get(position).getPalabraEsp()));

        if(listaSaludos.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listaSaludos.get(position).getImagen());
        }
        else
        {
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listaSaludos.size();
    }


    public class SaludoHolder extends RecyclerView.ViewHolder{
        TextView txtPalabra, txtPalabraEspanol;
        ImageView imagen;
        public SaludoHolder(@NonNull View itemView) {
            super(itemView);
            txtPalabra=itemView.findViewById(R.id.fcl_palabra);
            txtPalabraEspanol=itemView.findViewById(R.id.fcl_palabraEspanol);

            imagen=itemView.findViewById(R.id.fcl_imagen);
        }
    }
}
