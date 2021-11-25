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

public class FamiliaRankingBasicoAdapter extends RecyclerView.Adapter<FamiliaRankingBasicoAdapter.FamiliaRankingBasicoHolder> {
    List<Ranking> listaRankingBasicoFamilias;

    public FamiliaRankingBasicoAdapter(List<Ranking> listaRankingBasicoFamilias) {
        this.listaRankingBasicoFamilias = listaRankingBasicoFamilias;
    }

    @NonNull
    @Override
    public FamiliaRankingBasicoAdapter.FamiliaRankingBasicoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_familia_basico, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new FamiliaRankingBasicoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FamiliaRankingBasicoAdapter.FamiliaRankingBasicoHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingBasicoFamilias.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingBasicoFamilias.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingBasicoFamilias.size();
    }

    public class FamiliaRankingBasicoHolder extends RecyclerView.ViewHolder{
        TextView  txtusuario, txtpuntaje;
        public FamiliaRankingBasicoHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rfb_usuario);
            txtpuntaje=itemView.findViewById(R.id.rfb_puntaje);
        }
    }
}
