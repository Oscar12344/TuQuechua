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

public class FamiliaImagenAdapter extends RecyclerView.Adapter<FamiliaImagenAdapter.FamiliaHolder>{
    List<Pregunta> listaFamilias;

    public FamiliaImagenAdapter(List<Pregunta> listaFamilias) {
        this.listaFamilias = listaFamilias;
    }

    @NonNull
    @Override
    public FamiliaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.frase_familia_lista, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new FamiliaHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FamiliaHolder holder, int position) {
        holder.txtPalabra.setText(String.valueOf(listaFamilias.get(position).getPalabra()));
        holder.txtPalabraEspanol.setText(String.valueOf(listaFamilias.get(position).getPalabraEsp()));

        if(listaFamilias.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listaFamilias.get(position).getImagen());
        }
        else
        {
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listaFamilias.size();
    }

    public class FamiliaHolder extends RecyclerView.ViewHolder {
        TextView txtPalabra, txtPalabraEspanol;
        ImageView imagen;
        public FamiliaHolder(@NonNull View itemView) {
            super(itemView);
            txtPalabra=itemView.findViewById(R.id.fcl_palabra);
            txtPalabraEspanol=itemView.findViewById(R.id.fcl_palabraEspanol);

            imagen=itemView.findViewById(R.id.fcl_imagen);
        }
    }

}
