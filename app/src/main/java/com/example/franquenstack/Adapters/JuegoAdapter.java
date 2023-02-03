package com.example.franquenstack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.Comentario;
import com.example.franquenstack.modelos.Detalles;
import com.example.franquenstack.modelos.JuegoDetalles;

import java.util.List;

public class JuegoAdapter extends RecyclerView.Adapter<JuegoAdapter.ViewHolder> {
    private JuegoDetalles detalles;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView memory, os, graphics, storage, processor;
        public ViewHolder(View view){
            super(view);
            memory = view.findViewById(R.id.textViewMemory);
            os = view.findViewById(R.id.textViewOs);
            graphics = view.findViewById(R.id.textViewGraphics);
            storage = view.findViewById(R.id.textViewStorage);
            processor = view.findViewById(R.id.textViewProcessor);
        }
    }
    public JuegoAdapter(JuegoDetalles datos){ detalles = datos;}
    @Override
    public JuegoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.juego_card, viewGroup, false);
        return new JuegoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JuegoAdapter.ViewHolder viewHolder, final int position){
        viewHolder.memory.setText( viewHolder.memory.getText() + " " + detalles.getMemory());
        viewHolder.os.setText( viewHolder.os.getText() + " " + detalles.getOs());
        viewHolder.graphics.setText( viewHolder.graphics.getText() + " " + detalles.getGraphics());
        viewHolder.storage.setText( viewHolder.storage.getText() + " " + detalles.getStorage());
        viewHolder.processor.setText( viewHolder.processor.getText() + " " + detalles.getProcessor());
    }
    @Override
    public int getItemCount(){
        return 1;
    }
}
