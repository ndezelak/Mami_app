package com.nejc.mamiapp.fragments.inputActivity;
/* @author Nejc
        * <p/>
        * Description:
        *          Fragment displaying input options to the user
        *          This fragment needs to have a reference to the activity(basically interface)
        *          in which it is created in order to call the appropriate interface method
        */

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.nejc.mamiapp.R;
import com.nejc.mamiapp.adapters.inputActivity.GridViewChooserAdapter;
import com.nejc.mamiapp.helpers.InterFragmentInterface;

/**
 * Fragment used for displaying (and hiding) the item chooser dialog inside the InputActivity
 */
public class ChooserFragment extends Fragment {
    Context mContext;
    InterFragmentInterface commInterface;

    // GUI elements
    private ImageView dop_kuh;
    private ImageView pop_kuh;
    private ImageView dop_sank;
    private ImageView pop_sank;
    private ImageView prosto;
    private ImageView dopust;

    // Private variables
    private int day;
    private int month;
    private int year;
    private int dummy;

    // Fragment constructor
    public ChooserFragment() {
        super();
    }

    // Return View representing the fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
       // Inflate the XML representing the fragment
        View layout = inflater.inflate(R.layout.input_activity_fragment_chooser, container, false);


        // Array of references to Images representing options for the user
        int[] refArray={R.drawable.dop_sank_big,R.drawable.dop_k_big, R.drawable.pop_sank_big,
                        R.drawable.pop_k_big, R.drawable.dopust_big,  R.drawable.prosto_big  };

        // Initialization of the Gridview located inside the ChooserFragment
        GridView grid = (GridView) layout.findViewById(R.id.chooser_grid);
        GridViewChooserAdapter gridAdapter = new GridViewChooserAdapter(refArray);
        grid.setAdapter(gridAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               int item;
                switch (position) {
                    case 0:
                        item=1;
                        break;
                    case 1:
                        item=3;
                        break;
                    case 2:
                        item=2;
                        break;
                    case 3:
                        item=4;
                        break;
                    case 4:
                        item=6;
                        break;
                    case 5:
                        item=5;
                        break;
                    default:
                        item=0;
                }
                // Notify which item has been chosen by the user
                commInterface.onHideChooser(item);
            }
        });
        return layout;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Here you get reference to the activity that created the fragment
        commInterface = (InterFragmentInterface) getActivity();
    }

    public void setSelectedDate(int day, int month, int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

}
