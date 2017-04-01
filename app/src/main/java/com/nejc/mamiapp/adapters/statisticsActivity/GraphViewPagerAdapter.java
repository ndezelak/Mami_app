package com.nejc.mamiapp.adapters.statisticsActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.BaseAdapter;

import com.nejc.mamiapp.fragments.statisticsActivity.GraphFragment;

/**
 * @author Nejc
 * <p>
 * Description:
 *              ViewPager adapter constructing instances of Graphfragments for the ViewPager
 */


/*********** REVISION HISTORY *****************
 * 31/03/2017:
 *          -Defined basic structure
 *
 *
 *
 *
 /***********************************************/

public class GraphViewPagerAdapter extends FragmentStatePagerAdapter {
    private int FRAGMENT_COUNT = 12;
    public GraphViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // This method is called whenever a new Fragment has to be created
    @Override
    public Fragment getItem(int position) {
        return new GraphFragment();
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }


}
