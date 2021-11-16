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

public class NumeroRankingAdapter extends RecyclerView.Adapter<NumeroRankingAdapter.NumeroRankingHolder>{
    List<Ranking> listaRankingNumeros;

    public NumeroRankingAdapter(List<Ranking> listaRankingNumeros) {

        this.listaRankingNumeros = listaRankingNumeros;
    }


    @NonNull
    @Override
    public NumeroRankingAdapter.NumeroRankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_numero, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new NumeroRankingHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NumeroRankingAdapter.NumeroRankingHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingNumeros.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingNumeros.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingNumeros.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingNumeros.size();
    }

    public class NumeroRankingHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public NumeroRankingHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rn_id);
            txtusuario=itemView.findViewById(R.id.rn_usuario);
            txtpuntaje=itemView.findViewById(R.id.rn_puntaje);
        }
    }
}
