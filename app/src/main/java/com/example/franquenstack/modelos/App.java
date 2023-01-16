package com.example.franquenstack.modelos;

import com.example.franquenstack.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class App implements Serializable {
    private String nombre;
    private Double mediaPuntos;

    public App(String nombre, Double mediaPuntos) {
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
    }
    public App(JSONObject app){
        try {
            nombre = app.getString("nombre");
            mediaPuntos = app.getDouble("mediaPuntos");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMediaPuntos() {
        return mediaPuntos;
    }

    public void setMediaPuntos(Double mediaPuntos) {
        this.mediaPuntos = mediaPuntos;
    }
    public int getImageId(){
        int id = R.drawable.franquenstack;
        switch (nombre){
            case "POKEDEX":
                id = R.drawable.pokeapi_logo;
                break;
            case "NETFLIZ":
                id = R.drawable.netflix_logo;
                break;
            case "FREE TO GAME":
                id = R.drawable.freetoplay_logo ;
                break;
        }
        return id;
    }
}
