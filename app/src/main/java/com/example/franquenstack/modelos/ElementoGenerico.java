package com.example.franquenstack.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ElementoGenerico {
    private int id;
    private Object tipo;
    private String name;
    private String image;
    private String description;
    private String version;
    private String publisher;
    private String genero;
    private Detalles detalles;
    private ArrayList<Comentario> comentarios;

    public ElementoGenerico(JSONObject object) throws JSONException {
        this.id = object.getInt("id");
        /*this.tipo = object.getJSONObject("tipo");*/
        this.name = object.getString("name");
        this.image = object.getString("image");
        this.description = object.getString("description").replaceAll("<[^>]*>", "");
        this.version = object.getString("version");
        this.publisher = object.getString("publisher");
        this.genero = object.getString("genero");
        //TODO detalles
        /*this.detalles = detalles;*/
        JSONArray jsonArray = object.getJSONArray("comentarios");
        comentarios = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            comentarios.add(new Comentario(jsonArray.getJSONObject(i)));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getTipo() {
        return tipo;
    }

    public void setTipo(Object tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenero() {
        String[] generos = genero.split(";");
        String generoSpliteado = "";
        for (int i = 0; i < generos.length; i++){
            if(i == 0)
                generoSpliteado = generoSpliteado + generos[i];
            else
                generoSpliteado = generoSpliteado + ", " + generos[i];
        }


        return generoSpliteado;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Detalles getDetalles() {
        return detalles;
    }

    public void setDetalles(Detalles detalles) {
        this.detalles = detalles;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
