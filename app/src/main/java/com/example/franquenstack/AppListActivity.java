package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.franquenstack.Adapters.AppCardAdapter;
import com.example.franquenstack.llamadasApi.LlamadaListaApps;
import com.example.franquenstack.modelos.App;

import java.util.List;

public class AppListActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list_activity);

        TextView welcome = findViewById(R.id.textViewWelcome);
        welcome.setText("WELCOME "+ LoginActivity.sharedPreferences.getString("username", "Eustaquio"));
        ImageView logoFran = findViewById(R.id.franquenstackLogo);
        logoFran.setImageResource(R.drawable.franquenstack);


        LlamadaListaApps lla = new LlamadaListaApps(getApplicationContext(),this, LoginActivity.sharedPreferences.getString("token", null));
        List<App> apps = lla.obtenerListado();

        RecyclerView appLista = findViewById(R.id.appList);
        AppCardAdapter appCardAdapter = new AppCardAdapter(apps);
        appLista.setHasFixedSize(true);
        appLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appLista.setAdapter(appCardAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_list_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logout:
                LoginActivity.editor.clear();
                LoginActivity.editor.apply();
                finish();
        }
        return(super.onOptionsItemSelected(item));
    }
}
