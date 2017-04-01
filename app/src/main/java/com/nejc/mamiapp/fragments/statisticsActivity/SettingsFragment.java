package com.nejc.mamiapp.fragments.statisticsActivity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nejc.mamiapp.R;

/**
 * @author Nejc
 * <p>
 * Description:
 *          Special implementation of the Fragment class specialized for
 *          displaying and handling preferences.
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
    public SettingsFragment() {
        super();
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
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
