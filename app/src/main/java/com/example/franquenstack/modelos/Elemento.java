package com.example.franquenstack.modelos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Elemento {
    private int id;
    private String name;
    private String imagen;
    private ArrayList<String> generos;
    public Elemento(JSONObject object){
        try {
            id = object.getInt("id");
            name = object.getString("name");
            imagen = object.getString("imagen");
            this.generos = new ArrayList<>();
            String[] generos = object.getString("genres").split(";");
            for (int i = 0; i < generos.length; i++){
                this.generos.add(generos[i].substring(0,1).toUpperCase() + generos[i].substring(1));
            }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

}
