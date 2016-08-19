package com.nejc.mamiapp;

// First fragment class representing a layout that is swipable

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class WeekviewFragement extends android.support.v4.app.Fragment {
    int resource_id;

    public WeekviewFragement() {
        super();
    }

    // Attach layout to the week overview Fragment instance
    public void attach_layout(int resource_id){
        this.resource_id=resource_id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listviewLayout = inflater.inflate(R.layout.month_input_fragment, container, false);

        ListView listview_left= (ListView) listviewLayout.findViewById(R.id.listView);
        ListView listview_right = (ListView) listviewLayout.findViewById(R.id.listView_right);

        ArrayList<String> arrayList = new ArrayList<String>();
        // TODO: Use string.xml
        arrayList.add("P");
        arrayList.add("T");
        arrayList.add("S");
        arrayList.add("Č");
        arrayList.add("P");
        arrayList.add("S");
        arrayList.add("N");

        CustomAdapter adapter_listview_left = new CustomAdapter(getActivity().getApplicationContext(), arrayList, false);
        CustomAdapter adapter_listview_right = new CustomAdapter(getActivity().getApplicationContext(), arrayList, true);
        listview_left.setAdapter(adapter_listview_left);
        listview_right.setAdapter(adapter_listview_right);

        return listviewLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
