package id.co.rumahcoding.myquran.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import id.co.rumahcoding.myquran.R;
import id.co.rumahcoding.myquran.models.Juz;


/**
 * Created by blastocode on 2/14/17.
 */

public class JuzItemAdapter extends ArrayAdapter<Juz> {
    private List<Juz> juzList;
    private Context mContext;

    public JuzItemAdapter(Context context, List<Juz> juzList) {
        super(context, R.layout.item_juz, juzList);
        this.juzList = juzList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return juzList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_juz, null);
        }

        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        TextView pageTextView = (TextView) view.findViewById(R.id.page);

        Juz juz = getItem(position);
        titleTextView.setText("Juz " + juz.juz);
        pageTextView.setText("Halaman " + juz.page);

        return view;
    }
}
