package collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deivi.pedidosonline.R;

import java.util.ArrayList;
import java.util.List;

public class restAdapter extends BaseAdapter {
    private Context con2;
    private List<item3> items3;


    public restAdapter(Context con2, ArrayList<item3> items3) {
        this.con2 = con2;
        this.items3 = items3;
    }

    @Override
    public int getCount() {
        return this.items3.size();
    }

    @Override
    public Object getItem(int i) {
        return this.items3.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(this.items3.get(i).getId1());
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.con2.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_ui3, null);
        }
        TextView nombre = (TextView)view.findViewById (R.id.restaurant);
        TextView nit = (TextView)view.findViewById (R.id.NIT2);
        TextView propietario = view.findViewById(R.id.property2);
        TextView telefono = view.findViewById(R.id.phone2);
        TextView calle = view.findViewById(R.id.street2);
        ImageView image = (ImageView) view.findViewById (R.id.imgrestaurant);
        Glide.with (this.con2).load (this.items3.get(i).getId1 ()).into (image);
        nombre.setText (this.items3.get (i).getNombre ());
        nit.setText (this.items3.get (i).getNit ());
        propietario.setText(this.items3.get(i).getPropietario());
        telefono.setText(this.items3.get(i).getTelefono());
        calle.setText(this.items3.get(i).getCalle());

        return view;
    }
}
