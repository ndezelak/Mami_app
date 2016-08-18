package com.nejc.mamiapp;

/**
 * @author Nejc
 *
 * Description:
 * Implementation of BaseAdapter class which manages Listviews and Spinners
 * The adapter is able to use the listview row layout and populate it with
 * appropriate days of the week and data from the database for every day of the month
 */



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*********** REVISION HISTORY *****************
 *      10/07/2016: Added the class description.
 *
 *
 *
 *
 /***********************************************/
public class CustomAdapter extends BaseAdapter{


    Context context;
    ArrayList<String> list;
    int length;
    int Position;





    public CustomAdapter(Context context,ArrayList<String> list) {
        super();
        this.context=context;
        this.list=list;
        SharedPreferences preferences=context.getSharedPreferences("Constants", context.MODE_PRIVATE);
        length=preferences.getInt("length",0);
        Position=0;

    }




    // This method is used by the Listview to determine the number of rows it will have to display.
    @Override
    public int getCount() {
        SharedPreferences preferences=context.getSharedPreferences("Constants", context.MODE_PRIVATE);
        length=preferences.getInt("length",0);
        return length;
    }




    @Override
    public Object getItem(int position) {
        return null;
    }






    @Override
    public long getItemId(int position) {
        return position;
    }





// The most important Callback. Here the Adapter returns a View that has to be displayed on the current row.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Open the database
        SQLiteDatabase sqLiteDatabase=context.openOrCreateDatabase("Workdays", context.MODE_PRIVATE, null);


        //Get system inflater and inflate .xml row description into a View object
        LayoutInflater inflater=(LayoutInflater)      context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.listview_row, null);

        //Read current month and month length fromt he preference file
        SharedPreferences preferences=context.getSharedPreferences("Constants", context.MODE_PRIVATE);
        int month=preferences.getInt("month", 0);
        length=preferences.getInt("length",0);
        // TO DO, add another entry into the Preference file for the year
        int year=2016;


        // Use the GregorianCalendar class to get the day of the week for the specified date
        // Months start with 0, position starts with 0
        GregorianCalendar calendar=new GregorianCalendar(year,month-1,position+1);
        // Get the day of the week as an Integer
        int day=calendar.get(Calendar.DAY_OF_WEEK);

        // Convert Gregorian interpretation into yours.
        switch (day){
            case 1:
                day=7;
                break;
            case 2:
                day=1;
                break;
            case 3:
                day=2;
                break;
            case 4:
                day=3;
                break;
            case 5:
                day=4;
                break;
            case 6:
                day=5;
                break;
            case 7:
                day=6;
                break;

        }



        //Retrieve Views in a Listview row (note you are using View v that you inflated from an .XML that
        // contains more primitive Views.
        TextView dan=(TextView)v.findViewById(R.id.Day);
        TextView datum=(TextView) v.findViewById(R.id.Date);
        TextView type=(TextView) v.findViewById(R.id.Type);


        //***********Fill those views with data***************************************************//
        // Here you should do the following:
        //                                    -According to your position and month you need to determine the day of the week
        //                                      -According to your position and month you need to read the value from the database


        // Fill the current day of the week with words that are inside a List
        dan.setText(list.get(day-1)      );


        Cursor cursor=null;

            if(cursor!=null) {
                if ( !cursor.isClosed() ) cursor.close();
            }

            //Find the entry in the database for the particular date.
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM workdays WHERE day=" + Integer.toString(position+1) + " AND month=" + Integer.toString(month) + " AND year=" + Integer.toString(year), null);
            //Always initialize the cursor after it receives a Query from a Database
            cursor.moveToFirst();


            if (cursor.getCount() != 0) {
                //TO DO: Cursor could be set even before the Query!
                datum.setText(Integer.toString(cursor.getInt(0)) + "." + Integer.toString(cursor.getInt(1)) + "." + Integer.toString(cursor.getInt(2)));


                // Set type that you've read from the Database
                switch (cursor.getInt(3)) {
                    case 0:
                        type.setText("Prazno");
                        break;

                    case 1:
                        type.setText("Šank dopoldan");
                        break;
                    case 2:
                        type.setText("Šank popoldan");
                        break;
                    case 3:
                        type.setText("Kuhinja dopoldan");
                        break;
                    case 4:
                        type.setText("Kuhinja popoldan");
                        break;
                    case 5:
                        type.setText("Prosto");
                        break;
                    case 6:
                        type.setText("Dopust");
                        break;

                }

            }
        // If the day is Sunday make it red.
        if(day==7) dan.setTextColor(Color.RED);


        // Close the cursor.
        if(!cursor.isClosed() ) cursor.close();

        //Return the View to the Listview.
        return v;
    }





}






