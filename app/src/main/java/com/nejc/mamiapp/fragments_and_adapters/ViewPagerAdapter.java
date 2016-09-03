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

/*********** REVISION HISTORY *****************
 *
 * 24.8.2016:
 *      - Logic for determining the right calendar month
 *      - Creating the WeekviewFragment with appropriate data
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
