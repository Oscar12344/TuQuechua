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

public class NumeroRankingBasicoAdapter extends RecyclerView.Adapter<NumeroRankingBasicoAdapter.NumeroRankingBasicoHolder>{
    List<Ranking> listaRankingBasicoNumeros;

    public NumeroRankingBasicoAdapter(List<Ranking> listaRankingBasicoNumeros) {

        this.listaRankingBasicoNumeros = listaRankingBasicoNumeros;
    }


    @NonNull
    @Override
    public NumeroRankingBasicoAdapter.NumeroRankingBasicoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_numero_basico, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new NumeroRankingBasicoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NumeroRankingBasicoAdapter.NumeroRankingBasicoHolder holder, int position) {

        holder.txtusuario.setText(String.valueOf(listaRankingBasicoNumeros.get(position).getNombre()));
        holder.txtpuntaje.setText(String.valueOf(listaRankingBasicoNumeros.get(position).getPuntaje()));
    }

    @Override
    public int getItemCount() {
        return listaRankingBasicoNumeros.size();
    }

    public class NumeroRankingBasicoHolder extends RecyclerView.ViewHolder {
        TextView  txtusuario, txtpuntaje;
        public NumeroRankingBasicoHolder(@NonNull View itemView) {
            super(itemView);

            txtusuario=itemView.findViewById(R.id.rnb_usuario);
            txtpuntaje=itemView.findViewById(R.id.rnb_puntaje);
        }
    }
}
