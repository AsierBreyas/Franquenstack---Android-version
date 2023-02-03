package com.example.franquenstack.modelos;

import com.example.franquenstack.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Comentario {
    public String comment_text;
    public String hora;
    public String username;
    public Comentario(JSONObject object) throws JSONException {
        comment_text = object.getString("comment_text");
        hora = object.getString("hora");
        username = object.getString("username");
    }

    public Comentario(String comment_text) {
        this.comment_text = comment_text;
        hora = String.format("%02d",Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", Calendar.getInstance().get(Calendar.MINUTE));
        username = LoginActivity.sharedPreferences.getString("username", "Eustaquio");

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
