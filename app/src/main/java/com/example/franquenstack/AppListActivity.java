package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.Adapters.AppCardAdapter;
import com.example.franquenstack.llamadasApi.LlamadaListaApps;
import com.example.franquenstack.modelos.App;

import java.util.List;

public class AppListActivity extends Activity {
    RecyclerView appLista;
    ImageButton logout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list_activity);

        TextView welcome = findViewById(R.id.textViewWelcome);
        welcome.setText("WELCOME\n"+ LoginActivity.sharedPreferences.getString("username", "Eustaquio"));
        ImageView logoFran = findViewById(R.id.franquenstackLogo);
        logout = findViewById(R.id.imageButtonLogout);
        logout.setImageResource(R.drawable.logout);
        logoFran.setImageResource(R.drawable.franquenstack);

        appLista = findViewById(R.id.appList);
        LlamadaListaApps lla = new LlamadaListaApps(getApplicationContext(),this, LoginActivity.sharedPreferences.getString("token", null));
        lla.obtenerListado();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.editor.clear();
                LoginActivity.editor.apply();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        LoginActivity.editor.clear();
        LoginActivity.editor.apply();
        finish();
    }
    public void enseñarListado(List<App> apps){
        AppCardAdapter appCardAdapter = new AppCardAdapter(apps);
        appLista.setHasFixedSize(true);
        appLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appLista.setAdapter(appCardAdapter);
    }
}
