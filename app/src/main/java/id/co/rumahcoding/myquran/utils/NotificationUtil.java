package id.co.rumahcoding.myquran.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import id.co.rumahcoding.myquran.R;


/**
 * Created by blastocode on 4/7/16.
 */
public class NotificationUtil {
    public static void showNotification(Context context, PendingIntent pendingIntent, String message) {
        NotificationCompat.Builder builder  = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, builder.build());
    }
}
