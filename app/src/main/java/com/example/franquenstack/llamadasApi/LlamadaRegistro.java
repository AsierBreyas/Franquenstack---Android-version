package com.example.franquenstack.llamadasApi;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LlamadaRegistro extends LlamarApi{
    Context context;
    Activity activity;
    public LlamadaRegistro(String url, Context context, Activity activity){
        setStringUrl(url);
        this.context = context;
        this.activity = activity;
    }
    public void registrarse(String nombre, String password, String email){
        HashMap data = new HashMap();
        data.put("nombre", nombre);
        data.put("password", password);
        data.put("email", email);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, context.getString(R.string.Registrarse), new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null){
                    Toast.makeText(context, "Registrado", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }else{
                    Toast.makeText(context, "No lo conseguiste", Toast.LENGTH_SHORT).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        ApplicationController.getInstance().addToRequestQueue(request);
    }
}
