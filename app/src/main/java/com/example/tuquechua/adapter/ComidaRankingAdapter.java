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
import com.example.tuquechua.entidades.Ranking;

import java.util.List;

public class ComidaRankingAdapter extends RecyclerView.Adapter<ComidaRankingAdapter.ComidaRankingHolder>{
    List<Ranking> listaRankingComidas;

    public ComidaRankingAdapter(List<Ranking> listaRankingComidas) {
        this.listaRankingComidas = listaRankingComidas;
    }

    @NonNull
    @Override
    public ComidaRankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_comida, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ComidaRankingHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaRankingHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingComidas.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingComidas.get(position).getNombre()));
         holder.txtpuntaje.setText(String.valueOf(listaRankingComidas.get(position).getPuntaje()));


    }

    @Override
    public int getItemCount() {
        return listaRankingComidas.size();
    }

    public class ComidaRankingHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;

        public ComidaRankingHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rc_id);
            txtusuario=itemView.findViewById(R.id.rc_usuario);
            txtpuntaje=itemView.findViewById(R.id.rc_puntaje);


        }
    }
}
