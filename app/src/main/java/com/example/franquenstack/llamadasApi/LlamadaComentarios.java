package com.example.franquenstack.llamadasApi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.AppDetailsActivity;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.Comentario;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LlamadaComentarios {
    Context context;

    public LlamadaComentarios(Context context) {
        this.context = context;
    }
    public void publicarComentarioApp(Comentario comentario, int appId){
        HashMap data = new HashMap();
        data.put("comment_text", comentario.getComment_text());
        data.put("hora", comentario.getHora());
        data.put("app_id", appId + "");
        StringRequest request = new StringRequest(Request.Method.POST, context.getString(R.string.postearComentarioApp), new Response.Listener<String>() {
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
    public void publicarComentario(Comentario comentario, int elementoId){
        HashMap data = new HashMap();
        data.put("comment_text", comentario.getComment_text());
        data.put("hora", comentario.getHora());
        data.put("elemento_id", elementoId + "");
        data.put("app_id", LoginActivity.sharedPreferences.getInt("appId", 0) + "");
        StringRequest request = new StringRequest(Request.Method.POST, context.getString(R.string.postearComentario), new Response.Listener<String>() {
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
}
