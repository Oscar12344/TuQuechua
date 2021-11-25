package com.example.tuquechua.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Diccionario;
import com.example.tuquechua.entidades.Ranking;

import java.util.List;

public class DiccionarioAdapter extends RecyclerView.Adapter<DiccionarioAdapter.DiccinarioHolder> {
    List<Diccionario> listaDiccionarios;

    public DiccionarioAdapter(List<Diccionario> listaDiccionarios) {
        this.listaDiccionarios = listaDiccionarios;
    }

    @NonNull
    @Override
    public DiccionarioAdapter.DiccinarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.diccionario, parent, false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new DiccinarioHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DiccionarioAdapter.DiccinarioHolder holder, int position) {

        holder.txtquechua.setText(String.valueOf(listaDiccionarios.get(position).getDicPalabraQuechua()));
        holder.txtespanol.setText(String.valueOf(listaDiccionarios.get(position).getDicSignificado()));
    }

    @Override
    public int getItemCount() {
        return listaDiccionarios.size();
    }

    public class DiccinarioHolder extends RecyclerView.ViewHolder {
        TextView  txtquechua, txtespanol;
        public DiccinarioHolder(@NonNull View itemView) {
            super(itemView);

            txtquechua=itemView.findViewById(R.id.d_quechua);
            txtespanol=itemView.findViewById(R.id.d_espanol);
        }
    }
}
