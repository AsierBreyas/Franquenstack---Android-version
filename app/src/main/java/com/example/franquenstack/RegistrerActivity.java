package com.example.franquenstack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.franquenstack.llamadasApi.LlamadaRegistro;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrerActivity extends Activity {
    Button registrarse, goBack;
    TextInputLayout username, password, repeatPassword, email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrer_actvity);

        registrarse = (Button) findViewById(R.id.buttonRegistrer);
        goBack = (Button) findViewById(R.id.buttonRegistrerGoBack);
        username = (TextInputLayout) findViewById(R.id.textInputLayoutRegistrerUsername);
        password = (TextInputLayout) findViewById(R.id.textInputLayoutRegistrerPassword);
        repeatPassword = (TextInputLayout) findViewById(R.id.textInputLayoutRepearPassword);
        email = (TextInputLayout) findViewById(R.id.textInputLayoutRegistrerEmail);
        Activity activity = this;

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringUsername = username.getEditText().getText().toString();
                String stringPassword = password.getEditText().getText().toString();
                String stringRepeat = repeatPassword.getEditText().getText().toString();
                String stringEmail = email.getEditText().getText().toString();
                if(stringPassword.equals(stringRepeat)){
                    LlamadaRegistro lr = new LlamadaRegistro(getApplicationContext(), activity);
                    lr.registrarse(stringUsername, stringPassword, stringEmail);
                }
            }
        });
    }
}
