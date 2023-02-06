package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.Adapters.ComentariosAdapter;
import com.example.franquenstack.Adapters.JuegoAdapter;
import com.example.franquenstack.Adapters.NetflixAdapter;
import com.example.franquenstack.Adapters.PokemonAdapter;
import com.example.franquenstack.llamadasApi.LlamadaComentarios;
import com.example.franquenstack.llamadasApi.LlamadaElementosGenericos;
import com.example.franquenstack.llamadasApi.LlamadaFavoritos;
import com.example.franquenstack.modelos.Comentario;
import com.example.franquenstack.modelos.ElementoGenerico;
import com.example.franquenstack.modelos.JuegoDetalles;
import com.example.franquenstack.modelos.NetflixDetalles;
import com.example.franquenstack.modelos.PokemonDetalles;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class ElementGenericActivity extends Activity {
    RecyclerView listaComentarios, detalles;
    TextView nombre, desc, genero, fecha, publisher;
    ImageView imagen, fav;
    ImageButton enviar;
    TextInputLayout comentario;
    boolean estaFaveado;
    int elementoId;
    String elementoNombre;
    ComentariosAdapter comentariosAdapter;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.element_generic);
        if (LoginActivity.sharedPreferences.getInt("appId", 1) == 2)
            elementoNombre = (String) getIntent().getSerializableExtra("ElementoNombre");
        else
            elementoId = (int) getIntent().getSerializableExtra("ElementoId");

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
        fav = findViewById(R.id.imageViewElementGenericFav);
        detalles = findViewById(R.id.recyclerViewDetallesElemento);

        LlamadaElementosGenericos lle = new LlamadaElementosGenericos(getApplicationContext(), this);
        if (LoginActivity.sharedPreferences.getInt("appId", 1) == 2)
            lle.obtenerElemento(elementoNombre);
        else
            lle.obtenerElemento(elementoId);
    }
    public void ponerElementos(ElementoGenerico elemento){
        nombre.setText(elemento.getName().substring(0,1).toUpperCase() + elemento.getName().substring(1));
        desc.setText(elemento.getDescription());
        genero.setText(genero.getText() + " " + elemento.getGenero());
        fecha.setText(fecha.getText() + " " + elemento.getVersion());
        publisher.setText(publisher.getText() + " " + elemento.getPublisher());
        Picasso.get().load(elemento.getImage()).into(imagen);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estaFaveado = ! estaFaveado;
                if(estaFaveado)
                    fav.setImageResource(R.drawable.fav_relleno);
                else
                    fav.setImageResource(R.drawable.fav);
                LlamadaFavoritos lf = new LlamadaFavoritos(getApplicationContext());
                if (LoginActivity.sharedPreferences.getInt("appId", 0) == 2)
                    lf.cambiarFavorito(elemento.getName());
                else
                    lf.cambiarFavorito(elemento.getId() + "");

            }
        });
        detalles.setHasFixedSize(true);
        detalles.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        switch (LoginActivity.sharedPreferences.getInt("appId", 0)){
            case 1:
                JuegoAdapter juegoAdapter = new JuegoAdapter((JuegoDetalles) elemento.getDetalles());
                detalles.setAdapter(juegoAdapter);
                break;
            case 2:
                PokemonAdapter pokemonAdapter = new PokemonAdapter((PokemonDetalles) elemento.getDetalles());
                detalles.setAdapter(pokemonAdapter);
                genero.setText("Type: " + elemento.getGenero());
                fecha.setText("Realese game: " + elemento.getVersion());
                break;
            case 3:
                NetflixAdapter netflixAdapter = new NetflixAdapter((NetflixDetalles) elemento.getDetalles());
                detalles.setAdapter(netflixAdapter);
                break;
        }
        if (elemento.getComentarios().size() != 0)
            hacerAdaptador(elemento);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comentario.getEditText().getText().toString() != ""){
                    Comentario comentarioE = new Comentario(comentario.getEditText().getText().toString());
                    LlamadaComentarios lc = new LlamadaComentarios(getApplicationContext());
                    lc.publicarComentario(comentarioE, elemento);
                    elemento.addComentario(comentarioE);
                    if (elemento.getComentarios().size() == 1)
                        hacerAdaptador(elemento);
                    else
                        comentariosAdapter.notifyDataSetChanged();
                    comentario.getEditText().setText("");
                }else
                    Toast.makeText(getApplicationContext(), "You can't publish a comment with no text", Toast.LENGTH_SHORT).show();
            }
        });
        LlamadaFavoritos lf = new LlamadaFavoritos(getApplicationContext());
        if (LoginActivity.sharedPreferences.getInt("appId", 0) == 2)
            lf.estaElementoFaveado(this, elemento.getName());
        else
            lf.estaElementoFaveado(this, elemento.getId() + "");
    }
    public void hacerAdaptador(ElementoGenerico elemento){
        comentariosAdapter = new ComentariosAdapter(elemento.getComentarios());
        listaComentarios.setHasFixedSize(true);
        listaComentarios.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listaComentarios.setAdapter(comentariosAdapter);
    }
    public void estaElementofaveado(boolean faveado){
        estaFaveado = faveado;
        if (faveado)
            fav.setImageResource(R.drawable.fav_relleno);
        else
            fav.setImageResource(R.drawable.fav);
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
