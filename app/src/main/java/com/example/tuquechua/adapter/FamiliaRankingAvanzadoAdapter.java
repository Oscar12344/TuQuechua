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

public class FamiliaRankingAvanzadoAdapter extends RecyclerView.Adapter<FamiliaRankingAvanzadoAdapter.FamiliaRankingAvanzadoHolder> {
    List<Ranking> listaRankingAvanzadoFamilias;

    public FamiliaRankingAvanzadoAdapter(List<Ranking> listaRankingAvanzadoFamilias) {
        this.listaRankingAvanzadoFamilias = listaRankingAvanzadoFamilias;
    }

    @NonNull
    @Override
    public FamiliaRankingAvanzadoAdapter.FamiliaRankingAvanzadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_familia_avanzado, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new FamiliaRankingAvanzadoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FamiliaRankingAvanzadoAdapter.FamiliaRankingAvanzadoHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingAvanzadoFamilias.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingAvanzadoFamilias.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingAvanzadoFamilias.size();
    }

    public class FamiliaRankingAvanzadoHolder extends RecyclerView.ViewHolder {
        TextView  txtusuario, txtpuntaje;
        public FamiliaRankingAvanzadoHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rfa_usuario);
            txtpuntaje=itemView.findViewById(R.id.rfa_puntaje);
        }
    }
}
