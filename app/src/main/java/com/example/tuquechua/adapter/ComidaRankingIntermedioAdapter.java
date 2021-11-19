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

public class ComidaRankingIntermedioAdapter extends RecyclerView.Adapter<ComidaRankingIntermedioAdapter.ComidaRankingIntermedioHolder> {

    List<Ranking> listaRankingIntermedioComidas;

    public ComidaRankingIntermedioAdapter(List<Ranking> listaRankingIntermedioComidas) {
        this.listaRankingIntermedioComidas = listaRankingIntermedioComidas;
    }

    @NonNull
    @Override
    public ComidaRankingIntermedioAdapter.ComidaRankingIntermedioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_comida_intermedio, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ComidaRankingIntermedioHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaRankingIntermedioAdapter.ComidaRankingIntermedioHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingIntermedioComidas.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingIntermedioComidas.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingIntermedioComidas.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingIntermedioComidas.size();
    }

    public class ComidaRankingIntermedioHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public ComidaRankingIntermedioHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rci_id);
            txtusuario=itemView.findViewById(R.id.rci_usuario);
            txtpuntaje=itemView.findViewById(R.id.rci_puntaje);
        }
    }
}
