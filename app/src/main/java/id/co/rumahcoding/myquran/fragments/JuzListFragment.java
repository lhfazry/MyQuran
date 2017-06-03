package id.co.rumahcoding.myquran.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.rumahcoding.myquran.QuranActivity;
import id.co.rumahcoding.myquran.R;
import id.co.rumahcoding.myquran.adapters.JuzItemAdapter;
import id.co.rumahcoding.myquran.models.Juz;
import id.co.rumahcoding.myquran.utils.QuranUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuzListFragment extends Fragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.listView)
    ListView listView;

    private JuzItemAdapter mAdapter;

    public JuzListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_juz_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());

        mAdapter = new JuzItemAdapter(getActivity(), new ArrayList<Juz>());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        loadData();
    }

    private void loadData() {
        for(int i = 0; i < QuranUtil.JUZ_PAGES.length; i++) {
            Juz juz = new Juz();
            juz.juz = i + 1;
            juz.page = QuranUtil.JUZ_PAGES[i];

            mAdapter.add(juz);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Juz juz = mAdapter.getItem(i);

        Intent intent = new Intent(getActivity(), QuranActivity.class);
        intent.putExtra(QuranActivity.KEY_PAGE, juz.page);
        startActivity(intent);
    }
}