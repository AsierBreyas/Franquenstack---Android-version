package com.example.franquenstack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.ElementListActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.App;

import java.util.List;

public class AppCardAdapter extends RecyclerView.Adapter<AppCardAdapter.ViewHolder> {
    private List<App> listaApps;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        Button goDetails, goApp;
        ImageView estrella1, estrella2, estrella3, estrella4, estrella5, imgLogo, imgMedia;
        TextView notaApp, nombreApp;
        Context context;

        public ViewHolder(View view){
            super(view);
            context = view.getContext();
            goDetails = view.findViewById(R.id.buttonGoDetails);
            goApp = view.findViewById(R.id.buttonGoApp);

            estrella1 = view.findViewById(R.id.imageEstrella1);
            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
            estrella2 = view.findViewById(R.id.imageEstrella2);
            estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
            estrella3 = view.findViewById(R.id.imageEstrella3);
            estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
            estrella4 = view.findViewById(R.id.imageEstrella4);
            estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
            estrella5 = view.findViewById(R.id.imageEstrella5);
            estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
            imgLogo = view.findViewById(R.id.imageApp);
            imgMedia = view.findViewById(R.id.imageViewmediaEstrella);
            imgMedia.setImageResource(R.drawable.icons8_estrella_relleno_48);

            notaApp = view.findViewById(R.id.textViewNota);
            nombreApp = view.findViewById(R.id.textViewCardNombreApp);

            estrella1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    actualizarNota(1);
                }
            });
            estrella2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    actualizarNota(2);
                }
            });
            estrella3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    actualizarNota(3);
                }
            });
            estrella4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    actualizarNota(4);
                }
            });
            estrella5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    actualizarNota(5);
                }
            });
        }
        private void actualizarNota(int nota){
            //TODO mandar a la api nota actualizada
        }
        public void activarBotones(App app){
            goDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, AppDetailsActivity.class);
                    i.putExtra("App", app);
                    context.startActivity(i);
                }
            });

            goApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO mandar a la view a la aplicacion
                    Intent i = new Intent(context, ElementListActivity.class);
                    i.putExtra("App", app);
                    context.startActivity(i);
                }
            });
        }
    }
    public AppCardAdapter(List<App> dataset){
        listaApps = dataset;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_card, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        App app = listaApps.get(position);
        viewHolder.nombreApp.setText(app.getNombre());
        viewHolder.notaApp.setText(app.getMediaPuntos().toString());
        viewHolder.activarBotones(app);
        viewHolder.imgLogo.setImageResource(app.getImageId());
    }
    @Override
    public int getItemCount(){
        return listaApps.size();
    }
}
