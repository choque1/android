package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;



public class VerMenu extends AppCompatActivity {
    ListView listamenu;
    ImageButton atras;

//    ArrayList<item> list_data = new ArrayList<item> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menu);
        atras = findViewById(R.id.imgatras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerMenu.this,InfoRestaurant.class));
                finish();
            }
        });
      /*  for (int i = 0; i < 100; i++) {
            item p = new item();
            p.id = i;
            p.nombre = "Titulo" + i;
            p.description = "Descripcion" + i;
            p.url = "image" + i;
            list_data.add (p);

        }
        listAdapter adapter = new listAdapter(this, list_data);
        listamenu = this.findViewById (R.id.listamenu);
        listamenu.setAdapter (adapter);*/
    }
}
