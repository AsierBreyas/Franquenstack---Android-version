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
import com.example.franquenstack.llamadasApi.LlamadaComentarios;
import com.example.franquenstack.llamadasApi.LlamadaListaApps;
import com.example.franquenstack.modelos.App;
import com.example.franquenstack.modelos.Comentario;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class AppDetailsActivity extends Activity {
    TextView nombreApp, descripcion, nota;
    ImageView logoApp, estrellita;
    RecyclerView containerComentarios;
    ImageButton enviar;
    TextInputLayout inputComentario;
    App app;
    ComentariosAdapter comentariosAdapter;
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.app_detail_layout);
        App app = (App) getIntent().getSerializableExtra("App");
        nombreApp = findViewById(R.id.textViewDetailNombreApp);
        descripcion = findViewById(R.id.textViewAppDescripcion);
        nota = findViewById(R.id.textViewNotaDetalles);
        logoApp = findViewById(R.id.imageViewDetailLogoApp);
        estrellita = findViewById(R.id.imageViewMediaDetalles);
        containerComentarios = findViewById(R.id.containerComentariosDetallesApp);
        enviar = findViewById(R.id.buttonElementGenericEnviarComentario);
        enviar.setImageResource(R.drawable.enviar);
        inputComentario = findViewById(R.id.textInputLayoutElementGenericComentario);

        LlamadaListaApps lla = new LlamadaListaApps(getApplicationContext(), this);
        lla.obtenerApp(app.getId());
    }
    public void ponerApps(App aplicacion){
        app = aplicacion;
        nombreApp.setText(app.getNombre());
        logoApp.setImageResource(app.getImageId());
        descripcion.setText(app.getDescripcion());
        nota.setText(app.getMediaPuntos().toString());
        estrellita.setImageResource(R.drawable.icons8_estrella_relleno_48);
        comentariosAdapter = new ComentariosAdapter(app.getComentarios());
        containerComentarios.setHasFixedSize(true);
        containerComentarios.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        containerComentarios.setAdapter(comentariosAdapter);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputComentario.getEditText().getText().toString() != ""){
                    Comentario comentario = new Comentario(inputComentario.getEditText().getText().toString());
                    LlamadaComentarios lc = new LlamadaComentarios(getApplicationContext());
                    lc.publicarComentarioApp(comentario, app.getId());
                    app.addComentario(comentario);
                    comentariosAdapter.notifyDataSetChanged();
                    inputComentario.getEditText().setText("");
                }else
                    Toast.makeText(getApplicationContext(), "You can't publish a comment with no text", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
