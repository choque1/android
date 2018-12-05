package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import collections.StaticData;
import collections.item;
import collections.listAdapter;

public class Carrito extends AppCompatActivity {
    Button pedir;
    ListView list1;
    Bundle bundle ;
    listAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        pedir= findViewById (R.id.pedir);
        pedir.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(Carrito.this,Pedidos.class));
                finish ();

            }
        });

    }
    @Override
    protected void onResume() {
        if (StaticData.LISTAPARCIAL == null){
            StaticData.LISTAPARCIAL = new ArrayList<>();
        }
        bundle = getIntent().getExtras();
        item item2 = new item();
        item2.setTitle ("Titulo");
        item2.setDescription ("Descripcion");
        StaticData.LISTAPARCIAL.add (item2);
        list1 = findViewById (R.id.pedidos);
        adapter = new listAdapter(this, StaticData.LISTAPARCIAL);
        list1.setAdapter (adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StaticData.LISTAPARCIAL.remove(position);
                adapter.notifyDataSetChanged();


            }
        });



        super.onResume ();
    }
}
