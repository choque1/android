package com.example.deivi.pedidosonline;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.strictmode.CleartextNetworkViolation;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cliente.this,EditarCliente.class));
                finish();
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cliente.this);

                builder.setTitle ("Eliminar Cuenta")
                        .setMessage("Esta Seguro de eliminar su cuenta")

                        .setPositiveButton ("Aceptar", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Cliente.this,Login.class));
                                Toast.makeText(Cliente.this,"Se ha eliminado su cuenta", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }).setNegativeButton ("Cancelar", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                Dialog dialog = builder.create ();
                dialog.show ();
            }
        });
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
