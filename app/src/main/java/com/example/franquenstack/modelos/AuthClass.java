package com.example.franquenstack.modelos;

import java.io.Serializable;

public class AuthClass implements Serializable {
    String authToken;
    String nombre;

    public AuthClass(String authToken, String nombre) {
        this.authToken = authToken;
        this.nombre = nombre;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
