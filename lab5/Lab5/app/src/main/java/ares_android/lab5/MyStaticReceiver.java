package ares_android.lab5;

import android.app.Notification;
//import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

/**
 * Created by 73454 on 2017/11/6.
 */

public class MyStaticReceiver extends BroadcastReceiver {
    private Merchandise tempMerchandise;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.lab5.MyStaticFilter")) {
            tempMerchandise = (Merchandise)intent.getSerializableExtra("Merchandise");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), tempMerchandise.getPicname());
            Intent to_details = new Intent(context, details.class);
            to_details.putExtra("Merchandise", tempMerchandise);
            builder.setContentTitle("新商品热卖")
                    .setContentText(tempMerchandise.getName()+"仅售¥"+Double.toString(tempMerchandise.getPrice()))
                    .setTicker("您有一条新消息")
                    .setSmallIcon(tempMerchandise.getPicname())
                    .setLargeIcon(bm)
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, (int) System.currentTimeMillis(), to_details, PendingIntent.FLAG_UPDATE_CURRENT));
            Notification notify = builder.build();
            manager.notify((int) System.currentTimeMillis(), notify);
        }
    }
}
