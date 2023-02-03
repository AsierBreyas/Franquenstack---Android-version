package com.example.franquenstack.modelos;

import org.json.JSONException;
import org.json.JSONObject;

public class JuegoDetalles extends Detalles{
    String memory;
    String os;
    String graphics;
    String processor;
    String storage;
    public JuegoDetalles(JSONObject detalles) throws JSONException {
        memory = detalles.getString("memory");
        os = detalles.getString("os");
        graphics = detalles.getString("graphics");
        processor = detalles.getString("processor");
        storage = detalles.getString("storage");
    }

    public String getMemory() {
        return memory;
    }

    public String getOs() {
        return os;
    }

    public String getGraphics() {
        return graphics;
    }

    public String getStorage() {
        return storage;
    }

    public String getProcessor() {
        return processor;
    }
}
