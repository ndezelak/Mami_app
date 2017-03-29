package com.nejc.mamiapp.fragments_and_adapters;/**
 * @author Nejc
 * <p/>
 * Description:
 * Creates a Weekview Fragment when a new page wants to be created by the System.
 * It saves all fragments inside a Hashmap.
 * It can notify all listViewAdapters inside the Fragment
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;

/*********** REVISION HISTORY *****************
 *
 * 24/08/2016:
 *      - Logic for determining the right calendar month
 *      - Creating the WeekviewFragment with appropriate data
 *
 *
 *
 /***********************************************/
public class ViewPagerAdapter extends FragmentStatePagerAdapter  {
    private final int min_year = 2016;
    HashMap<Integer,WeekviewFragement> fragmentDataBase;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentDataBase=new HashMap<>();
    }
    int currentPosition;

    // This method is called by the system whenever it wants to create a new page
    @Override
    public Fragment getItem(int position) {
        WeekviewFragement fragment = new WeekviewFragement();
        int year=min_year;

        // Simple method for handling new calendar years
        while(position > 11) {
            position=position -12;
            year++;
        }
        // Call fragments method that attaches a layout
        fragment.setMonthYear(position, year);

        return fragment;

    }

    @Override
    public int getCount() {
        return 24;
    }

    // This method is called when the given fragment is drawn on the screen
    // What you do is only save the created Fragment under fragmentDataBase
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentDataBase.put(position,(WeekviewFragement)fragment);
        return fragment;
    }

    // Public method that is used by other objects
    // It reads the wanted Fragment from the Database, gets its ListviewAdapters
    // and notifys both adapters data they have to be re-rendered.
    public void notifyFragment(int position){
        // Get fragment at the current position
      WeekviewFragement frag = fragmentDataBase.get(position);
        // Get fragments's listview adapter and reinitialize it.
        // Do this for both listviews (left and right)
        WeekDaysAdapter[] adapters=frag.getListviewAdapters();
        adapters[0].notifyDataSetChanged();
        adapters[1].notifyDataSetChanged();
    }

}
