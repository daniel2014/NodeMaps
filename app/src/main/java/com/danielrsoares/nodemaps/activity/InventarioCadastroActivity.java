package com.danielrsoares.nodemaps.activity;

import android.os.Bundle;
import android.view.View;

import com.danielrsoares.nodemaps.R;
import androidx.appcompat.app.AppCompatActivity;

public class InventarioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_cadastro);

    }

    public void btFecharTela(View view){
        finish();
    }

}
