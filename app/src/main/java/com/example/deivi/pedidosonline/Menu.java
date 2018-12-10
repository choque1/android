package com.example.deivi.pedidosonline;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import collections.MenuAdapter;
import collections.Menus;
import cz.msebera.android.httpclient.Header;

public class Menu extends AppCompatActivity {
   ListView lista;
   EditText c;
   TextView p;
   String precios ,cant, total1;
   Double pre,total,can;

   ArrayList<Menus> list_data = new ArrayList<Menus> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lista = Menu.this.findViewById (R.id.lismenu);
        loadComponents();
        lista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Menus menus= list_data.get (position);
                AlertDialog.Builder builder = new AlertDialog.Builder (Menu.this);
                LayoutInflater inflater = (Menu.this).getLayoutInflater();
                builder.setTitle("Cantidad");
                builder.setView(inflater.inflate(R.layout.dialogo, null));
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c = findViewById(R.id.cantidad);
                        cant = c.getText().toString();
                        p = findViewById(R.id.precio);
                        precios = p.getText().toString();
                        pre = Double.parseDouble(precios);
                        can = Double.parseDouble(cant);
                        total = pre * can;
                        total1 = String.valueOf(total);
                        p.setText(total1);
                        menus.setPrecio(total1);
                        list_data.add(menus);
                        MenuAdapter adapter =  new MenuAdapter(Menu.this,list_data);
                        lista.setAdapter(adapter);



                        Intent i = new Intent(Menu.this, Carrito.class);
                        i.putExtra("nombre", menus.nombre);
                        i.putExtra("descripcion", menus.descripcion);
                        i.putExtra("precio", menus.precio);
                        // i.putExtra ("image",item.foto);
                        finish();
                        startActivity(i);


                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                Dialog dialog = builder.create ();
                dialog.show ();


            }
        });


    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.1.108:7777/api/v1.0/menus",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {

                try {

                    for (int i =0 ; i < jsonArray.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = jsonArray.getJSONObject(i);
                        menus.setId(i);
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getString("precio"));
                        //menus.setFoto(object.getString("foto"));
                        list_data.add(menus);
                    }
                    MenuAdapter adapter =  new MenuAdapter(Menu.this,list_data);
                    lista.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });




    }




}