package com.danielrsoares.nodemaps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.danielrsoares.nodemaps.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InventarioActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        toolbar = findViewById(R.id.toolbar_Inventario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("São Bernardo do Campo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    //======== Menu ToolBar ========================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_inventario, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_inventario_filtrarCidade: //Filtrar Cidades
                Toast.makeText( this, "Filtrar Cidades", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_inventario_criarNode: //Criar Novo Node
                startActivity(new Intent(InventarioActivity.this, InventarioCadastroActivity.class));
                //Toast.makeText(this, "Criar Node", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_inventario_criarCidade: //Criar Nova Cidade
                Toast.makeText(this, "Criar Nova Cidade", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish(); //Fecha a Tela
        }
        return super.onOptionsItemSelected(item);
    }

}
