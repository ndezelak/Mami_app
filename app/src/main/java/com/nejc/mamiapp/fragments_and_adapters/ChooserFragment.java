package com.nejc.mamiapp.fragments_and_adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.helpers.DataBaseHelper;
import com.nejc.mamiapp.helpers.InterFragmentInterface;

import java.util.ArrayList;

/**
 * Fragment used for displaying (and hiding) the item chooser dialog inside the InputActivity
 */
public class ChooserFragment extends Fragment implements View.OnClickListener{
    Context mContext;
    InterFragmentInterface commInterface;

    // GUI elements
    private ImageView dop_kuh;
    private ImageView pop_kuh;
    private ImageView dop_sank;
    private ImageView pop_sank;
    private ImageView prosto;
    private ImageView dopust;
    private int day;
    private int month;
    private int year;


    // Fragment constructor
    public ChooserFragment() {
        super();
    }

    // Return View representing the fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Here you should initialize the fragment layout and return it
        View layout = inflater.inflate(R.layout.fragment_chooser, container, false);
        dop_kuh = (ImageView) layout.findViewById(R.id.dop_kuh_big);
        pop_kuh = (ImageView) layout.findViewById(R.id.pop_kuh_big);
        dop_sank = (ImageView) layout.findViewById(R.id.dop_sank_big);
        pop_sank = (ImageView) layout.findViewById(R.id.pop_sank_big);
        prosto = (ImageView) layout.findViewById(R.id.prosto_big);
        dopust = (ImageView) layout.findViewById(R.id.dopust_big);

        return layout;

    }

    // Here you get reference to the activity that created the fragment
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commInterface = (InterFragmentInterface) getActivity();
    }

    public void setSelectedDate(int day, int month, int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }


    @Override
    public void onClick(View v) {
        DataBaseHelper dbHelper = new DataBaseHelper(getActivity().getApplicationContext());
        dbHelper.openDataBase();
        switch(v.getId()){
            case R.id.dop_kuh_big:
                dbHelper.updateRow(day,month,year,3);
                break;
            case R.id.pop_kuh_big:
                dbHelper.updateRow(day,month,year,4);
                break;
            case R.id.dop_sank_big:
                dbHelper.updateRow(day,month,year,1);
                break;
            case R.id.pop_sank_big:
                dbHelper.updateRow(day,month,year,2);
                break;
            case R.id.prosto_big:
                dbHelper.updateRow(day,month,year,5);
                break;
            case R.id.dopust_big:
                dbHelper.updateRow(day,month,year,6);
                break;
        }
    }
}
