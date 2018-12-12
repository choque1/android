package collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deivi.pedidosonline.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends BaseAdapter {
    Context context;
    List<Menus> menus;

    public MenuAdapter(Context context, ArrayList<Menus> menus) {
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
        return this.menus.get(position).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_ui, null);
        }
        TextView nombre = view.findViewById (R.id.nombreproducto);
        TextView descripcion = view.findViewById (R.id.descripcionproducto);
        TextView precio = view.findViewById(R.id.precioproducto);
        ImageView image =  view.findViewById (R.id.imgproducto);
        Button pedir = view.findViewById(R.id.pedirproducto);
        Glide.with (this.context).load (this.menus.get(i).getId()).into (image);
        nombre.setText (this.menus.get (i).getNombre ());
        descripcion.setText (this.menus.get (i).getDescripcion());
        precio.setText(this.menus.get(i).getPrecio().toString());


        return view;
    }


}
