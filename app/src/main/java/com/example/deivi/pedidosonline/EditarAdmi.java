package com.example.deivi.pedidosonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        loadData();
        guardar1 = findViewById(R.id.guardar1);
        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreR=nom.getText().toString();
                Intent t=new Intent(EditarAdmi.this,Admi.class);
                t.putExtra("nombreMod",nombreR);
                startActivity(t);
                Toast.makeText(EditarAdmi.this,"Se guardo correctamente los datos",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private void loadData() {
        AsyncHttpClient client =new AsyncHttpClient();
        client.get("http://192.168.1.12:7777/api/v1.0/cliente", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                JSONArray aux =timeline;
                Bundle x=getIntent().getExtras();
                //email de la actividad
                String com=x.getString("email");
                try
                {
                    JSONArray jsonArray = timeline;
                    //https://es.androids.help/q28175
                    //ArrayList<String> datos=new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++){
                        try {
                            JSONObject ob=(JSONObject) jsonArray.getJSONObject(i);
                            //email del servicio
                            String em=(String)ob.getString("email");
                            if(com.equals(em)) {
                                /*nombre = (String) ob.getString("nombre");
                                ci = (String) ob.getString("ci");
                                fono = (String) ob.getString("telefono");
                                corr = com;*/
                                nom=findViewById(R.id.nombre4);
                                nom.setText((String) ob.getString("nombre"));
                                c=findViewById(R.id.ci4);
                                c.setText(ob.getString("ci"));
                                f=findViewById(R.id.phone4);
                                f.setText(ob.getString("telefono"));
                                e=findViewById(R.id.correo4);
                                e.setText(com);
                               // Toast.makeText(getApplicationContext(),nombre+" "+ci+" ",Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"no rescato datos",Toast.LENGTH_LONG).show();
                        }

                    }


                }
                catch (Exception e)
                {

                };
            }


        });
    }
}
