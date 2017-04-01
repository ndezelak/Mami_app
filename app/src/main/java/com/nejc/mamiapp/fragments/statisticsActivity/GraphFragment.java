package com.nejc.mamiapp.fragments.statisticsActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nejc.mamiapp.R;
/**
 * @author Nejc
 * <p>
 * Description:
 *      Modular UI element representing a graph
 */

/*********** REVISION HISTORY *****************
 * 31/03/2017
 *          + Defined basic structure
 *
 *
 *
 *
 /***********************************************/

public class GraphFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.statistics_activity_month_fragment,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
