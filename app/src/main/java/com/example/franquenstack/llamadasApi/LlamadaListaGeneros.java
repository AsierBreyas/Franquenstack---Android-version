package com.example.franquenstack.llamadasApi;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.ElementListActivity;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.Elemento;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlamadaListaGeneros {
    Context context;
    public LlamadaListaGeneros(Context context){
        this.context = context;
    }
    public void llamarGeneros(ElementListActivity activity){
        StringRequest request = new StringRequest(Request.Method.GET,
                context.getString(R.string.listaGeneros) + "?app_id=" + LoginActivity.sharedPreferences.getInt("appId", 1), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> generos = new ArrayList<>();
                generos.add("No filter");
                try {
                    JSONArray arrayElementos = new JSONArray(response);
                    for (int i = 0; i < arrayElementos.length(); i++)
                        generos.add(arrayElementos.getString(i).substring(0,1).toUpperCase() + arrayElementos.getString(i).substring(1));
                    activity.ponerSpiner(generos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        };
        ApplicationController.getInstance().addToRequestQueue(request);
    }
}
