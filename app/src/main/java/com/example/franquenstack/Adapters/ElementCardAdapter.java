package com.example.franquenstack.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.Controladores.FavList;
import com.example.franquenstack.ElementGenericActivity;
import com.example.franquenstack.ElementListActivity;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.llamadasApi.LlamadaFavoritos;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.Elemento;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ElementCardAdapter extends RecyclerView.Adapter<ElementCardAdapter.ViewHolder2>{
    private List<Elemento> elementos;
    ElementListActivity activity;
    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        ImageView elementImage;
        TextView elementNombre;
        ConstraintLayout layout;
        Context context;
        boolean faveado;
        public ViewHolder2(View view){
            super(view);
            elementImage = view.findViewById(R.id.imageViewElementList);
            elementNombre = view.findViewById(R.id.textViewElementName);
            layout = view.findViewById(R.id.constraintLayoutElementCard);
            context = view.getContext();
        }
        public void activarBotonDetalles(Elemento elemento){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ElementGenericActivity.class);
                    i.putExtra("ElementoId", elemento.getId());
                    context.startActivity(i);
                }
            });
        }

    }
    public ElementCardAdapter(List<Elemento> elementos, ElementListActivity activity){
        this.elementos = elementos;
        this.activity = activity;
    }
    @Override
    public ElementCardAdapter.ViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.element_card, viewGroup, false);
        return new ElementCardAdapter.ViewHolder2(view);
    }
    @Override
    public void onBindViewHolder(ElementCardAdapter.ViewHolder2 viewHolder, final int position){
        Elemento elemento = elementos.get(position);
        viewHolder.elementNombre.setText(elemento.getName());
        viewHolder.activarBotonDetalles(elemento);
        Picasso.get().load(elemento.getImagen()).into(viewHolder.elementImage);
        viewHolder.faveado = false;
    }
    @Override
    public int getItemCount(){
        return elementos.size();
    }
}
