package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Admi extends AppCompatActivity {
    Button crearres,veres;
    Button edicuenta1;
    static String em="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admi);
        crearres =  findViewById(R.id.crestaurant);
        veres =  findViewById(R.id.verestaurant);
        edicuenta1 = findViewById(R.id.edicuenta1);
        final TextView nombre=(TextView) findViewById(R.id.nombre2);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView ci=(TextView) findViewById(R.id.ci2);
        ci.setText( getIntent().getExtras().getString("ci"));
        final TextView telefono=(TextView) findViewById(R.id.telefono2);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView email=(TextView) findViewById(R.id.correo2);
        em=getIntent().getExtras().getString("email");
        email.setText( getIntent().getExtras().getString("email"));
        final TextView tipo=(TextView) findViewById(R.id.tipo);
        tipo.setText( getIntent().getExtras().getString("tipo"));
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
        edicuenta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(Admi.this,EditarAdmi.class);
                t.putExtra("email",(String)em);
                startActivity(t);
                /*Bundle b=getIntent().getExtras();
                Toast.makeText(getApplicationContext(),b.getString("nombreMod")+"",Toast.LENGTH_LONG).show();*/
                finish();
            }
        });
        Bundle b=getIntent().getExtras();
        Toast.makeText(getApplicationContext(),b.getString("nombreMod")+"",Toast.LENGTH_LONG).show();
    }
}
