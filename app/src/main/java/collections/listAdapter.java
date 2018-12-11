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

public class listAdapter extends BaseAdapter{
    private Context context;
    private List<item> items;
    public listAdapter(Context context, ArrayList<item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size ();
    }

    @Override
    public Object getItem(int i) {
        return this.items.get (i);
    }

    @Override
    public long getItemId(int i) {
        return this.items.get (i).getId ();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_ui, null);
        }
        TextView nombre = (TextView)view.findViewById (R.id.nomproduc);
        TextView descripcion = (TextView)view.findViewById (R.id.description);
        TextView precio = view.findViewById(R.id.prec);
        TextView bs = view.findViewById(R.id.text);
        ImageView image = (ImageView) view.findViewById (R.id.imagesource);
        Glide.with (this.context).load (this.items.get(i).getUrl ()).into (image);
        nombre.setText (this.items.get (i).getNombrepro ());
        descripcion.setText (this.items.get (i).getDescription ());
        precio.setText(this.items.get(i).getPrecio());
        bs.setText("Bs");

        return view;
    }
}