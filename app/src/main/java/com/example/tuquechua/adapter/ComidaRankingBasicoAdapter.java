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

public class ComidaRankingBasicoAdapter extends RecyclerView.Adapter<ComidaRankingBasicoAdapter.ComidaRankingBasicoHolder>{
    List<Ranking> listaRankingBasicoComidas;

    public ComidaRankingBasicoAdapter(List<Ranking> listaRankingBasicoComidas) {
        this.listaRankingBasicoComidas = listaRankingBasicoComidas;
    }

    @NonNull
    @Override
    public ComidaRankingBasicoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_comida_basico, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ComidaRankingBasicoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaRankingBasicoHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingBasicoComidas.get(position).getNombre()));
         holder.txtpuntaje.setText(String.valueOf(listaRankingBasicoComidas.get(position).getPuntaje()));


    }

    @Override
    public int getItemCount() {
        return listaRankingBasicoComidas.size();
    }

    public class ComidaRankingBasicoHolder extends RecyclerView.ViewHolder {
        TextView  txtusuario, txtpuntaje;

        public ComidaRankingBasicoHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rcb_usuario);
            txtpuntaje=itemView.findViewById(R.id.rcb_puntaje);


        }
    }
}
