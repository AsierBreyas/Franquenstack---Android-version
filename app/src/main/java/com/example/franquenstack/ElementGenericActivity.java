package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.Adapters.ComentariosAdapter;
import com.example.franquenstack.Controladores.FavList;
import com.example.franquenstack.llamadasApi.LlamadaElementosGenericos;
import com.example.franquenstack.llamadasApi.LlamadaFavoritos;
import com.example.franquenstack.modelos.Elemento;
import com.example.franquenstack.modelos.ElementoGenerico;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ElementGenericActivity extends Activity {
    RecyclerView listaComentarios;
    TextView nombre, desc, genero, fecha, publisher;
    ImageView imagen, fav;
    ImageButton enviar;
    TextInputLayout comentario;
    Button seeMore;
    boolean estaFaveado;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.element_generic);
        int elementoId = (int) getIntent().getSerializableExtra("ElementoId");

        listaComentarios = findViewById(R.id.listaComentariosElementoGenereic);
        nombre = findViewById(R.id.textViewElementGenericName);
        desc = findViewById(R.id.textViewElementGenericDes);
        genero = findViewById(R.id.textViewElemetGenericGenre);
        fecha = findViewById(R.id.textViewElemetGenericDate);
        publisher = findViewById(R.id.textViewElemetGenericPublisher);
        imagen = findViewById(R.id.imageViewElementGenereic);
        comentario = findViewById(R.id.textInputLayoutElementGenericComentario);
        enviar = findViewById(R.id.buttonElementGenericEnviarComentario);
        enviar.setImageResource(R.drawable.envia2);
        //TODO si es fav cambiar el icono al corazon rojo
        fav = findViewById(R.id.imageViewElementGenericFav);
        estaFaveado = false;
        fav.setImageResource(R.drawable.fav);
        for (int i = 0; i < FavList.favList.size(); i++){
            if (elementoId == FavList.favList.get(i)) {
                estaFaveado = true;
                fav.setImageResource(R.drawable.fav_relleno);
            }
        }
        seeMore = findViewById(R.id.buttonSeeMore);
        LlamadaElementosGenericos lle = new LlamadaElementosGenericos(getApplicationContext(), this);
        lle.obtenerElemento(elementoId);
    }
    public void ponerElementos(ElementoGenerico elemento){
        nombre.setText(elemento.getName());
        desc.setText(elemento.getDescription());
        genero.setText(genero.getText() + " " + elemento.getGenero());
        fecha.setText(fecha.getText() + " " + elemento.getVersion());
        publisher.setText(publisher.getText() + " " + elemento.getPublisher());
        Picasso.get().load(elemento.getImage()).into(imagen);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estaFaveado = ! estaFaveado;
                if(estaFaveado){
                    fav.setImageResource(R.drawable.fav_relleno);
                    FavList.favList.add(elemento.getId());
                }else{
                    fav.setImageResource(R.drawable.fav);
                    for (int i = 0; i < FavList.favList.size(); i++){
                        if (FavList.favList.get(i) == elemento.getId())
                            FavList.favList.remove(i);
                    }
                }
                LlamadaFavoritos lf = new LlamadaFavoritos(getApplicationContext());
                lf.cambiarFavorito(elemento.getId());
            }
        });
        //TODO el boton see more
        ComentariosAdapter comentariosAdapter = new ComentariosAdapter(elemento.getComentarios());
        listaComentarios.setHasFixedSize(true);
        listaComentarios.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listaComentarios.setAdapter(comentariosAdapter);
    }
}
