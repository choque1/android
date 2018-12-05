package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoRestaurant extends AppCompatActivity {
    Button crear,ver,editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_restaurant);
        crear = findViewById(R.id.crearmenu);
        ver = findViewById(R.id.vermenu);
        editar = findViewById(R.id.editarmenu);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,CrearMenu.class));
                finish();
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,VerMenu.class));
                finish();
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,EditarMenu.class));
                finish();
            }
        });
    }
}
