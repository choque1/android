package com.example.deivi.pedidosonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import collections.item;
import collections.listAdapter;

public class Pedidos extends AppCompatActivity {


        ListView list;

        ArrayList<item> list_data1 = new ArrayList<item> ();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate (savedInstanceState);
            setContentView (R.layout.activity_pedidos);
            for (int i = 0; i < 100; i++) {
                item p1 = new item();
                p1.id = i;
                p1.title = "Titulo" + i;
                p1.description = "Descripcion" + i;
                p1.url = "image" + i;
                list_data1.add (p1);
            }
            listAdapter adapter = new listAdapter(this, list_data1);
            list = this.findViewById (R.id.pedidos);
            list.setAdapter (adapter);


            list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                @SuppressLint("ResourceType")
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    final item item2 = list_data1.get (position);


                    Intent i = new Intent(Pedidos.this, InfoPedidos.class);
                    i.putExtra ("Titulo", item2.getTitle());
                    i.putExtra ("Descripcion", item2.getDescription());
                    i.putExtra ("image",item2.getUrl());
                    startActivity(i);
                }
            });

    }
}