package com.example.tuquechua.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Ranking;

import java.util.List;

public class ComidaRankingAvanzadoAdapter extends RecyclerView.Adapter<ComidaRankingAvanzadoAdapter.ComidaRankingAvanzadoHolder> {
    List<Ranking> listaRankingAvanzadoComidas;

    public ComidaRankingAvanzadoAdapter(List<Ranking> listaRankingAvanzadoComidas) {
        this.listaRankingAvanzadoComidas = listaRankingAvanzadoComidas;
    }

    @NonNull
    @Override
    public ComidaRankingAvanzadoAdapter.ComidaRankingAvanzadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_comida_avanzado, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ComidaRankingAvanzadoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaRankingAvanzadoAdapter.ComidaRankingAvanzadoHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingAvanzadoComidas.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingAvanzadoComidas.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingAvanzadoComidas.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingAvanzadoComidas.size();
    }

    public class ComidaRankingAvanzadoHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public ComidaRankingAvanzadoHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rca_id);
            txtusuario=itemView.findViewById(R.id.rca_usuario);
            txtpuntaje=itemView.findViewById(R.id.rca_puntaje);
        }
    }
}
