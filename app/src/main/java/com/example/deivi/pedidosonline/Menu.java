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
import android.widget.ListView;

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
                final Menus item = list_data.get (position);
                AlertDialog.Builder builder = new AlertDialog.Builder (Menu.this);
                LayoutInflater inflater = (Menu.this).getLayoutInflater();
                builder.setTitle ("Cantidad")
                        .setView (inflater.inflate(R.layout.dialogo,null))

                        .setPositiveButton ("Aceptar", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Intent i = new Intent(Menu.this,Carrito.class);
                                i.putExtra ("Titulo", item.nombre);
                                i.putExtra ("Descripcion", item.descripcion );
                                i.putExtra("precio",item.precio);
                                i.putExtra ("image",item.foto);
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
                        menus.setFoto(object.getString("foto"));
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