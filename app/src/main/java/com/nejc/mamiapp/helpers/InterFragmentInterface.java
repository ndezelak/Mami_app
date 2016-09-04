package com.nejc.mamiapp.helpers;

/**
 * @author Nejc
 *         <p/>
 *         Description:
 *         Interface to handle interfragment communication in your app
 */

public  interface InterFragmentInterface {

    public void onListItemClicked(int date, int month, int year);

    public void onHideChooser(int itemSelected);

}
