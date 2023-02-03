package com.example.franquenstack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.Comentario;
import com.example.franquenstack.modelos.Elemento;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {
    private List<Comentario> comentarios;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, texto;
        public ViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.textViewComentarioNombreHora);
            texto = view.findViewById(R.id.textViewComentario);
        }
    }
    public ComentariosAdapter(List<Comentario> datos){ comentarios = datos;}
    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comentario, viewGroup, false);
        return new ComentariosAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ComentariosAdapter.ViewHolder viewHolder, final int position){
        Comentario comentario = comentarios.get(position);
        if (comentario.getUsername().equals(LoginActivity.sharedPreferences.getString("username", "Eustaquio")))
            viewHolder.nombre.setText("You - " + comentario.getHora());
        else
            viewHolder.nombre.setText(comentario.getUsername() + " - " + comentario.getHora());
        viewHolder.texto.setText(comentario.getComment_text());
    }
    @Override
    public int getItemCount(){
        return comentarios.size();
    }
}
