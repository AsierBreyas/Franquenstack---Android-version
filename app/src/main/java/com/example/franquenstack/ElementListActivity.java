package com.example.franquenstack;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.Adapters.ElementCardAdapter;
import com.example.franquenstack.Controladores.FavList;
import com.example.franquenstack.llamadasApi.LlamadaFavoritos;
import com.example.franquenstack.llamadasApi.LlamadaListaElementos;
import com.example.franquenstack.llamadasApi.LlamadaListaGeneros;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.Elemento;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElementListActivity extends Activity {
    private ImageView logoApp;
    private ImageButton buscar, goFav;
    private Spinner filtros;
    private TextView nombre;
    private RecyclerView elementosLista;
    private List<Elemento> listaDeElementos;
    private List<Elemento> listaActual;
    private List<Elemento> listaFiltrada;
    private ElementCardAdapter elementCardAdapter;
    private TextInputLayout filtroNombre;
    private boolean filtroFavActivado;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.element_list_activity);
        App app = (App) getIntent().getSerializableExtra("App");

        nombre = findViewById(R.id.textViewElementListNombreApp);
        nombre.setText(app.getNombre());
        LoginActivity.editor.putInt("appId", app.getId());
        LoginActivity.editor.apply();

        goFav = findViewById(R.id.imageButtonGoFavList);
        goFav.setImageResource(R.drawable.fav);
        filtroFavActivado = false;
        ElementListActivity activity = this;
        goFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtroFavActivado = ! filtroFavActivado;
                if (filtroFavActivado){
                    LlamadaFavoritos lf = new LlamadaFavoritos(getApplicationContext());
                    lf.llamarListaFavoritos(activity);
                    goFav.setImageResource(R.drawable.fav_relleno);
                }else{
                    listaActual.clear();
                    for (Elemento elemento: listaDeElementos){
                        listaActual.add(elemento);
                    }
                    elementCardAdapter.notifyDataSetChanged();
                    goFav.setImageResource(R.drawable.fav);
                }
            }
        });

        buscar = findViewById(R.id.imageButtonElementListSearchButton);
        buscar.setImageResource(R.drawable.search);
        filtroNombre = findViewById(R.id.textInputLayoutFilterName);
        buscar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String filtro = filtroNombre.getEditText().getText().toString();
                List<Elemento> listaProvisional = new ArrayList<>();
                listaProvisional = (ArrayList<Elemento>) listaFiltrada.stream().filter(x -> x.getName().toLowerCase().contains(filtro.toLowerCase())).collect(Collectors.toList());
                if (listaProvisional.size() > 0){
                    listaActual.clear();
                    for (Elemento elemento: listaProvisional){
                        listaActual.add(elemento);
                    }
                    elementCardAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Results no found", Toast.LENGTH_SHORT);
                }
            }
        });

        logoApp = findViewById(R.id.imageElementListAppLogo);
        logoApp.setImageResource(app.getImageId());

        filtros = findViewById(R.id.spinnerFiltros);

        elementosLista = findViewById(R.id.elementosLista);
        LlamadaListaElementos lle = new LlamadaListaElementos(getApplicationContext());
        lle.llamandoListaElementos(this);

    }
    public void enseñarListado(List<Elemento> elementos){
        listaDeElementos = elementos;
        listaActual = new ArrayList<>();
        listaFiltrada = new ArrayList<>();
        for (Elemento elemento: elementos)
            listaActual.add(elemento);
        elementCardAdapter = new ElementCardAdapter(listaActual, this);
        elementosLista.setHasFixedSize(true);
        elementosLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        elementosLista.setAdapter(elementCardAdapter);
        LlamadaListaGeneros llg = new LlamadaListaGeneros(getApplicationContext());
        llg.llamarGeneros(this);
    }
    public void ponerSpiner(ArrayList<String> generos){
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, generos);
        filtros.setAdapter(spinnerAdapter);
        filtros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listaActual.clear();
                listaFiltrada.clear();
                if (adapterView.getItemAtPosition(i).equals("No filter")){
                    for (Elemento elemento: listaDeElementos){
                        listaActual.add(elemento);
                        listaFiltrada.add(elemento);
                    }
                }else{
                    for (Elemento elemento : listaDeElementos) {
                        for (String genero : elemento.getGeneros()) {
                            if (genero.equals(adapterView.getItemAtPosition(i))) {
                                listaActual.add(elemento);
                                listaFiltrada.add(elemento);
                            }
                        }
                    }
                }
                goFav.setImageResource(R.drawable.fav);
                elementCardAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                for (Elemento elemento: listaDeElementos){
                    listaActual.add(elemento);
                    listaFiltrada.add(elemento);
                }
                elementCardAdapter.notifyDataSetChanged();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ponerFavoritos(ArrayList<Integer> favoritos){
        listaActual.clear();
        for (int favId: favoritos){
            for (Elemento elemento: (ArrayList<Elemento>) listaDeElementos.stream().filter(x -> x.getId() == favId).collect(Collectors.toList())){
                listaActual.add(elemento);
            }
        }
        if (listaActual.size() == 0){
            Toast.makeText(getApplicationContext(), "You have no favorites in this app", Toast.LENGTH_SHORT);
            for (Elemento elemento: listaDeElementos){
                listaActual.add(elemento);
            }
            filtroFavActivado = false;
            goFav.setImageResource(R.drawable.fav);
        }
        elementCardAdapter.notifyDataSetChanged();
    }
}
