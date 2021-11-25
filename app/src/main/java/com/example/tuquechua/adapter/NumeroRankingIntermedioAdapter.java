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

public class NumeroRankingIntermedioAdapter extends RecyclerView.Adapter<NumeroRankingIntermedioAdapter.NumeroRankingIntermedioHolder> {
    List<Ranking> listaRankingIntermedioNumeros;

    public NumeroRankingIntermedioAdapter(List<Ranking> listaRankingIntermedioNumeros) {
        this.listaRankingIntermedioNumeros = listaRankingIntermedioNumeros;
    }

    @NonNull
    @Override
    public NumeroRankingIntermedioAdapter.NumeroRankingIntermedioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_numero_intermedio, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new NumeroRankingIntermedioHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NumeroRankingIntermedioAdapter.NumeroRankingIntermedioHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingIntermedioNumeros.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingIntermedioNumeros.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingIntermedioNumeros.size();
    }

    public class NumeroRankingIntermedioHolder extends RecyclerView.ViewHolder {
        TextView  txtusuario, txtpuntaje;
        public NumeroRankingIntermedioHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rni_usuario);
            txtpuntaje=itemView.findViewById(R.id.rni_puntaje);
        }
    }
}
