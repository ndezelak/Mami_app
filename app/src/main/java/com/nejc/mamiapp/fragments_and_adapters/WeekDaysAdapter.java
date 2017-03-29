package com.nejc.mamiapp.fragments_and_adapters;

/**
 * @author Nejc
 * <p/>
 * Description:
 * Figures out the day of the week
 * Populates the GUI elements of a single row accordingly to the given date and database data
 */


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nejc.mamiapp.helpers.InterFragmentInterface;
import com.nejc.mamiapp.helpers.MyDataBaseHelper;
import com.nejc.mamiapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*********** REVISION HISTORY *****************
 *      10/07/2016: Added the class description.
 *
 *
 *      18/08/2016:
 *                  +Changed activity layout
 *                  +Changed listview item layout
 *                  + Added functionality to automatically configure the listview according to the specified day
 *
 *       03/09/2016:
 *                  + Current data can be reported to the main activity via the custom interfragment interface
 *       04/09/2016:
 *                  + Bugfix: Nonexistend database item is displayed as empty
 /***********************************************/
public class WeekDaysAdapter extends BaseAdapter {


    Context context;

    private int length;
    private int Position;
    private int month;
    private int year;
    private boolean side;
    GregorianCalendar calendar;
    private int numItems;
    InterFragmentInterface commInterface;


    public WeekDaysAdapter(Context context, boolean side, int month, int year, InterFragmentInterface commInterface) {
        super();
        this.commInterface = commInterface;
        this.context = context;
        this.side = side;
        Position = 0;
        this.month = month;
        this.year = year;
        // Set the calendar and length of the month
        calendar = new GregorianCalendar();
        calendar.set(Calendar.MONTH, month);
        calendar.set(GregorianCalendar.YEAR, year);
        this.length = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
    }


    // This method is used by the Listview to determine the number of rows it will have to display.
    @Override
    public int getCount() {
        // Small hack for using two listviews for the calendar. Should be replaced in the future
        // with a gridView
        if (side) {
            numItems = length - 16;
            return numItems;
        } else {
            numItems = 16;
            return numItems;
        }
    }


    // Not important but has to be implemented
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
        // Get correct day of the month in the listview. Hack for using two listViews
        if (side) {
            position = position + 16;
        }
        final int mposition = position;
        // Set the calendar day
        calendar.set(Calendar.DATE, position + 1);

        //Open the database, you need it to retrieve saved data for a particular day
        //SQLiteDatabase sqLiteDatabase=context.openOrCreateDatabase("Workdays", context.MODE_PRIVATE, null);


