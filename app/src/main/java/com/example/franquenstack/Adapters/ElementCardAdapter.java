package com.example.franquenstack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.Elemento;

import java.util.List;

public class ElementCardAdapter extends RecyclerView.Adapter<ElementCardAdapter.ViewHolder>{
    private List<Elemento> elementos;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fav, elementImage;
        TextView elementNombre;
        Button viewDetails;
        Context context;
        boolean estaFaveado;
        public ViewHolder(View view){
            super(view);
            fav = view.findViewById(R.id.imageViewElementListFav);
            elementImage = view.findViewById(R.id.imageViewElementList);
            //TODO si es fav cambiar el icono al corazon rojo
            elementImage.setImageResource(R.drawable.fav);
            estaFaveado = false;
            elementNombre = view.findViewById(R.id.textViewElementName);
            viewDetails = view.findViewById(R.id.buttonGoDetails);
            context = view.getContext();

            elementImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!estaFaveado)
                        elementImage.setImageResource(R.drawable.fav_relleno);
                    else
                        elementImage.setImageResource(R.drawable.fav);
                    estaFaveado = ! estaFaveado;
                    actualizarFav(estaFaveado);
                }
            });
        }
        public void actualizarFav(boolean hayQueAñadir){
            //TODO
        }
        public void activarBotonDetalles(Elemento elemento){
            viewDetails.setOnClickListener(new View.OnClickListener() {
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
    public ElementCardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_card, viewGroup, false);
        return new ElementCardAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ElementCardAdapter.ViewHolder viewHolder, final int position){
        Elemento elemento = elementos.get(position);
        viewHolder.elementNombre.setText(elemento.getName());
        viewHolder.activarBotonDetalles(elemento);
        //TODO poner imagen con piccasso
    }
    @Override
    public int getItemCount(){
        return elementos.size();
    }
}
