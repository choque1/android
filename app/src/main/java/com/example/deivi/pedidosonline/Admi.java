package com.example.deivi.pedidosonline;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admi extends AppCompatActivity {
    Button crearres,veres,editcuenta,elimcuenta,pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admi);
        crearres =  findViewById(R.id.crestaurant);
        veres =  findViewById(R.id.verestaurant);
        editcuenta = findViewById(R.id.edicuenta1);
        elimcuenta = findViewById(R.id.elicuenta1);
        pedido = findViewById(R.id.irpedidos);
        editcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admi.this,EditarAdmi.class));
            }
        });
        elimcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Admi.this);

                builder.setTitle ("Eliminar Cuenta")
                        .setMessage("Esta Seguro de eliminar su cuenta")

                        .setPositiveButton ("Aceptar", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Admi.this,Login.class));
                                Toast.makeText(Admi.this,"Se ha eliminado su cuenta", Toast.LENGTH_SHORT).show();
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

        crearres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admi.this,RegistrarRestaurant.class));
                finish();
            }
        });
        veres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admi.this,InfoRestaurant.class));
                finish();
            }
        });
    }
}
