package collections;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.deivi.pedidosonline.R;
import com.example.deivi.pedidosonline.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BorrarMenuAdapter extends BaseAdapter {
    Context context;
    List<Menus> menus;

    public BorrarMenuAdapter(Context context, ArrayList<Menus> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return this.menus.size();
    }

    @Override
    public Object getItem(int position) {
        return this.menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong((String)this.menus.get(position).getId());

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.borrar_menu, null);
        }
        TextView nombre = view.findViewById (R.id.nombreproducto);
        TextView descripcion = view.findViewById (R.id.descripcionproducto);
        TextView precio = view.findViewById(R.id.precioproducto);
        ImageView image =  view.findViewById (R.id.imgproducto);
        Button borrar = view.findViewById(R.id.borrarproducto);
        Glide.with (this.context).load (this.menus.get(i).getId()).into (image);
        nombre.setText (this.menus.get (i).getNombre ());
        descripcion.setText (this.menus.get (i).getDescripcion());
        precio.setText(this.menus.get(i).getPrecio().toString());

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // Add the buttons
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        // User clicked OK button
                        deleteMenu();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.setTitle("Esta seguro de eliminar el menu");

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            private void deleteMenu() {
                AsyncHttpClient client = new AsyncHttpClient();

                client.delete("http://192.168.1.102:7777/api/v1.0/menus" + "/" + Data.ID_Menus ,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String message = response.getString("message");
                            if (message != null){
                                Toast.makeText(context,message,Toast.LENGTH_LONG).show();


                            }else   {
                                Toast.makeText(context,"Error al borrar",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        });




        return view;
    }


}
