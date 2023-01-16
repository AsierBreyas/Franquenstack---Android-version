package com.example.franquenstack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.franquenstack.llamadasApi.LlamadaLogin;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends Activity {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    Button logearse;
    Button registrarse;
    TextInputLayout username;
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        sharedPreferences = this.getSharedPreferences("application", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        logearse = (Button) findViewById(R.id.buttonLogearse);
        registrarse = (Button) findViewById(R.id.buttonRegistrarse);
        username = (TextInputLayout) findViewById(R.id.textInputUsername);
        password = (TextInputLayout) findViewById(R.id.textInputContraseña);
        Activity activity = this;
        logearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringUsername = username.getEditText().getText().toString();
                String stringPassword = password.getEditText().getText().toString();
                if( stringUsername.equals("") && stringPassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Rellena los campos", Toast.LENGTH_SHORT).show();
                }else{
                    LlamadaLogin ll = new LlamadaLogin(LoginActivity.this, activity);
                    ll.logearse(stringUsername, stringPassword);
                }
            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrerActivity.class));
            }
        });
    }
}