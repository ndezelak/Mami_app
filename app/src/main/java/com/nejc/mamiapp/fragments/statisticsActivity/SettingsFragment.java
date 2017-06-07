package com.nejc.mamiapp.fragments.statisticsActivity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.adapters.statisticsActivity.GraphViewPagerAdapter;
import com.nejc.mamiapp.adapters.statisticsActivity.SettingsListViewAdapter;

/**
 * @author Nejc
 * <p>
 * Description:
 *          Settings UI as a fragment.
 */


/*********** REVISION HISTORY *****************
 *
 *  01/04/2017:
 *          - Defined basic structure
 *
 *
 *
 /***********************************************/

public class SettingsFragment extends Fragment {
    private Activity parent;
    public SettingsFragment() {
        super();
    }
    public void setParent(Activity parent){
        this.parent = parent;
    }
    public Activity getParent(){
        return this.parent;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View v = (View) inflater.inflate(R.layout.statistics_activity_settings_fragment,container, false);
        ListView settingsListView = (ListView)v.findViewById(R.id.settings_listview);
        //GraphViewPagerAdapter adapter = new GraphViewPagerAdapter(getFragmentManager());
        settingsListView.setAdapter(new SettingsListViewAdapter(getContext(),getParent()));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
