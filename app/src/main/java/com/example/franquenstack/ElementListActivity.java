package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.Adapters.ElementCardAdapter;
import com.example.franquenstack.llamadasApi.LlamadaListaElementos;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.Elemento;

import java.util.List;

public class ElementListActivity extends Activity {
    ImageView logoApp;
    ImageButton buscar;
    Spinner filtros;
    TextView nombre;
    RecyclerView elementosLista;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.element_list_activity);
        App app = (App) getIntent().getSerializableExtra("App");

        nombre.setText(app.getNombre());
        LoginActivity.editor.putInt("appId", app.getId());
        LoginActivity.editor.apply();

        //TODO filtros y boton buscar

        logoApp = findViewById(R.id.imageElementListAppLogo);
        logoApp.setImageResource(app.getImageId());

        LlamadaListaElementos lle = new LlamadaListaElementos(getApplicationContext());
        List<Elemento> elementos = lle.llamandoListaElementos();

        elementosLista = findViewById(R.id.elementosLista);
        ElementCardAdapter elementCardAdapter = new ElementCardAdapter(elementos);
        elementosLista.setHasFixedSize(true);
        elementosLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        elementosLista.setAdapter(elementCardAdapter);
    }
}
