package com.nejc.mamiapp.adapters.inputActivity;/**
 * @author Nejc
 * <p/>
 * Description:
 *          Adapter that displays images representing options in a 3x2 grid
 */


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/***********
 * REVISION HISTORY *****************
 * 04/09/2016:
 *          + Created the class with basic functionality
 * /
 ***********************************************/
public class GridViewChooserAdapter extends BaseAdapter {
   // References to your images
    int[] listImages;

    // Class constructor that saves reference to the images that have to be displayed
    public GridViewChooserAdapter (int[] listImages){
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

    // Return element as an ImageView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView option=new ImageView(parent.getContext());
        option.setImageResource(listImages[position]);
        option.setAdjustViewBounds(true);
        option.setId(listImages[position]);
        return option;
    }
}
