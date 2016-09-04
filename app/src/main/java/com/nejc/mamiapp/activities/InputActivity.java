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
import android.widget.Toast;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.fragments_and_adapters.ChooserFragment;
import com.nejc.mamiapp.fragments_and_adapters.ViewPagerAdapter;
import com.nejc.mamiapp.helpers.DataBaseHelper;
import com.nejc.mamiapp.helpers.InterFragmentInterface;


/***********
 * REVISION HISTORY *****************
 * 10/07/2016: Added some comments
 *
 * 21/08/2016:
 * + Resconstruction and simplification of the old Activity version.
 *
 *
 ***********************************************/


public class InputActivity extends AppCompatActivity implements InterFragmentInterface {


    PendingIntent reNotify;
    Context context;
    SharedPreferences pref;
    android.app.FragmentManager manager;

    // XML Widgets
    ViewPager pager ;
    ViewPagerAdapter adapter ;
    ChooserFragment fragmentToAdd ;
    int selectedDay;
    int selectedMonth;
    int selectedYear;
    @Override
    // Initializes all the GUI components
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set activity layout
        setContentView(R.layout.activity_week);

        manager = getFragmentManager();
        // App context and preference file
        context = getApplicationContext();
        pref = context.getSharedPreferences("Constants", context.MODE_PRIVATE);
        fragmentToAdd = new ChooserFragment();
        // All GUI objects
        pager = (ViewPager) findViewById(R.id.pager);


        // ************ ViewPager initialization *******************//

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(1);

        // *********************************************************//
        //startService(intent);

    }//End of onCreate

    @Override
    // Nothing happens here so far
    protected void onResume() {
        //Log.i("Database", "You are inside on Resume");
        super.onResume();
        //AlarmManager manager = (AlarmManager) this.getSystemService(ALARM_SERVICE);

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
    // *************Interfragment communication interface**************************************//


    // This method is called from within the fragment that is populating the viewPager
    @Override
    public void onListItemClicked(int date, int month, int year) {
        this.selectedDay=date;
        this.selectedMonth=month;
        this.selectedYear=year;
        // Get the fragment manager
        // - If the fragment layout exists, show the fragment
        //   and add animation to it.
        // - Also report the selected date to the fragment
        if (!fragmentToAdd.isAdded()) {
            android.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.animator.translate_vertical_up, R.animator.translate_vertical_up);
            transaction.add(R.id.linearlayout_week_activity, fragmentToAdd);
            transaction.commit();
            fragmentToAdd.setSelectedDate(date, month, year);
            Toast.makeText(getApplicationContext(), "Item " + Integer.toString(date) + "." + Integer.toString(month) + "." + Integer.toString(year) + " has been clicked", Toast.LENGTH_SHORT).show();
        }

    }

    // This method is called from the Fragment that represents the pop-up message
    @Override
    public void onHideChooser(int itemSelected) {
        // Hide ChooserFragment
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.animator.translate_vertical_down, R.animator.translate_vertical_down);
        transaction.remove(fragmentToAdd);
        transaction.commit();
        // Update Listview with the newly selected item
        DataBaseHelper helper = new DataBaseHelper(this);
        helper.updateRow(this.selectedDay, this.selectedMonth, this.selectedYear, itemSelected);
        // Call viewpager-adapter method that notifies the view representing days in the view hierarchy
        helper.close();
        adapter.notifyFragment(pager.getCurrentItem());
    }
    //******************************************************************************************//
}// End of Activity
