package com.danielrsoares.nodemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginUsuarioActivity extends AppCompatActivity {

    private Button buttonIrCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        buttonIrCadastro = findViewById(R.id.buttonIrCadastro);
        buttonIrCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class));
            }
        });
    }
}
