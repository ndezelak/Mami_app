package com.nejc.mamiapp.fragments_and_adapters;/**
 * @author Nejc
 * <p/>
 * Description:
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/*********** REVISION HISTORY *****************
 *
 *
 *
 *
 *
 /***********************************************/
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final int min_year = 2016;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

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

}
