package com.example.franquenstack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.Elemento;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ElementCardAdapter extends RecyclerView.Adapter<ElementCardAdapter.ViewHolder2>{
    private List<Elemento> elementos;
    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        ImageView fav, elementImage;
        TextView elementNombre;
        ConstraintLayout layout;
        Context context;
        boolean estaFaveado;

        public ViewHolder2(View view){
            super(view);
            fav = view.findViewById(R.id.imageViewElementListFav);
            elementImage = view.findViewById(R.id.imageViewElementList);

            //TODO si es fav cambiar el icono al corazon rojo
            fav.setImageResource(R.drawable.fav);
            estaFaveado = false;
            elementNombre = view.findViewById(R.id.textViewElementName);
            layout = view.findViewById(R.id.constraintLayoutElementCard);
            context = view.getContext();

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!estaFaveado)
                        fav.setImageResource(R.drawable.fav_relleno);
                    else
                        fav.setImageResource(R.drawable.fav);
                    estaFaveado = ! estaFaveado;
                    actualizarFav(estaFaveado);
                }
            });
        }
        public void actualizarFav(boolean hayQueAñadir){
            //TODO
        }
        public void activarBotonDetalles(Elemento elemento){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent i = new Intent(context, AppDetailsActivity.class);
                    i.putExtra("ElementoId", elemento.getId());*/
                }
            });
        }

    }
    public ElementCardAdapter(List<Elemento> elementos){ this.elementos = elementos; }
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
    }
    @Override
    public int getItemCount(){
        return elementos.size();
    }
}
