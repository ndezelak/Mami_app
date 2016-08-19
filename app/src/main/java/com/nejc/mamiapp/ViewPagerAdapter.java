package com.nejc.mamiapp;/**
 * @author Nejc
 * <p/>
 * Description:
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/*********** REVISION HISTORY *****************
 *
 *
 *
 *
 *
 /***********************************************/
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("ADAPTER", Integer.toString(position));
        WeekviewFragement fragment = new WeekviewFragement();
        fragment.attach_layout(R.layout.month_input_fragment);
        return fragment;

    }

    @Override
    public int getCount() {
        return 10;
    }

}
