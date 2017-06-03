package id.co.rumahcoding.myquran.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.rumahcoding.myquran.R;
import id.co.rumahcoding.myquran.models.Surah;

/**
 * Created by blastocode on 2/14/17.
 */

public class SurahItemAdapter extends ArrayAdapter<Surah>{
    private List<Surah> surahList;
    private Context mContext;

    public SurahItemAdapter(Context context, List<Surah> surahList) {
        super(context, R.layout.item_surah, surahList);
        this.surahList = surahList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return surahList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_surah, null);
        }

        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        TextView ayahTextView = (TextView) view.findViewById(R.id.ayah);

        Surah surah = getItem(position);
        titleTextView.setText(surah.getTitle());
        ayahTextView.setText(surah.getAyah() + " Ayat");

        return view;
    }
}
