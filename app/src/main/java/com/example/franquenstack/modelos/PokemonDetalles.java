package com.example.franquenstack.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetalles extends Detalles{
    int weigth;
    int height;
    int hp;
    int attack;
    int defense;
    int sp_attack;
    int sp_defense;
    int speed;
    List<String> habilidades;

    public PokemonDetalles(JSONObject detalles) throws JSONException {
        weigth = detalles.getJSONObject("dimensiones").getInt("weight");
        height = detalles.getJSONObject("dimensiones").getInt("height");
        hp = detalles.getJSONObject("stats").getInt("hp");
        attack = detalles.getJSONObject("stats").getInt("attack");
        defense = detalles.getJSONObject("stats").getInt("defense");
        sp_attack = detalles.getJSONObject("stats").getInt("special_attack");
        sp_defense = detalles.getJSONObject("stats").getInt("special_defense");
        speed = detalles.getJSONObject("stats").getInt("speed");
        JSONArray habilidadesArray = detalles.getJSONArray("habilidades");
        habilidades = new ArrayList<>();
        for (int i = 0; i < habilidadesArray.length(); i++){
            habilidades.add(habilidadesArray.getString(i));
        }
    }

    public int getWeigth() {
        return weigth;
    }

    public int getHeight() {
        return height;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSp_attack() {
        return sp_attack;
    }

    public int getSp_defense() {
        return sp_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }
}
