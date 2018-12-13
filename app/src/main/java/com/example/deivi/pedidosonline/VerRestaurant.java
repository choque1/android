package com.example.deivi.pedidosonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collections.MenuAdapter;
import collections.Menus;
import collections.ResAdapter;
import collections.Restaurants;
import cz.msebera.android.httpclient.Header;

public class VerRestaurant extends AppCompatActivity {
    ListView listares;
    ArrayList<Restaurants>restaurants=new ArrayList<Restaurants>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurant);
        loadComponents();
    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.1.102:7777/api/v1.0/restaurant",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants restaurants1 = new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        restaurants1.setId(object.getString("_id"));
                        restaurants1.setNombre(object.getString("nombre"));
                        restaurants1.setTelefono(object.getInt("telefono"));
                        restaurants1.setCalle(object.getString("calle"));
                        restaurants1.setImagen(object.getString("fotolugar"));
                        restaurants.add(restaurants1);
                    }
                    ResAdapter adapter =  new ResAdapter(VerRestaurant.this,restaurants);
                    listares = findViewById(R.id.lisrestaurant);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });




    }
}
