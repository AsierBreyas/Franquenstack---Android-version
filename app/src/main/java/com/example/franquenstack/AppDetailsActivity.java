package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.modelos.App;

public class AppDetailsActivity extends Activity {
    TextView nombreApp, descripcion;
    ImageView logoApp;
    RecyclerView containerComentarios;
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.app_detail_layout);
        //TODO nombrar textinput layout en app_detail_layout
        App app = (App) getIntent().getSerializableExtra("App");
        nombreApp = findViewById(R.id.textViewDetailNombreApp);
        descripcion = findViewById(R.id.textViewAppDescripcion);
        logoApp = findViewById(R.id.imageViewDetailLogoApp);
        containerComentarios = findViewById(R.id.containerComentarios);

        nombreApp.setText(app.getNombre());
        logoApp.setImageResource(app.getImageId());
        //TODO poner descripcion (esperando a la API) y recyclerview de los comentarios de la app
    }
}
