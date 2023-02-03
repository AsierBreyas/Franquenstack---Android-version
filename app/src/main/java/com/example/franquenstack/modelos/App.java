package com.example.franquenstack.modelos;

import com.example.franquenstack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class App implements Serializable {
    private int id;
    private String descripcion;
    private String nombre;
    private Double mediaPuntos;
    private List<Comentario> comentarios;

    public App(JSONObject app){
        try {
            id = app.getInt("app_id");
            nombre = app.getString("nombre");
            descripcion = app.getString("descripcion");
            mediaPuntos = app.getDouble("mediaPuntos");
            comentarios = new ArrayList<>();
            JSONArray arrayComentarios = app.getJSONArray("listaComentarios");
            if (arrayComentarios.length() > 0){
                for (int i = 0; i < arrayComentarios.length(); i++){
                    Comentario comentario = new Comentario(arrayComentarios.getJSONObject(i));
                    comentarios.add(comentario);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public App(int id, String descripcion, String nombre, Double mediaPuntos) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
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

    public Double getMediaPuntos() {
        return mediaPuntos;
    }

    public int getImageId(){
        int id = R.drawable.franquenstack;
        switch (nombre){
            case "POKEDEX":
                id = R.drawable.pokeapi_logo;
                break;
            case "NETFLIX":
                id = R.drawable.netflix_logo;
                break;
            case "FREE TO GAME":
                id = R.drawable.freetoplay_logo ;
                break;
        }
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }
    public void addComentario(Comentario comentario){
        comentarios.add(comentario);
    }
}
