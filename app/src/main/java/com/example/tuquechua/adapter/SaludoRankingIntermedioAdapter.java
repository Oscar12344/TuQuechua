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

public class SaludoRankingIntermedioAdapter  extends RecyclerView.Adapter<SaludoRankingIntermedioAdapter.SaludoRankingIntermedioHolder>{
    List<Ranking> listaRankingIntermedioSaludos;

    public SaludoRankingIntermedioAdapter(List<Ranking> listaRankingIntermedioSaludos) {
        this.listaRankingIntermedioSaludos = listaRankingIntermedioSaludos;
    }

    @NonNull
    @Override
    public SaludoRankingIntermedioAdapter.SaludoRankingIntermedioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_saludo_intermedio, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new SaludoRankingIntermedioHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SaludoRankingIntermedioAdapter.SaludoRankingIntermedioHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingIntermedioSaludos.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingIntermedioSaludos.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingIntermedioSaludos.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingIntermedioSaludos.size();
    }

    public class SaludoRankingIntermedioHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public SaludoRankingIntermedioHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rsi_id);
            txtusuario=itemView.findViewById(R.id.rsi_usuario);
            txtpuntaje=itemView.findViewById(R.id.rsi_puntaje);
        }
    }
}
