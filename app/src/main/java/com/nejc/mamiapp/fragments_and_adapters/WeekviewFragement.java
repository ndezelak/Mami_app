package com.nejc.mamiapp.fragments_and_adapters;

// First fragment class representing a layout that is swipeable

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.fragments_and_adapters.CustomAdapter;
import com.nejc.mamiapp.helpers.InterFragmentInterface;

import java.util.ArrayList;
import java.util.Calendar;


public class WeekviewFragement extends android.support.v4.app.Fragment {
    int month;
    int year;
    InterFragmentInterface commInterface;

    public WeekviewFragement() {
        super();
    }

    // Attach layout to the week overview Fragment instance
    public void attach_layout(int month, int year) {
        this.month = month;
        this.year = year;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Create the fragment layout out of default values
        View listviewLayout = inflater.inflate(R.layout.month_input_fragment, container, false);

        // GUI elements of the layout
        ListView listview_left = (ListView) listviewLayout.findViewById(R.id.listView);
        ListView listview_right = (ListView) listviewLayout.findViewById(R.id.listView_right);
        ImageView month_title = (ImageView) listviewLayout.findViewById(R.id.month_title);
        ImageView year_title = (ImageView) listviewLayout.findViewById(R.id.year_title);

        ArrayList<String> arrayList = new ArrayList<String>();
        // TODO: Use string.xml
        arrayList.add("P");
        arrayList.add("T");
        arrayList.add("S");
        arrayList.add("Č");
        arrayList.add("P");
        arrayList.add("S");
        arrayList.add("N");

        // Attach adapters to the listviews
        CustomAdapter adapter_listview_left = new CustomAdapter(getActivity().getApplicationContext(), arrayList, false, month, year);
        CustomAdapter adapter_listview_right = new CustomAdapter(getActivity().getApplicationContext(), arrayList, true, month, year);
        listview_left.setAdapter(adapter_listview_left);
        listview_right.setAdapter(adapter_listview_right);

        // Set month image
        switch (month) {
            case Calendar.JANUARY:
                month_title.setImageResource(R.drawable.januar);
                break;
            case Calendar.FEBRUARY:
                month_title.setImageResource(R.drawable.februar);
                break;
            case Calendar.MARCH:
                month_title.setImageResource(R.drawable.marec);
                break;
            case Calendar.APRIL:
                month_title.setImageResource(R.drawable.april);
                break;
            case Calendar.MAY:
                month_title.setImageResource(R.drawable.maj);
                break;
            case Calendar.JUNE:
                month_title.setImageResource(R.drawable.junij);
                break;
            case Calendar.JULY:
                month_title.setImageResource(R.drawable.julij);
                break;
            case Calendar.AUGUST:
                month_title.setImageResource(R.drawable.avgust);
                break;
            case Calendar.SEPTEMBER:
                month_title.setImageResource(R.drawable.september);
                break;
            case Calendar.OCTOBER:
                month_title.setImageResource(R.drawable.oktober);
                break;
            case Calendar.NOVEMBER:
                month_title.setImageResource(R.drawable.november);
                break;
            case Calendar.DECEMBER:
                month_title.setImageResource(R.drawable.december);
                break;
        }

        // Set year image
        switch(year){
            case 2016:
                year_title.setImageResource(R.drawable.y2016);
                break;
            case 2017:
                year_title.setImageResource(R.drawable.y2017);
                break;
            case 2018:
                year_title.setImageResource(R.drawable.y2018);
                break;
            case 2019:
                year_title.setImageResource(R.drawable.y2019);
                break;
            case 2020:
                year_title.setImageResource(R.drawable.y2020);
                break;

        }

        // Use a single GridView instead of two ListViews
        // ListView adapter should be usable for the GrdiView too.
        listview_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                commInterface.onHideChooser(2);
            }
        });

        listview_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                commInterface.onListItemClicked(1,1,2016);
            }
        });

        return listviewLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commInterface = (InterFragmentInterface) getActivity();
    }
}
