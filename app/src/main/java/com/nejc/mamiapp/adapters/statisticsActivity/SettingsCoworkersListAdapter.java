package com.nejc.mamiapp.adapters.statisticsActivity;
/**
 * @author Nejc
 * <p>
 * Description:
 */
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.nejc.mamiapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.support.v7.cardview.R.style.CardView;

/*********** REVISION HISTORY *****************
 *05/06/2017:
 *          - First basic implementation
 *07/06/2017:
 *          - Handling of the RecyclerView (coworker list)
 *          - Handling of all settings data
 *
 *
 *
 *
 /***********************************************/
// This adapter creates ViewHolder objects of your flavour.
public class SettingsCoworkersListAdapter extends RecyclerView.Adapter<SettingsCoworkersListAdapter.ViewHolder> {
    public List<String> mDataset;
    Context mContext;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    // Basically your personal structure of a ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public CardView card;
        public ViewHolder(View v) {
            super(v);
            text =(TextView) v.findViewById(R.id.card_text);
            card = (android.support.v7.widget.CardView) v.findViewById(R.id.card_view);
        }
        public void setCardText(String text){
            this.text.setText(text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SettingsCoworkersListAdapter( Context mContext) {
        this.mContext = mContext;
        this.mDataset = new ArrayList<String>();
    }

    // Adapters method for returning a new ViewHolder - not populated with any data!
    @Override
    public SettingsCoworkersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // Add CardView to the ViewHolder
        ViewHolder vh = new ViewHolder(LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.coworkers_card,parent,false));
        vh.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return vh;
    }
    // Add content to a ViewHolder object
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Read database and refresh each item of the recyclerview
        mDataset.clear();
        mDataset.addAll(mContext.getSharedPreferences("databaseSettings",Context.MODE_PRIVATE).getStringSet("Coworkers",null));
        holder.setCardText(mDataset.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        mDataset.clear();
        mDataset.addAll(mContext.getSharedPreferences("databaseSettings",Context.MODE_PRIVATE).getStringSet("Coworkers",null));
        return mDataset.size();
    }
}