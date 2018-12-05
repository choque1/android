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

public class EditarMenu extends AppCompatActivity {
    ListView list;

    ArrayList<item> list_data = new ArrayList<item> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_menu);
        for (int i = 0; i < 100; i++) {
            item p = new item();
            p.id = i;
            p.title = "Titulo" + i;
            p.description = "Descripcion" + i;
            p.url = "image" + i;
            list_data.add (p);

        }
        listAdapter adapter = new listAdapter(this, list_data);
        list = this.findViewById (R.id.listamenu1);
        list.setAdapter (adapter);

        list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final item item = list_data.get (position);


                                Intent i = new Intent(EditarMenu.this, Editar1.class);
                                i.putExtra ("Titulo", item.title);
                                i.putExtra ("Descripcion", item.description );
                                i.putExtra ("image",item.url);
                                finish();
                                startActivity(i);


            }
        });

    }
}