        //Generate a layout for the listview row
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.listview_row, null);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commInterface.onListItemClicked(mposition + 1, month + 1, year);
            }
        });

        // Row GUI elements
        ImageView work_type = (ImageView) v.findViewById(R.id.work_type);
        ImageView day_of_week = (ImageView) v.findViewById(R.id.day_of_week);
        ImageView number = (ImageView) v.findViewById(R.id.number);
        ImageView background_type = (ImageView) v.findViewById(R.id.background_type);


        //v.getLayoutParams().height=parent.getLayoutParams().height/numItems;


        //Read current month and month length fromt he preference file
        //SharedPreferences preferences=context.getSharedPreferences("Constants", context.MODE_PRIVATE);
        //int month=preferences.getInt("month", 0);
        //length=preferences.getInt("length",0);
        // TO DO, add another entry into the Preference file for the year
        //int year=2016;


        // Use the GregorianCalendar class to get the day of the week for the specified date
        // Months start with 0, position starts with 0
        //GregorianCalendar calendar=new GregorianCalendar(year,month-1,position+1);


        // Get the day of the week as an Integer
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        // Convert Gregorian interpretation into yours.
        switch (day) {
            case Calendar.SUNDAY:
                day = 7;
                break;
            case Calendar.MONDAY:
                day = 1;
                break;
            case Calendar.TUESDAY:
                day = 2;
                break;
            case Calendar.WEDNESDAY:
                day = 3;
                break;
            case Calendar.THURSDAY:
                day = 4;
                break;
            case Calendar.FRIDAY:
                day = 5;
                break;
            case Calendar.SATURDAY:
                day = 6;
                break;

        }

        // Set the number image
        switch (position + 1) {
            case 1:
                number.setImageResource(R.drawable.one);
                break;
            case 2:
                number.setImageResource(R.drawable.two);
                break;
            case 3:
                number.setImageResource(R.drawable.three);
                break;
            case 4:
                number.setImageResource(R.drawable.four);
                break;
            case 5:
                number.setImageResource(R.drawable.five);
                break;
            case 6:
                number.setImageResource(R.drawable.six);
                break;
            case 7:
                number.setImageResource(R.drawable.seven);
                break;
            case 8:
                number.setImageResource(R.drawable.eight);
                break;
            case 9:
                number.setImageResource(R.drawable.nine);
                break;
            case 10:
                number.setImageResource(R.drawable.ten);
                break;
            case 11:
                number.setImageResource(R.drawable.eleven);
                break;
            case 12:
                number.setImageResource(R.drawable.twelve);
                break;
            case 13:
                number.setImageResource(R.drawable.thirteen);
                break;
            case 14:
                number.setImageResource(R.drawable.fourteen);
                break;
            case 15:
                number.setImageResource(R.drawable.fifteen);
                break;
            case 16:
                number.setImageResource(R.drawable.sixteen);
                break;
            case 17:
                number.setImageResource(R.drawable.seventeen);
                break;
            case 18:
                number.setImageResource(R.drawable.eightteen);
                break;
            case 19:
                number.setImageResource(R.drawable.nineteen);
                break;
            case 20:
                number.setImageResource(R.drawable.twenty);
                break;
            case 21:
                number.setImageResource(R.drawable.twentyone);
                break;
            case 22:
                number.setImageResource(R.drawable.twentytwo);
                break;
            case 23:
                number.setImageResource(R.drawable.twentythree);
                break;
            case 24:
                number.setImageResource(R.drawable.twentyfour);
                break;
            case 25:
                number.setImageResource(R.drawable.twentyfive);
                break;
            case 26:
                number.setImageResource(R.drawable.twentysix);
                break;
            case 27:
                number.setImageResource(R.drawable.twentyseven);
                break;
            case 28:
                number.setImageResource(R.drawable.twentyeight);
                break;
            case 29:
                number.setImageResource(R.drawable.twentynine);
                break;
            case 30:
                number.setImageResource(R.drawable.thirty);
                break;
            case 31:
                number.setImageResource(R.drawable.thirtyone);
                break;
            default:
                number.setImageResource(R.drawable.eight);
                break;
        }


        // Fill the current day of the week with an image
        switch (day) {
            case 1:
                day_of_week.setImageResource(R.drawable.p);
                break;
            case 2:
                day_of_week.setImageResource(R.drawable.t);
                break;
            case 3:
                day_of_week.setImageResource(R.drawable.s);
                break;
            case 4:
                day_of_week.setImageResource(R.drawable.cetrtek);
                break;
            case 5:
                day_of_week.setImageResource(R.drawable.p);
                break;
            case 6:
                day_of_week.setImageResource(R.drawable.s);
                break;
            case 7:
                day_of_week.setImageResource(R.drawable.n);
                break;
        }


        // If the day is Sunday make the row background red

        if (day == 7) {
            background_type.setImageResource(R.drawable.element_rdec_new);

        } else {
            background_type.setImageResource(R.drawable.kvadrat_bel_nov);
        }


        Cursor cursor = null;

        if (cursor != null) {
            if (!cursor.isClosed()) cursor.close();
        }

        //Find the entry in the database for the particular date.
        cursor = MyDataBaseHelper.dbHelper.getWritableDatabase().rawQuery("SELECT * FROM workdays WHERE day=" + Integer.toString(position + 1) + " AND month=" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + " AND year=" + Integer.toString(calendar.get(calendar.YEAR)), null);
        //Always initialize the cursor after it receives a Query from a Database
        cursor.moveToFirst();


        if (cursor.getCount() != 0) {
            //TO DO: Cursor could be set even before the Query!
            // datum.setText(Integer.toString(cursor.getInt(0)) + "." + Integer.toString(cursor.getInt(1)) + "." + Integer.toString(cursor.getInt(2)));


            // Set type that you've read from the Database
            switch (cursor.getInt(3)) {
                case 0:
                    work_type.setImageDrawable(null);
                    break;

                case 1:

                    work_type.setImageResource(R.drawable.dop_sanka_small);

                    break;
                case 2:
                    work_type.setImageResource(R.drawable.pop_sank_small);

                    break;
                case 3:
                    work_type.setImageResource(R.drawable.dop_k_small);

                    break;
                case 4:
                    work_type.setImageResource(R.drawable.pop_k_small);

                    break;
                case 5:
                    work_type.setImageResource(R.drawable.prosto_small);

                    break;
                case 6:
                    work_type.setImageResource(R.drawable.dopust_small);

                    break;

            }

        }

        // This day hasnt been added to the database or an error occurred. Display an empty image
        else {
            work_type.setImageDrawable(null);
        }


        // Close the cursor.
        if (!cursor.isClosed()) cursor.close();
        MyDataBaseHelper.dbHelper.close();
        //Return the View to the Listview.
        return v;
    }


}






