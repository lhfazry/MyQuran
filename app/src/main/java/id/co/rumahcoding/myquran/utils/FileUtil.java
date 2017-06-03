package id.co.rumahcoding.myquran.utils;

/**
 * Created by blastocode on 2/24/17.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
    public static final String TAG = "FileUtil";
    public static final File ROOT = Environment.getExternalStorageDirectory();

    public static final File getAppDir(){
        File file = new File(ROOT.getAbsolutePath(), "Hatam");
        if(!file.exists()) file.mkdirs();
        return file;
    }

    public static final File getImageDir(){
        File file = new File(getAppDir().getAbsolutePath(), "Images");
        if(!file.exists()) file.mkdirs();

        File nomedia = new File(file.getAbsolutePath(), ".nomedia");

        try {
            if (!nomedia.exists()) nomedia.createNewFile();
        }
        catch (Exception e) {

        }

        return file;
    }

    public static final File getTmpDir(){
        File file = new File(getAppDir().getAbsolutePath(), "Tmp");
        if(!file.exists()) file.mkdirs();
        return file;
    }

    public static final File getAudioDir(){
        File file = new File(getAppDir().getPath(), "Audios");
        if(!file.exists()) file.mkdirs();

        File nomedia = new File(file.getAbsolutePath(), ".nomedia");

        try {
            if (!nomedia.exists()) nomedia.createNewFile();
        }
        catch (Exception e) {

        }

        return file;
    }

    public static final File getAudioFile(String name, boolean createIfNotExist) {
        File file = new File(getAudioDir().getAbsolutePath(), name);

        if(!file.exists() && createIfNotExist) {
            try {
                file.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }

        return file;
    }

    public static final File getImageFile(String name, boolean createIfNotExist){
        Log.d(TAG, "Get image file: " + name);
        File file = new File(getImageDir().getAbsolutePath(), name);

        if(!file.exists() && createIfNotExist) {
            try {
                file.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }

        return file;
    }

    public static final File getTmpFile(String name, boolean createIfNotExist){
        File file = new File(getTmpDir().getAbsolutePath(), name);

        if(!file.exists() && createIfNotExist) {
            try {
                file.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage());
            }
        }

        return file;
    }

    public static final int getImageCount(){
        File[] files = getImageDir().listFiles();

        if(files != null) {
            return files.length;
        }
        else {
            return 0;
        }
    }

    public static final List<String> checkAudios(List<String> audios) {
        List<String> audioList = new ArrayList<>();
        Iterator<String> iterator = audios.iterator();

        while(iterator.hasNext()) {
            final String audio = iterator.next();

            File[] files = getAudioDir().listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    if(s.contains(audio)) {
                        return true;
                    }

                    return false;
                }
            });

            if(files.length == 0) {
                audioList.add(audio);
            }
        }

        return audioList;
    }
}
