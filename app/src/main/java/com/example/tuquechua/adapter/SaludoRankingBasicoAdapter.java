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

public class SaludoRankingBasicoAdapter extends RecyclerView.Adapter<SaludoRankingBasicoAdapter.SaludoRankingBasicoHolder>{
    List<Ranking> listaRankingBasicoSaludos;

    public SaludoRankingBasicoAdapter(List<Ranking> listaRankingBasicoSaludos) {

        this.listaRankingBasicoSaludos = listaRankingBasicoSaludos;
    }

    @NonNull
    @Override
    public SaludoRankingBasicoAdapter.SaludoRankingBasicoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_saludo_basico, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new SaludoRankingBasicoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SaludoRankingBasicoAdapter.SaludoRankingBasicoHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingBasicoSaludos.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingBasicoSaludos.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingBasicoSaludos.size();
    }

    public class SaludoRankingBasicoHolder extends RecyclerView.ViewHolder {
        TextView  txtusuario, txtpuntaje;
        public SaludoRankingBasicoHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rsb_usuario);
            txtpuntaje=itemView.findViewById(R.id.rsb_puntaje);
        }
    }
}
