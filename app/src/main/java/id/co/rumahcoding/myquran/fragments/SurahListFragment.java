package id.co.rumahcoding.myquran.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.rumahcoding.myquran.QuranActivity;
import id.co.rumahcoding.myquran.R;
import id.co.rumahcoding.myquran.adapters.SurahItemAdapter;
import id.co.rumahcoding.myquran.models.Surah;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class SurahListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "SurahListFragment";

    @BindView(R.id.listView)
    ListView listView;

    private SurahItemAdapter mAdapter;
    private List<Surah> surahList = new ArrayList<Surah>();

    public SurahListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_surah_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());

        mAdapter = new SurahItemAdapter(getActivity(), surahList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        loadDataFromRealm();
    }

    private void loadDataFromRealm() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Surah> surahRealmResults = realm.where(Surah.class).findAll();
        Iterator<Surah> iterator = surahRealmResults.iterator();
        Log.d(TAG, "Jumlah surat:" + surahRealmResults.size());

        while (iterator.hasNext()) {
            Surah surah = iterator.next();
            surahList.add(surah);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Surah surah = mAdapter.getItem(i);

        Intent intent = new Intent(getActivity(), QuranActivity.class);
        intent.putExtra(QuranActivity.KEY_SURAH_ID, (long) surah.getId());
        startActivity(intent);
    }
}