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
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

/**
 * Created by 73454 on 2017/11/7.
 */

public class MyDynamicReceiver extends BroadcastReceiver {
    private Merchandise tempMerchandise;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.lab5.MyDynamicFilter")) {
            tempMerchandise = (Merchandise)intent.getSerializableExtra("Merchandise");
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), tempMerchandise.getPicname());
            Intent back_to_carts = new Intent(context, MainActivity.class);
            Bundle senddata = new Bundle();
            senddata.putString("Backto","ListView");
            senddata.putBoolean("notify_id_flag", true);
            back_to_carts.putExtras(senddata);
            builder.setContentTitle("马上下单")
                    .setContentText(tempMerchandise.getName()+"已添加到购物车")
                    .setTicker("您有一条新消息")
                    .setSmallIcon(tempMerchandise.getPicname())
                    .setLargeIcon(bm)
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, (int) System.currentTimeMillis(), back_to_carts,PendingIntent.FLAG_UPDATE_CURRENT));
            Notification notify = builder.build();
            manager.notify((int) System.currentTimeMillis(), notify);
        }
    }
}
