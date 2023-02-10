package com.example.franquenstack.llamadasApi;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.AppListActivity;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlamadaListaApps{
    Context context;
    AppListActivity activity;
    private String token;
    AppDetailsActivity activity2;
    public LlamadaListaApps(Context context, AppListActivity activity, String token){
        this.context = context;
        this.activity = activity;
        this.token = token;
    }
    public LlamadaListaApps(Context context, AppDetailsActivity activity){
        this.context = context;
        activity2 = activity;
    }
    public void obtenerListado(){
        StringRequest request = new StringRequest(Request.Method.GET, context.getString(R.string.listaApps), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<App> apps = new ArrayList<>();
                try {
                    JSONArray appLista = new JSONArray(response);
                    for (int i = 0; i < appLista.length(); i++){
                        App app = new App(appLista.getJSONObject(i).getInt("app_id"),
                                appLista.getJSONObject(i).getString("descripcion"),
                                appLista.getJSONObject(i).getString("nombre"),
                                appLista.getJSONObject(i).getDouble("mediaPuntos"));
                        apps.add(app);
                    }
                    activity.enseñarListado(apps);
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
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + LoginActivity.sharedPreferences.getString("token", null));
                return params;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(request);
    }
    public void obtenerApp(int appIDd){
        StringRequest request = new StringRequest(Request.Method.GET, context.getString(R.string.obtenerApp) + "?app_id=" + appIDd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    App app = new App(new JSONObject(response));
                    activity2.ponerApps(app);
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
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + LoginActivity.sharedPreferences.getString("token", null));
                return params;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(request);
    }
}
