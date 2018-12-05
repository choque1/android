package com.example.deivi.pedidosonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import collections.item;
import collections.listAdapter;

public class VerMenu extends AppCompatActivity {
    ListView listamenu;


    ArrayList<item> list_data = new ArrayList<item> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menu);
        for (int i = 0; i < 100; i++) {
            item p = new item();
            p.id = i;
            p.title = "Titulo" + i;
            p.description = "Descripcion" + i;
            p.url = "image" + i;
            list_data.add (p);

        }
        listAdapter adapter = new listAdapter(this, list_data);
        listamenu = this.findViewById (R.id.listamenu);
        listamenu.setAdapter (adapter);
    }
}
