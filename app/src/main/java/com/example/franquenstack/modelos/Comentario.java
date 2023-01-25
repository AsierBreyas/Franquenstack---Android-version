package com.example.franquenstack.modelos;

import org.json.JSONException;
import org.json.JSONObject;

public class Comentario {
    public String comment_text;
    public String hora;
    public String username;
    public Comentario(JSONObject object) throws JSONException {
        comment_text = object.getString("comment_text");
        hora = object.getString("hora");
        username = object.getString("username");
    }

    public Comentario(String comment_text, String hora, String username) {
        this.comment_text = comment_text;
        this.hora = hora;
        this.username = username;
    }

    public String getComment_text() {
        return comment_text;
    }

    public String getHora() {
        return hora;
    }

    public String getUsername() {
        return username;
    }
}
