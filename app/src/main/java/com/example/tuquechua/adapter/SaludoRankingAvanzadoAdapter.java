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

public class SaludoRankingAvanzadoAdapter extends RecyclerView.Adapter<SaludoRankingAvanzadoAdapter.SaludoRankingAvanzadoHolder> {
    List<Ranking> listaRankingAvanzadoSaludos;

    public SaludoRankingAvanzadoAdapter(List<Ranking> listaRankingAvanzadoSaludos) {
        this.listaRankingAvanzadoSaludos = listaRankingAvanzadoSaludos;
    }

    @NonNull
    @Override
    public SaludoRankingAvanzadoAdapter.SaludoRankingAvanzadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_saludo_avanzado, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new SaludoRankingAvanzadoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SaludoRankingAvanzadoAdapter.SaludoRankingAvanzadoHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingAvanzadoSaludos.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingAvanzadoSaludos.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingAvanzadoSaludos.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingAvanzadoSaludos.size();
    }

    public class SaludoRankingAvanzadoHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public SaludoRankingAvanzadoHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rsa_id);
            txtusuario=itemView.findViewById(R.id.rsa_usuario);
            txtpuntaje=itemView.findViewById(R.id.rsa_puntaje);
        }
    }
}
