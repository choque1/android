package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarAdmi extends AppCompatActivity {
    Button guardar1;
    String nom,c,tel,mail,tip;
    EditText nombre,ci,telefono,email,tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_admi);
        nombre=(EditText) findViewById(R.id.nombre4);
        ci=(EditText) findViewById(R.id.ci4);
        telefono=(EditText) findViewById(R.id.phone4);
        email=(EditText) findViewById(R.id.correo4);
        tipo=(EditText) findViewById(R.id.tipo4);
        guardar1 = findViewById(R.id.guardar1);


        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditarAdmi.this,Admi.class));
                Toast.makeText(EditarAdmi.this,"Se guardo correctamente los datos",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        informacion();
    }
    public void informacion (){
        Intent intent = getIntent();
        nom = intent.getStringExtra("nombre");
        c = intent.getStringExtra("ci");
        tel = intent.getStringExtra("telefono");
        mail = intent.getStringExtra("email");
        tip = intent.getStringExtra("tipo");
        nombre.setText(nom);
        ci.setText(c);
        telefono.setText(tel);
        email.setText(mail);
        tipo.setText(tip);
    }
}
