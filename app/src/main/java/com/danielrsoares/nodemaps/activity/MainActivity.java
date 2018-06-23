package com.danielrsoares.nodemaps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danielrsoares.nodemaps.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void botaoInventario(View view){
        startActivity(new Intent(MainActivity.this, InventarioActivity.class));
    }
}
