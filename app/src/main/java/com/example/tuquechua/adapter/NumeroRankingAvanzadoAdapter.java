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

public class NumeroRankingAvanzadoAdapter extends RecyclerView.Adapter<NumeroRankingAvanzadoAdapter.NumeroRankingAvanzadoHolder> {
    List<Ranking> listaRankingAvanzadoNumeros;

    public NumeroRankingAvanzadoAdapter(List<Ranking> listaRankingAvanzadoNumeros) {
        this.listaRankingAvanzadoNumeros = listaRankingAvanzadoNumeros;
    }

    @NonNull
    @Override
    public NumeroRankingAvanzadoAdapter.NumeroRankingAvanzadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_numero_avanzado, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new NumeroRankingAvanzadoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NumeroRankingAvanzadoAdapter.NumeroRankingAvanzadoHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingAvanzadoNumeros.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingAvanzadoNumeros.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingAvanzadoNumeros.size();
    }

    public class NumeroRankingAvanzadoHolder  extends RecyclerView.ViewHolder{
        TextView  txtusuario, txtpuntaje;
        public NumeroRankingAvanzadoHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rna_usuario);
            txtpuntaje=itemView.findViewById(R.id.rna_puntaje);
        }
    }
}
