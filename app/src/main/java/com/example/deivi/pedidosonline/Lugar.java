package com.example.deivi.pedidosonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


import collections.item2;
import collections.restaurantAdapter;

public class Lugar extends AppCompatActivity {
    ListView list;

    ArrayList<item2> list_data1 = new ArrayList<item2> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);
        for (int i = 0; i < 100; i++) {
            item2 p1 = new item2();
            p1.id = i;
            p1.nombre = "restaurant" + i;
            p1.url2 = "image" + i;
            list_data1.add (p1);

        }
        restaurantAdapter adapter = new restaurantAdapter(this, list_data1);
        list = this.findViewById (R.id.restaurants);
        list.setAdapter (adapter);
        list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final item2 item = list_data1.get (position);


                Intent i = new Intent(Lugar.this, Menu.class);

                finish();
                startActivity(i);


            }
        });

    }
}
