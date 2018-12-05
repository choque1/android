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

public class restaurantAdapter extends BaseAdapter {
    private Context con1;
    private List<item2> items1;

    public restaurantAdapter(Context con1, ArrayList<item2> items1) {
        this.con1 = con1;
        this.items1 = items1;
    }

    @Override
    public int getCount() {
        return this.items1.size ();
    }

    @Override
    public Object getItem(int i) {
        return this.items1.get (i);
    }

    @Override
    public long getItemId(int i) {
        return this.items1.get (i).getId ();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflate = (LayoutInflater) this.con1.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate (R.layout.item_ui2, null);
        }
        TextView restaurant = (TextView)view.findViewById (R.id.restaurant);
        ImageView image = (ImageView) view.findViewById (R.id.imgrestaurant);
        Glide.with (this.con1).load (this.items1.get(i).getUrl2 ()).into (image);
        restaurant.setText (this.items1.get (i).getNombre ());

        return view;
    }
}
