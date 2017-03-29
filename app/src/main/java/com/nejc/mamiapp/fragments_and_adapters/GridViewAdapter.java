package com.nejc.mamiapp.fragments_and_adapters;/**
 * @author Nejc
 * <p/>
 * Description:
 * Adapter for your fragment_chooser gridview which has custom frames containing imageviews.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nejc.mamiapp.R;

import java.util.ArrayList;
import java.util.List;

/***********
 * REVISION HISTORY *****************
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * /
 ***********************************************/
public class GridViewAdapter extends BaseAdapter {
    private final List<Integer> mItems = new ArrayList<Integer>();
    private final LayoutInflater mInflater;

    // When creating an adapüter object
    // you should save references to all ImageViews that will be used in the GridView
    public GridViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(R.drawable.dop_sank_big);
        mItems.add(R.drawable.dop_k_big);
        mItems.add(R.drawable.pop_sank_big);
        mItems.add(R.drawable.pop_k_big);
        mItems.add(R.drawable.dopust_big);
        mItems.add(R.drawable.prosto_big);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
        }
        picture = (ImageView) v.findViewById(R.id.picture);
        picture.setImageResource(mItems.get(i));

        return v;
    }

}
