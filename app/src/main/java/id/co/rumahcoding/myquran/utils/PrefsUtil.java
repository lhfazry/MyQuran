package id.co.rumahcoding.myquran.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;



public class PrefsUtil {
    private static final String PREF_KEY = "shared_preference";

    private static PrefsUtil instance;
    private Context context;

    public static void initInstance(final Context c) {
        if(instance == null) {
            instance = new PrefsUtil(c);
        }
    }

    public static PrefsUtil getInstance() {
        return instance;
    }
    
    private PrefsUtil(Context c) {
        this.context = c;
    }
    
    private String getKey(String mKey) {
        return mKey;
    }

    public boolean setNumberState(String mKey, final int state) {
        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getKey(mKey), state);
            editor.commit();
            return true;
        }
        else{
            return false;
        }
    }

    public int getNumberState(String mKey, final int defValue) {
        int state = 0;

        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);
            state = sharedPref.getInt(getKey(mKey), defValue);
        }

        return state;
    }

    public boolean setNumberState(String mKey, final long state) {
        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putLong(getKey(mKey), state);
            editor.commit();
            return true;
        }
        else{
            return false;
        }
    }

    public long getNumberState(String mKey, final long defValue) {
        long state = 0;

        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);
            state = sharedPref.getLong(getKey(mKey), defValue);
        }

        return state;
    }


    public boolean setStringState(String mKey, String state) {
        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();            
            editor.putString(getKey(mKey), state);            
            editor.commit();
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getStringState(String mKey, String defValue) {
        String state = "";
        
        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);          
            state = sharedPref.getString(getKey(mKey), defValue);
        }
        
        return state;
    }
    
    public boolean setBooleanState(String mKey, boolean state) {
        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();            
            editor.putBoolean(getKey(mKey), state);            
            editor.commit();
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean getBooleanState(String mKey, boolean defValue) {
        boolean state = false;
        
        if(context != null){
            SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY,
                    Context.MODE_PRIVATE);          
            state = sharedPref.getBoolean(getKey(mKey), defValue);
        }
        
        return state;
    }
}