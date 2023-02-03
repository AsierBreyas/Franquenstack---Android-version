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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.ElementListActivity;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.ElementoGenerico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            estrella2 = view.findViewById(R.id.imageEstrella2);
            estrella3 = view.findViewById(R.id.imageEstrella3);
            estrella4 = view.findViewById(R.id.imageEstrella4);
            estrella5 = view.findViewById(R.id.imageEstrella5);
            imgLogo = view.findViewById(R.id.imageApp);
            imgMedia = view.findViewById(R.id.imageViewmediaEstrella);
            imgMedia.setImageResource(R.drawable.icons8_estrella_relleno_48);

            notaApp = view.findViewById(R.id.textViewNota);
            nombreApp = view.findViewById(R.id.textViewCardNombreApp);

        }
        private void actualizarNota(int nota, int appId){
            HashMap data = new HashMap();
            data.put("app_id", Integer.toString(appId));
            data.put("puntos", Integer.toString(nota));
            StringRequest request = new StringRequest(Request.Method.POST, context.getString(R.string.ranking), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Nota actualizada", Toast.LENGTH_SHORT);
                    Float puntuacion = Float.parseFloat(response);
                    notaApp.setText(String.format("%.2f", puntuacion));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + LoginActivity.sharedPreferences.getString("token", null));
                    return params;
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    return new JSONObject(data).toString().getBytes();
                }
                @Override
                public String getBodyContentType(){
                    return "application/json";
                }
            };
            ApplicationController.getInstance().addToRequestQueue(request);
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
                    Intent i = new Intent(context, ElementListActivity.class);
                    i.putExtra("App", app);
                    context.startActivity(i);
                }
            });
            estrella1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                    estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                    actualizarNota(1, app.getId());
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
                    actualizarNota(2, app.getId());
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
                    actualizarNota(3, app.getId());
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
                    actualizarNota(4, app.getId());
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
                    actualizarNota(5, app.getId());
                }
            });
        }
        public void ponerEstrellitas(App app){
            StringRequest request = new StringRequest(Request.Method.GET,
                    context.getString(R.string.listRanking), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        try {
                            JSONArray appLista = new JSONArray(response);
                            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                            estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                            estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                            estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                            estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48__1_);
                            for (int i = 0; i < appLista.length(); i++) {
                                JSONObject object = appLista.getJSONObject(i);
                                if (object.getString("app_id").equals(Integer.toString(app.getId()))) {
                                    switch (Math.round(Float.parseFloat(object.getString("puntos")))) {
                                        case 1:
                                            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            break;
                                        case 2:
                                            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            break;
                                        case 3:
                                            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            break;
                                        case 4:
                                            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            break;
                                        case 5:
                                            estrella1.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella2.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella3.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella4.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            estrella5.setImageResource(R.drawable.icons8_estrella_relleno_48);
                                            break;
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + LoginActivity.sharedPreferences.getString("token", null));
                    return params;
                }
            };
            ApplicationController.getInstance().addToRequestQueue(request);
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
        viewHolder.notaApp.setText(String.format("%.2f", app.getMediaPuntos()));
        viewHolder.activarBotones(app);
        viewHolder.imgLogo.setImageResource(app.getImageId());
        viewHolder.ponerEstrellitas(app);
    }
    @Override
    public int getItemCount(){
        return listaApps.size();
    }
}
