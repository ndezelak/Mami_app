package com.nejc.mamiapp.adapters.statisticsActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nejc.mamiapp.R;

/**
 * @author Nejc
 * <p>
 * Description:
 */


/*********** REVISION HISTORY *****************
 *
 *02/04/2017:
 *          - Defined basic structure
 *
 *
 *
 /***********************************************/

public class SettingsListViewAdapter extends BaseAdapter {
    Context mContext;
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v;
        switch(position){

            case 0:
                v = inflater.inflate(R.layout.statistics_activity_settings_fragment_listview_row_1 ,null);
                break;
            case 1:
                v = inflater.inflate(R.layout.statistics_activity_settings_fragment_listview_row_2 ,null);
                break;
            default:
                v = inflater.inflate(R.layout.statistics_activity_settings_fragment_listview_row_2 ,null);

        }

        return v;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public SettingsListViewAdapter(Context appContext) {
        super();
        this.mContext = appContext;
    }
}
