package com.nejc.mamiapp.fragments_and_adapters;/**
 * @author Nejc
 * <p/>
 * Description:
 *          Adapter that displays images representing options in a 3x2 grid
 */


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/***********
 * REVISION HISTORY *****************
 * 04/09/2016:
 *          + Created the class with basic functionality
 * /
 ***********************************************/
public class GridViewChooserAdapter extends BaseAdapter {
   // References to your images
    int[] listImages;

    GridViewChooserAdapter (int[] listImages){
        this.listImages=listImages;
    }

    @Override
    public int getCount() {
        // There are only six images to be displayed
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView option=new ImageView(parent.getContext());
        option.setImageResource(listImages[position]);
        option.setAdjustViewBounds(true);
        option.setId(listImages[position]);
        return option;
    }
}
