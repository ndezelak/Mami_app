package com.nejc.mamiapp.adapters.statisticsActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.ArraySet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.nejc.mamiapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nejc
 * <p>
 * Description:
 */


/***********
 * REVISION HISTORY *****************
 * <p>
 * 02/04/2017:
 * - Defined basic structure
 * 06/06/2017:
 * - Implemented the setting container as a RecyclerView of CardView items. Added
 * all needed configurations for each item.
 * <p>
 * <p>
 * <p>
 * /
 ***********************************************/
public class SettingsListViewAdapter extends BaseAdapter {
    Context mContext;
    Activity parentActivity;
    String textToAdd;
    SharedPreferences databaseSettings;
    List<String> mData;

    // Adapter constructer
    public SettingsListViewAdapter(Context appContext, Activity parent) {
        super();
        this.mContext = appContext;
        this.parentActivity = parent;
        // Retrieve database
        databaseSettings = parentActivity.getSharedPreferences("databaseSettings", Context.MODE_PRIVATE);
        // Container for your coworkers (temporary and implemented as a list because it is easier
        // to deal with as with a set)
        mData = new ArrayList<String>();
        mData.add("Initial Person");
        // Read database
        Set<String> mSet = databaseSettings.getStringSet("Coworkers", new HashSet<String>(mData));
        databaseSettings.edit().putStringSet("Coworkers",mSet).commit();
        // Put database content into the temporary container
        mData.clear();
        mData.addAll(mSet);
    }
    // Number of settings
    @Override
    public int getCount() {
        return 2;
    }
    // Callback for retrieving View objects
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v;
        switch (position) {
            // Setting #1 (Number of vacation days in a year)
            case 0:
                // Construct the first setting
                v = inflater.inflate(R.layout.setting_1, null);
                NumberPicker picker = (NumberPicker) v.findViewById(R.id.picker);
                picker.setMinValue(20);
                picker.setMaxValue(35);
                picker.setValue(databaseSettings.getInt("VacDays", 20));
                picker.setWrapSelectorWheel(true);
                // Numberpicker value change listener
                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        Log.d("INFO", "Value has changed!");
                        // Save newly selected vacation days.
                        databaseSettings.edit().putInt("VacDays", newVal).commit();
                    }
                });
                break;
            // Setting #2 (Coworker list)
            case 1:
                // RecyclerView configuration
                v = inflater.inflate(R.layout.setting_2, null);
                RecyclerView coworkerList = (RecyclerView) v.findViewById(R.id.coworkers_item);
                LinearLayoutManager manager = new LinearLayoutManager(parent.getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                coworkerList.setLayoutManager(manager);

                // Adapter for RecyclerView configuration
                final SettingsCoworkersListAdapter adapter = new SettingsCoworkersListAdapter(mContext);
                coworkerList.setAdapter(adapter);

                // Add swiping functionallity to RecyclerView items
                ItemTouchHelper.SimpleCallback callbackAction = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    // Swipe callback
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getLayoutPosition();
                        mData.remove(position);
                        adapter.notifyDataSetChanged();
                        // Refresh the database
                        databaseSettings.edit().putStringSet("Coworkers", new HashSet<String>(mData)).commit();
                    }
                };
                ItemTouchHelper helper = new ItemTouchHelper(callbackAction);
                helper.attachToRecyclerView(coworkerList);

                // Configure EditText
                final EditText editText = (EditText) v.findViewById(R.id.coworkers_input);

                // Configure "Add" Button
                Button saveButton = (Button) v.findViewById(R.id.addButton);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().length() > 0) {
                            mData.add(editText.getText().toString());
                            adapter.notifyDataSetChanged();
                            editText.clearFocus();
                            editText.setText("");
                            // Remove soft-keyboard from screen
                            View view = parentActivity.getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            // Refresh the database
                            databaseSettings.edit().putStringSet("Coworkers", new HashSet<String>(mData)).commit();
                        }
                    }
                });
                break;
            default:
                v = inflater.inflate(R.layout.setting_2, null);
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


}
