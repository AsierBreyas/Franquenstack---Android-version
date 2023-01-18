package com.example.franquenstack.llamadasApi;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.franquenstack.AppListActivity;
import com.example.franquenstack.Controladores.ApplicationController;
import com.example.franquenstack.ElementListActivity;
import com.example.franquenstack.LoginActivity;
import com.example.franquenstack.R;
import com.example.franquenstack.modelos.Elemento;
import com.example.franquenstack.modelos.ElementoDetalles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlamadaListaElementos extends LlamarApi {
    Context context;

    public LlamadaListaElementos(Context context) {

        this.context = context;
    }

    public void llamandoListaElementos(ElementListActivity activity) {

        StringRequest request = new StringRequest(Request.Method.GET,
                context.getString(R.string.listaElementos) + "?api=" + LoginActivity.sharedPreferences.getInt("appId", 1), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Elemento> elementoList = new ArrayList<>();
                try {
                    JSONArray arrayElementos = new JSONArray(response);
                    for (int i = 0; i < arrayElementos.length(); i++) {
                        JSONObject objecto = arrayElementos.getJSONObject(i);
                        Elemento elemento = new Elemento(objecto.getInt("id"), objecto.getString("name"), objecto.getString("imagen"));
                        elementoList.add(elemento);
                    }
                    activity.enseñarListado(elementoList);
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
