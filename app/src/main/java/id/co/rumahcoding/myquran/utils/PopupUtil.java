package id.co.rumahcoding.myquran.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class PopupUtil {
    private static ProgressDialog dialog;
    public static final int SHORT = Toast.LENGTH_SHORT;
    public static final int LONG = Toast.LENGTH_LONG;

    public static final void showMsg(final Context c, final String msg, int duration){
        try{
            Toast.makeText(c, msg, duration).show();
        }
        catch(Exception e){}
    }

    public static final void showLoading(final Context context, final String title, final String msg){
        try {
            dialog = ProgressDialog.show(context, title, msg);
        }
        catch(Exception e){}
    }

    public static final void showProgress(final Context context, final String title, final String msg){
        try {
            dialog = new ProgressDialog(context);
            dialog.setTitle(title);
            dialog.setMessage(msg);
            dialog.setIndeterminate(true);
            dialog.setMax(100);
            dialog.show();
        }
        catch(Exception e){}
    }

    public static final void updateProgress(int progress){
        try{
            if(dialog != null) dialog.setProgress(progress);
        }
        catch(Exception e){}
    }

    public static final void dismissDialog(){
        try{
            if(dialog != null) dialog.dismiss();
        }
        catch(Exception e){}
    }

    public static final void alert(final Context context, final String msg,
                                   final String positif, final String negatif, final DialogInterface.OnClickListener listener){
        try{
            if(context == null || listener == null) throw new NullPointerException();
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage(msg);
            alert.setPositiveButton(positif, listener);
            if(negatif!=null) alert.setNegativeButton(negatif, listener);
            else alert.setCancelable(false);
            alert.show();
        }
        catch(Exception e){}
    }

}