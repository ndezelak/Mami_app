package com.nejc.mamiapp.activities;


/**
 * @author Nejc
 * <p/>
 * Description:
 * After reconstruction this activity only contains a ViewPager object and a home button
 * All the important logic is found inside the ViewPagerAdapter and Fragments that represent
 * the ViewPager.
 * <p/>
 * FragmentManager is responsible for showing and hiding the ChooserFragment. Communication between
 * the ViewPager fragment and the chooser Fragment goes through an interface
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.fragments_and_adapters.ChooserFragment;
import com.nejc.mamiapp.fragments_and_adapters.ViewPagerAdapter;
import com.nejc.mamiapp.helpers.InterFragmentInterface;


/***********
 * REVISION HISTORY *****************
 * 10/07/2016: Added some comments
 * <p/>
 * 21/08/2016:
 * + Resconstruction and simplification of the old Activity version.
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * /
 ***********************************************/

// TODO: Activity should implement a communication interface which manipulates the chooser fragment
public class InputActivity extends AppCompatActivity implements InterFragmentInterface {


    PendingIntent reNotify;
    Context context;
    SharedPreferences pref;

    // XML Widgets
    ViewPager pager = null;
    android.app.Fragment fragmentToAdd = null;

    @Override
    // Initializes all the GUI components
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set activity layout
        setContentView(R.layout.activity_week);

        // App context and preference file
        context = getApplicationContext();
        pref = context.getSharedPreferences("Constants", context.MODE_PRIVATE);
        fragmentToAdd = (android.app.Fragment) new ChooserFragment();
        // All GUI objects
        pager = (ViewPager) findViewById(R.id.pager);


        // ************ ViewPager initialization *******************//

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);

        //startService(intent);

    }//End of onCreate

    @Override
    // Display current month and day and highlight that day in the listview
    protected void onResume() {
        Log.i("Database", "You are inside on Resume");
        super.onResume();
        AlarmManager manager = (AlarmManager) this.getSystemService(ALARM_SERVICE);

/*
        // Here you cancel all the Alarms and initialize a new one at 9 pm next day
        Log.d("Alarm", "You are inside the onResume Callback");

        //  Get the Alarm manager
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Intent that starts a service
        Intent intent = new Intent(this, CreateNotificationService.class);

        // Close the repeating Alarm after you've started this activity.
        reNotify = PendingIntent.getService(this, 1, intent, 0);

        if (reNotify != null) {
            manager.cancel(reNotify);
            Log.d("Alarm", "Repeating alarm has been canceled");
        }

        // If the specified pending intent is not yet active, null is returned
       // sendNotification = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE);

        //If the pending intent is already in use, cancel it first.
        sendNotification = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        manager.cancel(sendNotification);
        sendNotification = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        // If the Pending Intent doesn't exist anymore then create a new one
        // Here the long alarm is initialized and started. All other alarms should
        // be inactive up to this point.

        manager.set(AlarmManager.RTC, calendar.getTimeInMillis(), sendNotification);
        Log.d("Alarm", "New nonrepeating alarm has been created");
        Log.d("Alarm", "System clock in miliseconds: "+Long.toString(System.currentTimeMillis()));
*/
    }
    // Interfragment communication interface


    // This method should be called when a listview item from the ViewPager fragment is clicked
    @Override
    public void onListItemClicked(int date, int month, int year) {
        android.app.FragmentManager manager = getFragmentManager();
        if (manager.findFragmentById(R.layout.fragment_chooser) == null) {
            android.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.animator.translate_vertical_up, R.animator.translate_vertical_up);
            transaction.add(R.id.linearlayout_week_activity, fragmentToAdd);
            transaction.commit();
        }
    }

    // This method should be called when the user choose an item (or presses something else)
    @Override
    public void onHideChooser(int itemSelected) {
        // Hide ChooserFragment
        // Update Listview with the newly selected item
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.animator.translate_vertical_down, R.animator.translate_vertical_down);
        transaction.remove(fragmentToAdd);
        transaction.commit();
    }

}// End of Activity
