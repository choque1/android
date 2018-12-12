package com.example.deivi.pedidosonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deivi.pedidosonline.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EditarAdmi extends AppCompatActivity {
    Button guardar1;
    EditText nom;
    EditText c;
    EditText f;
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_admi);
        //loadData();
        final TextView nombre=(TextView) findViewById(R.id.nombre4);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView ci=(TextView) findViewById(R.id.ci4);
        ci.setText( getIntent().getExtras().getString("ci"));
        final TextView telefono=(TextView) findViewById(R.id.phone4);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView email=(TextView) findViewById(R.id.correo4);
        email.setText( getIntent().getExtras().getString("email"));
        final TextView tipo=(TextView) findViewById(R.id.tipo);

        guardar1 = findViewById(R.id.guardar1);
        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();

            }
        });
    }

    /*private void loadData() {

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://192.168.1.106:7777/api/v1.0/cliente", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                JSONArray aux = timeline;
                Bundle x = getIntent().getExtras();
                //email de la actividad
                String com = x.getString("email");
                try {
                    JSONArray jsonArray = timeline;
                    //https://es.androids.help/q28175
                    //ArrayList<String> datos=new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject ob = (JSONObject) jsonArray.getJSONObject(i);
                            //email del servicio
                            String em = (String) ob.getString("email");
                            if (com.equals(em)) {
                                /*nombre = (String) ob.getString("nombre");
                                ci = (String) ob.getString("ci");
                                fono = (String) ob.getString("telefono");
                                corr = com;
                                nom = findViewById(R.id.nombre4);
                                nom.setText((String) ob.getString("nombre"));
                                c = findViewById(R.id.ci4);
                                c.setText(ob.getString("ci"));
                                f = findViewById(R.id.phone4);
                                f.setText(ob.getString("telefono"));
                                e = findViewById(R.id.correo4);
                                e.setText(com);
                                // Toast.makeText(getApplicationContext(),nombre+" "+ci+" ",Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "no rescato datos", Toast.LENGTH_LONG).show();
                        }

                    }


                } catch (Exception e) {

                }
                ;
            }


        });
    }*/

    //editar menu
    public void sedData() {
       TextView  nombre4 = findViewById(R.id.nombre4);
        TextView ci4 = findViewById(R.id.ci4);
        TextView telefono4 = findViewById(R.id.phone4);
        TextView email4 = findViewById(R.id.correo4);


        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.put("nombre", nombre4.getText().toString());
        params.put("ci", ci4.getText().toString());
        params.put("telefono", telefono4.getText().toString());
        params.put("email", email4.getText().toString());
        Toast.makeText(getApplicationContext(),Data.REGISTER_CLIENTE+"/"+Data.ID_User,Toast.LENGTH_LONG).show();
        client.put(Data.REGISTER_CLIENTE+"/"+Data.ID_User, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");


                AlertDialog alertDialog = new AlertDialog.Builder(EditarAdmi.this).create();
                try {
                    int resp = response.getInt("resp");

                    if (resp == 200) {
                        String msn = response.getString("msn");
                        JSONObject json = response.getJSONObject("dato");
                        final String nombre4_resp = json.getString("nombre");
                        final String ci4_resp = json.getString("ci");
                        final String telefono4_resp = json.getString("telefono");
                        final String email4_resp = json.getString("email");

                        alertDialog.setTitle("Mensaje");
                        alertDialog.setMessage(msn);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(EditarAdmi.this, Admi.class);
                                intent.putExtra("nombre", nombre4_resp);
                                intent.putExtra("ci", ci4_resp);
                                intent.putExtra("telefono", telefono4_resp);
                                intent.putExtra("email", email4_resp);
                                intent.putExtra("tipo",Data.Tipo);


                                startActivity(intent);
                                finish();
                            }
                        });
                        alertDialog.show();
                    } else {
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
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }


        });
    }
}