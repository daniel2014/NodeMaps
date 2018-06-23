package com.danielrsoares.nodemaps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danielrsoares.nodemaps.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InventarioActivity extends AppCompatActivity {


    private Toolbar toolbarInventario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        toolbarInventario = findViewById(R.id.toolbarInventario);
        setSupportActionBar(toolbarInventario); //Definindo ToolBar Padrão

    }

    public void fbt_TelaCadastroNode(View view){
        startActivity(new Intent(InventarioActivity.this, InventarioCadastroActivity.class));
    }
}
