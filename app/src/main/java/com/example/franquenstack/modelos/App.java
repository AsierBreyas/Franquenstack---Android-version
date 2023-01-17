package com.example.franquenstack.modelos;

import com.example.franquenstack.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class App implements Serializable {
    private int id;
    private String descripcion;
    private String nombre;
    private Double mediaPuntos;

    public App(int id, String nombre, Double mediaPuntos) {
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
    }
    public App(JSONObject app){
        try {
            id = app.getInt("app_id");
            nombre = app.getString("nombre");
            descripcion = app.getString("descripcion");
            mediaPuntos = app.getDouble("mediaPuntos");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
