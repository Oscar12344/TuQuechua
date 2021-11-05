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

public class SaludoRankingAdapter extends RecyclerView.Adapter<SaludoRankingAdapter.SaludoRankingHolder>{
    List<Ranking> listaRankingSaludos;

    public SaludoRankingAdapter(List<Ranking> listaRankingSaludos) {

        this.listaRankingSaludos = listaRankingSaludos;
    }

    @NonNull
    @Override
    public SaludoRankingAdapter.SaludoRankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_saludo, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new SaludoRankingHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SaludoRankingAdapter.SaludoRankingHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingSaludos.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingSaludos.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingSaludos.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingSaludos.size();
    }

    public class SaludoRankingHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public SaludoRankingHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rs_id);
            txtusuario=itemView.findViewById(R.id.rs_usuario);
            txtpuntaje=itemView.findViewById(R.id.rs_puntaje);
        }
    }
}
