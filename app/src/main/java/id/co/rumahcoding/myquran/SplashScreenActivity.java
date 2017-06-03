package id.co.rumahcoding.myquran;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.rumahcoding.myquran.models.Surah;
import id.co.rumahcoding.myquran.utils.FileDownloadService;
import id.co.rumahcoding.myquran.utils.FileUtil;
import io.realm.Realm;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = "SplashScreenActivity";
    private static int SPLASH_TIME_OUT = 3000;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.textView)
    TextView textView;

    private boolean isDownloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        /* Request user permissions in runtime */
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashScreenActivity.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.INTERNET
                    },
                    100);
            return;
        }

        /* Request user permissions in runtime */
        init();
    }

    private void init() {
        final Realm realm = Realm.getDefaultInstance();
        long numberOfSurah = realm.where(Surah.class).count();

        if(numberOfSurah < 114) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Initializing ...");

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.delete(Surah.class);
                    importSurahFromCSV(realm);
                }
            });
        }

        realm.close();

        int imageCount = FileUtil.getImageCount();

        if(imageCount < 6839) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            textView.setVisibility(View.VISIBLE);

            progressBar.setProgress(0);
            textView.setText("Downloading data ...");
            downloadQuranImages();
        }
        else {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoNextScreen();
                }
            }, SPLASH_TIME_OUT);
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void downloadQuranImages() {
        String serverFilePath = "https://www.fazrilabs.com/quran.zip";
        File file = FileUtil.getTmpFile("images.zip", true);

        String localPath = file.getAbsolutePath();
        String unzipPath = FileUtil.getImageDir().getAbsolutePath();

        FileDownloadService.DownloadRequest downloadRequest = new FileDownloadService.DownloadRequest(serverFilePath, localPath);
        downloadRequest.setRequiresUnzip(true);
        downloadRequest.setDeleteZipAfterExtract(true);
        downloadRequest.setUnzipAtFilePath(unzipPath);

        FileDownloadService.OnDownloadStatusListener listener = new FileDownloadService.OnDownloadStatusListener() {

            @Override
            public void onDownloadStarted() {
                //subscriber.onNext(String.valueOf(0));
                isDownloading = true;
            }

            @Override
            public void onDownloadCompleted(List<String> filenames) {
                isDownloading = false;
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                gotoNextScreen();
            }

            @Override
            public void onDownloadFailed() {
                isDownloading = false;
                textView.setText("Download failed. Retry");
            }

            @Override
            public void onDownloadProgress(int progress) {
                progressBar.setProgress(progress);
                textView.setText("Downloading data ... " + progress + "%");

                if(progress == 100) {
                    textView.setText("Please wait, processing data ...");
                }
            }
        };

        FileDownloadService.FileDownloader downloader = FileDownloadService.FileDownloader.getInstance(downloadRequest, listener);
        downloader.download(this);
    }

    private void gotoNextScreen() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            init();
        } else {
            finish();
        }

        return;
    }

    @OnClick(R.id.textView)
    public void OnTextViewClick(TextView textView) {
        if(!isDownloading) {
            downloadQuranImages();
        }
    }

    private void importSurahFromCSV(Realm realm) {
        try {
            InputStreamReader is = new InputStreamReader(getAssets()
                    .open("surah.csv"));
            BufferedReader reader = new BufferedReader(is);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                Surah surah = new Surah();
                surah.setId(Integer.parseInt(row[0]));
                surah.setTitle(row[1]);
                surah.setAyah(Integer.parseInt(row[2]));

                realm.insert(surah);
            }
        }
        catch (IOException exception) {
            Log.e(TAG, exception.toString());
        }
    }
}
