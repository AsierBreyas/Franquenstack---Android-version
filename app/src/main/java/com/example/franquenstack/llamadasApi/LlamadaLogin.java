package com.example.franquenstack.llamadasApi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.franquenstack.AppListActivity;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LlamadaLogin extends LlamarApi {
    Context context;
    Activity activity;

    public LlamadaLogin(String url, Context context, Activity activity) {
        iniciar(url);
        this.context = context;
    }

    public LlamadaLogin(Context context, Activity activity) {
        iniciar("");
        this.context = context;
        this.activity = activity;
    }

    private void iniciar(String url) {
        if (url == "" || url == null)
            url = "http://10.10.12.87:8080/auth/login";
        setStringUrl(url);
    }

    public void logearse(String username, String password) {
        HashMap data = new HashMap();
        data.put("username", username);
        data.put("password", password);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, context.getString(R.string.Login), new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    try {
                        Toast.makeText(context, "Registrado", Toast.LENGTH_SHORT).show();
                        LoginActivity.editor.putString("token", response.getString("accessToken"));
                        LoginActivity.editor.putString("username", response.getString("username"));
                        LoginActivity.editor.putString("user_id", response.getString("user_id"));
                        LoginActivity.editor.apply();
                        activity.startActivity(new Intent(context, AppListActivity.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
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
