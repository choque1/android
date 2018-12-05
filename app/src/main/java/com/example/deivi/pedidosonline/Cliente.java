package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cliente extends AppCompatActivity {
    Button pedido,carrito,eliminar,editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        pedido =  findViewById(R.id.reapedido);
        carrito = findViewById(R.id.vercarrito);
        eliminar = findViewById(R.id.elicuenta);
        editar = findViewById(R.id.edicuenta);
        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cliente.this,Lugar.class));
                finish();
            }
        });
        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cliente.this,Carrito.class));
            }
        });
    }
}
