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

public class FamiliaRankingIntermedioAdapter extends  RecyclerView.Adapter<FamiliaRankingIntermedioAdapter.FamiliaRankingIntermedioHolder>{
    List<Ranking> listaRankingIntermedioFamilias;

    public FamiliaRankingIntermedioAdapter(List<Ranking> listaRankingIntermedioFamilias) {
        this.listaRankingIntermedioFamilias = listaRankingIntermedioFamilias;
    }

    @NonNull
    @Override
    public FamiliaRankingIntermedioAdapter.FamiliaRankingIntermedioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_familia_intermedio, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new FamiliaRankingIntermedioHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FamiliaRankingIntermedioAdapter.FamiliaRankingIntermedioHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingIntermedioFamilias.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingIntermedioFamilias.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingIntermedioFamilias.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingIntermedioFamilias.size();
    }

    public class FamiliaRankingIntermedioHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtusuario, txtpuntaje;
        public FamiliaRankingIntermedioHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rfi_id);
            txtusuario=itemView.findViewById(R.id.rfi_usuario);
            txtpuntaje=itemView.findViewById(R.id.rfi_puntaje);
        }
    }
}
