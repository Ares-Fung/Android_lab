package ares_android.lab5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public  void  onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.my_widget);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        Bundle receivedata = new Bundle();
        receivedata = intent.getExtras();
        if(intent.getAction().equals("com.lab5.MyStaticWidgetFilter")) {
            Merchandise tempMerchandise = (Merchandise) receivedata.getSerializable("Merchandise");
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), tempMerchandise.getPicname());
            remoteViews.setImageViewBitmap(R.id.appwidget_image, bm);
            remoteViews.setTextViewText(R.id.appwidget_text,tempMerchandise.getName()+"仅售¥"+Double.toString(tempMerchandise.getPrice())+"!");
            Intent todetail = new Intent(context, details.class);
            Bundle senddata = new Bundle();
            senddata.putSerializable("Merchandise",tempMerchandise);
            todetail.putExtras(senddata);
            PendingIntent pi = PendingIntent.getActivity(context, (int) System.currentTimeMillis() , todetail,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.appwidget_image, pi);
            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pi);
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), MyWidget.class), remoteViews);
        };
        if(intent.getAction().equals("com.lab5.MyDynamicWidgetFilter")) {
            Merchandise tempMerchandise = (Merchandise) receivedata.getSerializable("Merchandise");
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), tempMerchandise.getPicname());
            remoteViews.setImageViewBitmap(R.id.appwidget_image, bm);
            remoteViews.setTextViewText(R.id.appwidget_text,tempMerchandise.getName()+"已添加到购物车");
            Intent toMainActivity = new Intent(context, MainActivity.class);
            Bundle senddata = new Bundle();
            senddata.getString("Backto","ListView");
            toMainActivity.putExtras(senddata);
            PendingIntent pi = PendingIntent.getActivity(context, (int) System.currentTimeMillis() , toMainActivity, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.appwidget_image, pi);
            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pi);
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), MyWidget.class), remoteViews);
        };
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        //for (int appWidgetId : appWidgetIds) {
        //    updateAppWidget(context, appWidgetManager, appWidgetId);
        //}
        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        updateViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
        updateViews.setOnClickPendingIntent(R.id.appwidget_image, pendingIntent);
        ComponentName me = new ComponentName(context, MyWidget.class);
        appWidgetManager.updateAppWidget(me, updateViews);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

