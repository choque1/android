package com.example.deivi.pedidosonline;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import collections.item;
import collections.listAdapter;

public class Menu extends AppCompatActivity {
    ListView list;

    ArrayList<item> list_data = new ArrayList<item> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        for (int i = 0; i < 100; i++) {
            item p = new item();
            p.id = i;
            p.nombrepro= "papa" + i;
            p.description = "Descripcion" + i;
            p.precio = "precio"+i;
            p.url = "image" + i;
            list_data.add (p);

        }
        listAdapter adapter = new listAdapter(this, list_data);
        list = this.findViewById (R.id.listamenu2);
        list.setAdapter (adapter);
        list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final item item = list_data.get (position);
                AlertDialog.Builder builder = new AlertDialog.Builder (Menu.this);
                LayoutInflater inflater = (Menu.this).getLayoutInflater();
                builder.setTitle ("Cantidad")
                        .setView (inflater.inflate(R.layout.dialog,null))

                        .setPositiveButton ("Aceptar", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                Intent i = new Intent(Menu.this, Carrito.class);
                i.putExtra ("papa", item.nombrepro);
                i.putExtra ("Descripcion", item.description );
                i.putExtra ("image",item.url);
                finish();
                startActivity(i);

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
    }
}
