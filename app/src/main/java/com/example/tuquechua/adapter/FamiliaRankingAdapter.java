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

public class FamiliaRankingAdapter extends RecyclerView.Adapter<FamiliaRankingAdapter.FamiliaRankingHolder> {
    List<Ranking> listaRankingFamilias;

    public FamiliaRankingAdapter(List<Ranking> listaRankingFamilias) {
        this.listaRankingFamilias = listaRankingFamilias;
    }

    @NonNull
    @Override
    public FamiliaRankingAdapter.FamiliaRankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_familia, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new FamiliaRankingHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FamiliaRankingAdapter.FamiliaRankingHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaRankingFamilias.get(position).getIdrank()));
        holder.txtusuario.setText(String.valueOf(listaRankingFamilias.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingFamilias.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingFamilias.size();
    }

    public class FamiliaRankingHolder extends RecyclerView.ViewHolder{
        TextView txtid, txtusuario, txtpuntaje;
        public FamiliaRankingHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.rf_id);
            txtusuario=itemView.findViewById(R.id.rf_usuario);
            txtpuntaje=itemView.findViewById(R.id.rf_puntaje);
        }
    }
}
