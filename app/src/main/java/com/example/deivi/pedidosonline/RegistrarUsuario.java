package com.example.deivi.pedidosonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deivi.pedidosonline.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistrarUsuario extends AppCompatActivity {
    Button crearcuenta;
    Spinner tipo;
    EditText name,ci,phone,correo1,password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_registrar_usuario);
        name = findViewById(R.id.name1);
        ci = findViewById(R.id.ci);
        phone = findViewById(R.id.phone1);
        correo1 = findViewById(R.id.correo1);
        password1 = findViewById(R.id.password1);
        crearcuenta = findViewById (R.id.crearcuenta);
        tipo = findViewById(R.id.tipo);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,
                R.array.tipousuario, android.R.layout.simple_spinner_item);
        tipo.setAdapter(adapter);

        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sedData();
            }

        });



    }
    public void Registrar(View view){
        String name1 = name.getText().toString();
        String ci1 = ci.getText().toString();
        String phone1 = phone.getText().toString();
        String correo11 = correo1.getText().toString();
        String password11 = password1.getText().toString();

        if (name1.length() == 0){
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_SHORT).show();
        }
        if (ci1.length() == 0){
            Toast.makeText(this, "Debes ingresar tu CI", Toast.LENGTH_SHORT).show();
        }

        if (phone1.length() == 0){
            Toast.makeText(this, "Debes ingresar tu telefono", Toast.LENGTH_SHORT).show();
        }

        if (correo11.length() == 0){
            Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_SHORT).show();
        }
        if (password11.length() == 0){
            Toast.makeText(this, "Debes ingresar un password", Toast.LENGTH_SHORT).show();
        }

        if (name1.length()!=0 && ci1.length()!=0 && phone1.length()!=0 && correo11.length()!=0  && password11.length()!=0) {
            Toast.makeText(this, "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
            startActivity (new Intent (RegistrarUsuario.this, Login.class));
            finish ();

        }



    }
    public void sedData(){
        TextView nombre  = findViewById(R.id.name1);
        TextView ci  = findViewById(R.id.ci);
        TextView telefono = findViewById(R.id.phone1);
        TextView email  = findViewById(R.id.correo1);
        TextView password  = findViewById(R.id.password1);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("authorization", Data.TOKEN);


        RequestParams params = new RequestParams();

        params.add("nombre", nombre.getText().toString());
        params.add("ci", ci.getText().toString());
        params.add("telefono",telefono.getText().toString());
        params.add("email",email.getText().toString());
        params.add("password",password.getText().toString());

        client .post(Data.REGISTER_USER, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
                AlertDialog alertDialog = new AlertDialog.Builder(RegistrarUsuario.this).create();
                try {
                    String msn = response.getString("msn");
                    alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }

}