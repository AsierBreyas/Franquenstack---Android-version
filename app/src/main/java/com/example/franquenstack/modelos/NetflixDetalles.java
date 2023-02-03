package com.example.franquenstack.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetflixDetalles extends Detalles{
    String web;
    String hora;
    List<String> dias;
    String duracion;
    String inicio;
    String fin;

    public NetflixDetalles(JSONObject detalles) throws JSONException {
        web = detalles.getString("web");
        hora = detalles.getString("hora");
        JSONArray diasArray = detalles.getJSONArray("dias");
        dias = new ArrayList<>();
        for (int i = 0; i < diasArray.length(); i++){
            dias.add(diasArray.getString(i));
        }
        duracion = detalles.getString("duracion");
        inicio = detalles.getString("inicio");
        fin = detalles.getString("fin");
    }

    public String getWeb() {
        return web;
    }

    public String getHora() {
        return hora;
    }

    public List<String> getDias() {
        return dias;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }
}
