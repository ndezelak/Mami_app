package com.nejc.mamiapp;
/***********
 * @author Nejc
 * <p/>
 * Description:
 * Service that manages notifications and alarms of the app.
 * TODO: Research lifetime and multihthreading regarding services. What should you use?


/*********** REVISION HISTORY *****************
 *  10/07/2016: Added some comments.
 *
 *
 *
 *
 /***********************************************/

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationService extends Service {


    // This service is started when the "long" alarm triggers
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Debug", "Service flags: " + Integer.toString(flags));
        Log.i("Debug", "Service startID: " + Integer.toString(startId));

       // Create an intent that includes the service itself.
        Intent notoficationIntent = new Intent(getApplicationContext(), NotificationService.class);
        notoficationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Build your Notification and display it.
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this);

        notifBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("VNESI DELOVNE DNI!")
                .setContentText("Vnesi za današnji dan!");
        Boolean contextCompare = this.equals(getApplicationContext());
        Log.i("Debug", "Result of context comparison:" + Boolean.toString(contextCompare));


        // Intent for starting the InputActivity & adding it to the Notification
        Intent weekIntent = new Intent(getApplicationContext(), InputActivity.class);
        weekIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        weekIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Pending intent which should start the weekIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, weekIntent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_CANCEL_CURRENT);

        //Set pending intent to the notification. If the notification is clicked the Inputctivity is launched
        notifBuilder.setContentIntent(pendingIntent);

        // Open database to check if current (and some other day in the last 7 days) hasn't been yet added
        //SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("Workdays", getApplicationContext().MODE_PRIVATE, null);
        SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("Workdays", getApplicationContext().MODE_PRIVATE, null);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (day - 7 < 1) day = 1;
        else day = day - 7;
        Log.i("Debug", "Current day: " + Integer.toString(day));

        int month = calendar.get(Calendar.MONTH);
        Log.i("Debug", "Current month is " + Integer.toString(month));
        Log.i("Debug", "Reading from the database");

        // TODO: Can you do the query smarter?
        // Read Database
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Workdays WHERE month=" + Integer.toString(month + 1) + " AND year=" + Integer.toString(2016), null);
        cursor.moveToFirst();

        Log.i("Debug", "Cursor count" + Integer.toString(cursor.getCount()));

        //Move to the first day
        cursor.move(day);


        int i = 0;
        int type;
        ArrayList<Integer> days2do = new ArrayList<>();

        // TODO: Implement possibility to display which days have still to be inserted
        while (!cursor.isLast() && i < 7) {
            i++;
            type = cursor.getInt(3);
            Log.i("Debug", "Type read for day " + Integer.toString(day + i) + " is " + Integer.toString(type));
            if (type == 0) {
                days2do.add(day + i);
            }

        }

        //Log.i("Debug", "Days to do: "+days2do.toArray().toString());


        // Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {50, 500, 50, 500};
        notifBuilder.setVibrate(pattern);


        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        // here you actually activate the notification.
        Notification notification = notifBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notifBuilder.build());

        // Create
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Pending intent for starting the Notification service
        PendingIntent reNotify = PendingIntent.getService(getApplicationContext(), 0, notoficationIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        manager.cancel(reNotify);
        reNotify = PendingIntent.getService(getApplicationContext(), 0, notoficationIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        // Short alarm creation
        // Trigger this alarm every 20 seconds. It will restart this service.
        manager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 20000, reNotify);
        Log.d("Alarm", "New repeating alarm has been created");


        // Stop the service
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
