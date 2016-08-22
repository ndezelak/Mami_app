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
import com.nejc.mamiapp.helpers.InterFragmentInterface;

import java.util.ArrayList;

/**
 * Fragment used for displaying (and hiding) the item chooser dialog inside the InputActivity
 */
public class ChooserFragment extends Fragment {
    Context mContext;
    InterFragmentInterface commInterface;
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


        return layout;

    }

    // Here you get reference to the activity that created the fragment
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commInterface = (InterFragmentInterface) getActivity();
    }
}
