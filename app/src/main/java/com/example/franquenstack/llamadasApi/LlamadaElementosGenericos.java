package com.example.franquenstack.llamadasApi;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.ElementGenericActivity;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.Elemento;
import com.example.franquenstack.modelos.ElementoGenerico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlamadaElementosGenericos {
    private  Context context;
    private ElementGenericActivity activity;
    public LlamadaElementosGenericos(Context context, ElementGenericActivity activity){
        this.context = context;
        this.activity = activity;
    }
    public void obtenerElemento(int idElemento) {
        StringRequest request = new StringRequest(Request.Method.GET,
                context.getString(R.string.obtenerElementoGenerico) + "?api=" + LoginActivity.sharedPreferences.getInt("appId", 1) + "&item=" + idElemento, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ElementoGenerico elementoGenerico;
                try {
                    JSONObject elemento = new JSONObject(response);
                    elementoGenerico = new ElementoGenerico(elemento);
                    activity.ponerElementos(elementoGenerico);
                } catch (JSONException e) {
                    e.printStackTrace();
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
    public void obtenerElemento(String nombreElemento) {
        StringRequest request = new StringRequest(Request.Method.GET,
                context.getString(R.string.obtenerElementoGenerico) + "?api=" + LoginActivity.sharedPreferences.getInt("appId", 1) + "&item=" + nombreElemento, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ElementoGenerico elementoGenerico;
                try {
                    JSONObject elemento = new JSONObject(response);
                    elementoGenerico = new ElementoGenerico(elemento);
                    activity.ponerElementos(elementoGenerico);
                } catch (JSONException e) {
                    e.printStackTrace();
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
