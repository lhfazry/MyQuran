package id.co.rumahcoding.myquran;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import id.co.rumahcoding.myquran.utils.ImageAdapter;
import id.co.rumahcoding.myquran.utils.QuranUtil;

public class QuranActivity extends AppCompatActivity {
    public static final String KEY_SURAH_ID = "surah_id";
    public static final String KEY_PAGE = "page";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ImageAdapter mImageAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        int surahId = (int) getIntent().getLongExtra(KEY_SURAH_ID, 0L);
        int page = getIntent().getIntExtra(KEY_PAGE, 0);

        if(surahId > 0) {
            page = QuranUtil.SURAH_PAGES[surahId - 1];
        }

        mImageAdapter = new ImageAdapter(this, 6839);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mImageAdapter);

        mViewPager.setCurrentItem(6839 - page);
    }
}
