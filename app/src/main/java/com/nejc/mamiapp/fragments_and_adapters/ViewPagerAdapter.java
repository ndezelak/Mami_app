package com.nejc.mamiapp.fragments_and_adapters;/**
 * @author Nejc
 * <p/>
 * Description:
 * Keep track of the selected calendar month and return the
 * appropriate WeekViewFragment instance to the attached ViewPager
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

/*********** REVISION HISTORY *****************
 *
 * 24.8.2016:
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
    @Override
    public Fragment getItem(int position) {
        WeekviewFragement fragment = new WeekviewFragement();
        int year=min_year;
        while(position > 11) {
            position=position -12;
            year++;
        }
        fragment.attach_layout(position, year);

        return fragment;

    }

    @Override
    public int getCount() {
        return 24;
    }

// This method is called when the given fragment is drawn on the screen
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentDataBase.put(position,(WeekviewFragement)fragment);
        return fragment;
    }


    public void notifyFragment(int position){
        // Get fragment at the current position
      WeekviewFragement frag = fragmentDataBase.get(position);
        // Get fragments's listview adapter and reinitialize it.
        // Do this for both listviews
        WeekDaysAdapter[] adapters=frag.getListviewAdapters();
        adapters[0].notifyDataSetChanged();
        adapters[1].notifyDataSetChanged();
    }

}
