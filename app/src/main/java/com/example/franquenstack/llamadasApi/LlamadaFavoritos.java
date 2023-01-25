package com.example.franquenstack.llamadasApi;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.Controladores.FavList;
import com.example.franquenstack.ElementListActivity;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlamadaFavoritos {
    private Context context;
    public ElementListActivity activity;
    public LlamadaFavoritos(Context context){
        this.context = context;
    }
    public LlamadaFavoritos(Context context, ElementListActivity activity){
        this.context = context;
        this.activity = activity;
    }
    public void cambiarFavorito(int elementId){
        HashMap data = new HashMap();
        data.put("elemento_id", Integer.toString(elementId));
        data.put("app_id", Integer.toString(LoginActivity.sharedPreferences.getInt("appId", 0)));
        StringRequest request = new StringRequest(Request.Method.POST, context.getString(R.string.favoritos), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String texto = response.toString();
                Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();
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
    public void llamarListaFavoritos(){
        StringRequest request = new StringRequest(Request.Method.GET,
                context.getString(R.string.favoritosLista) + "?app_id=" + LoginActivity.sharedPreferences.getInt("appId", 0),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray favoritos = new JSONArray(response);
                    for(int i = 0; i < favoritos.length(); i++){
                        FavList.favList.add(favoritos.getInt(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
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
