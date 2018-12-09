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

public class ResAdapter extends BaseAdapter {
    Context context;
    List<Restaurants> restaurants;

    public ResAdapter(Context context, ArrayList<Restaurants> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public int getCount() {
        return this.restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return this.restaurants.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return this.restaurants.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_ui2, null);
        }
        TextView nombre = (TextView)view.findViewById (R.id.restaurant);
        ImageView image = (ImageView) view.findViewById (R.id.imgrestaurant);
        Glide.with (this.context).load (this.restaurants.get(position).getId()).into (image);
        nombre.setText (this.restaurants.get (position).getNombre ());
        return view;
    }
}
