package com.example.franquenstack.modelos;

public class Elemento {
    private int id;
    private String name;
    private String imagen;

    public Elemento(int id, String name, String imagen) {
        this.id = id;
        this.name = name;
        this.imagen = imagen;
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

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
