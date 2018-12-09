package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;



public class EliminarMenu extends AppCompatActivity {
    ListView list3;
    ImageButton atras2;

    //ArrayList<item> list_data2 = new ArrayList<item> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_menu);
        atras2 = findViewById(R.id.imageatras);
        atras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EliminarMenu.this,InfoRestaurant.class));
                finish();
            }
        });

       /* for (int i = 0; i < 100; i++) {
            item p = new item();
            p.id = i;
            p.nombre = "Titulo" + i;
            p.description = "Descripcion" + i;
            p.precio = "precio" +i;
            p.url = "image" + i;
            list_data2.add (p);

        }
        final listAdapter adapter1 = new listAdapter(this, list_data2);
        list3 = this.findViewById (R.id.elist);
        list3.setAdapter (adapter1);
       /* list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list_data2.remove(position);
                adapter1.notifyDataSetChanged();
            }
        });*/
       /*list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               list_data2.remove(position);
               adapter1.notifyDataSetChanged();
           }
       });*/

    }
}
