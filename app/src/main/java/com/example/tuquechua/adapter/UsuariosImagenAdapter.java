package com.example.tuquechua.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuquechua.R;
import com.example.tuquechua.entidades.Usuario;

import java.util.List;

public class UsuariosImagenAdapter extends RecyclerView.Adapter<UsuariosImagenAdapter.UsuariosHolder>{
    List<Usuario> listaUsuarios;
    public UsuariosImagenAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list_image,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosHolder holder, int position) {
        holder.txtDocumento.setText(listaUsuarios.get(position).getDocumento().toString());
        holder.txtNombre.setText(listaUsuarios.get(position).getNombre().toString());
        holder.txtProfesion.setText(listaUsuarios.get(position).getProfesion().toString());
        if (listaUsuarios.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listaUsuarios.get(position).getImagen());
        }else{
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends  RecyclerView.ViewHolder{
        TextView txtDocumento,txtNombre,txtProfesion;
        ImageView imagen;

        public UsuariosHolder(View itemView) {
            super(itemView);
            txtDocumento= (TextView) itemView.findViewById(R.id.idDocumento);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            txtProfesion= (TextView) itemView.findViewById(R.id.idProfesion);
            imagen=(ImageView) itemView.findViewById(R.id.idImagen);

        }

    }

}
