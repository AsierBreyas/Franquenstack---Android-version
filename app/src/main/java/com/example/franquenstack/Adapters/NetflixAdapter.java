package com.example.franquenstack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.R;
import com.example.franquenstack.modelos.NetflixDetalles;
import com.example.franquenstack.modelos.PokemonDetalles;

public class NetflixAdapter extends RecyclerView.Adapter<NetflixAdapter.ViewHolder> {
    private NetflixDetalles detalles;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView diasTitulo, dias, start, finish, hour, duration;
        public ViewHolder(View view){
            super(view);
            diasTitulo = view.findViewById(R.id.textViewDiasTexto);
            dias = view.findViewById(R.id.textViewDayList);
            start = view.findViewById(R.id.textViewNetflixStart);
            finish = view.findViewById(R.id.textViewNetflixFinish);
            hour = view.findViewById(R.id.textViewNetflixHour);
            duration = view.findViewById(R.id.textViewNetflixDuration);
        }
    }
    public NetflixAdapter(NetflixDetalles datos){ detalles = datos;}
    @Override
    public NetflixAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.netflix_card, viewGroup, false);
        return new NetflixAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NetflixAdapter.ViewHolder viewHolder, final int position){
        viewHolder.start.setText("Start " + detalles.getInicio());
        viewHolder.finish.setText("Finish: " + detalles.getFin());
        viewHolder.hour.setText(viewHolder.hour.getText() + " " + detalles.getHora());
        viewHolder.duration.setText(viewHolder.duration.getText() + " " + detalles.getDuracion());
        for (int i = 0; i < detalles.getDias().size(); i++){
            if (i == 0)
                viewHolder.dias.setText("- " + detalles.getDias().get(i));
            else{
                viewHolder.dias.setText(viewHolder.dias.getText() + "\n - " +detalles.getDias().get(i));
                viewHolder.dias.setText("Days:");
            }
        }
    }
    @Override
    public int getItemCount(){
        return 1;
    }
}

