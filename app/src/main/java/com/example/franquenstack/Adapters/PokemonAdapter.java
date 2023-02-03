package com.example.franquenstack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.R;
import com.example.franquenstack.modelos.JuegoDetalles;
import com.example.franquenstack.modelos.PokemonDetalles;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private PokemonDetalles detalles;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView height, width, habilidades, hp, atk, def, spAtk, spDef, spd, habilidadesTitulo;
        public ViewHolder(View view){
            super(view);
            height = view.findViewById(R.id.textViewPokemonHeight);
            width = view.findViewById(R.id.textViewPokemonWidth);
            habilidades = view.findViewById(R.id.textViewAbilityList);
            hp = view.findViewById(R.id.textViewHpValue);
            atk = view.findViewById(R.id.textViewAtValue);
            def = view.findViewById(R.id.textViewDefVal);
            spAtk = view.findViewById(R.id.textViewSpAtVal);
            spDef = view.findViewById(R.id.textViewSpDefVal);
            spd = view.findViewById(R.id.textViewSpdVal);
            habilidadesTitulo = view.findViewById(R.id.textViewAbilityText);
        }
    }
    public PokemonAdapter(PokemonDetalles datos){ detalles = datos;}
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_card, viewGroup, false);
        return new PokemonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonAdapter.ViewHolder viewHolder, final int position){
        viewHolder.height.setText(viewHolder.height.getText() + " " + detalles.getHeight());
        viewHolder.width.setText(viewHolder.width.getText() + " " + detalles.getWeigth());
        viewHolder.hp.setText(detalles.getHp() + "");
        viewHolder.atk.setText(detalles.getAttack() + "");
        viewHolder.def.setText(detalles.getDefense() + "");
        viewHolder.spAtk.setText(detalles.getSp_attack() + "");
        viewHolder.spDef.setText(detalles.getSp_defense() + "");
        viewHolder.spd.setText(detalles.getSpeed() + "");
        for (int i = 0; i < detalles.getHabilidades().size(); i++){
            if (i == 0)
                viewHolder.habilidades.setText("- " + detalles.getHabilidades().get(i));
            else{
                viewHolder.habilidades.setText(viewHolder.habilidades.getText() + "\n - " +detalles.getHabilidades().get(i));
                viewHolder.habilidadesTitulo.setText("Abilities:");
            }
        }
    }
    @Override
    public int getItemCount(){
        return 1;
    }
}
