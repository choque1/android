package com.example.deivi.pedidosonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deivi.pedidosonline.utils.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class RegistrarRestaurant extends AppCompatActivity implements OnMapReadyCallback {
    private MapView map;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private TextView street;
    private Button crear;
    private LatLng mainposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurant);
        map = findViewById(R.id.MapView);
        map.onCreate(savedInstanceState);
        MapsInitializer.initialize(this);
        map.getMapAsync(this);
        geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        street = findViewById(R.id.street);
        crear = findViewById(R.id.crear);

    }
    public void sedData(){
        TextView nombre  = findViewById(R.id.name1);
        TextView nit  = findViewById(R.id.nit);
        TextView propietario = findViewById(R.id.propietario);
        TextView calle  = findViewById(R.id.street);
        TextView telefono  = findViewById(R.id.phone1);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.add("nombre", nombre.getText().toString());
        params.add("nit", nit.getText().toString());
        params.add("propietario",propietario.getText().toString());
        params.add("calle",calle.getText().toString());
        params.add("telefono",telefono.getText().toString());
        params.add("lat", String.valueOf( mainposition.latitude));
        params.add("lon", String.valueOf( mainposition.longitude));

        client .post(Data.REGISTER_RESTORANT, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");



                AlertDialog alertDialog = new AlertDialog.Builder(RegistrarRestaurant.this).create();
                try {
                    int resp = response.getInt("resp");

                    if(resp==200){
                    String msn = response.getString("msn");
                    JSONObject json=response.getJSONObject("dato");
                    final String name_resp=json.getString("nombre");
                    final String calle_resp=json.getString("calle");
                    final String tel_resp=json.getString("telefono");
                    final String propietario_resp=json.getString("propietario");
                    final String _id_reps=json.getString("_id");
                    alertDialog.setTitle("Mensaje");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent =new Intent(RegistrarRestaurant.this, InfoRestaurant.class);
                            intent.putExtra("nombre",name_resp);
                            intent.putExtra("calle",calle_resp);
                            intent.putExtra("telefono",tel_resp);
                            intent.putExtra("propietario",propietario_resp);
                            intent.putExtra("_id",_id_reps);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alertDialog.show();
                    }else{
                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage("Error al tratar de crear nuevo restaurant");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(-19.5783329, -65.7563853);
        mainposition = potosi;
        mMap.addMarker(new MarkerOptions().position(potosi).title("Ubicame").zIndex(15).draggable(true));
        mMap.setMinZoomPreference(15);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(potosi));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener(){


            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mainposition = marker.getPosition();
                String street_string =  getStreet(marker.getPosition().latitude,marker.getPosition().longitude);
                street.setText(street_string);
            }
        });

    }
    public String getStreet (Double lat, Double lon){
        List<Address> address;
        String result = "";
        try {
            address = geocoder.getFromLocation(lat, lon, 1);
            result += address.get(0).getThoroughfare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
