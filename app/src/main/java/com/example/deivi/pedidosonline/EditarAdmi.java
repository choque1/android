package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditarAdmi extends AppCompatActivity {
    Button guardar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_admi);
        guardar1 = findViewById(R.id.guardar1);
        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditarAdmi.this,Admi.class));
                Toast.makeText(EditarAdmi.this,"Se guardo correctamente los datos",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
