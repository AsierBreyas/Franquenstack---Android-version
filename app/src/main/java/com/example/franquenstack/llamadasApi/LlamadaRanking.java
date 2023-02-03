package com.example.franquenstack.llamadasApi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LlamadaRanking {
    Context context;

    public LlamadaRanking(Context context) {
        this.context = context;
    }

    public void actualizarRanking(int nota){

    }
}
