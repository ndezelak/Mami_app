package com.nejc.mamiapp.fragments_and_adapters;

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
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
 /***********************************************/
public class CustomAdapter extends BaseAdapter{


    Context context;
    ArrayList<String> list;
    int length;
    int Position;
    boolean side;
    GregorianCalendar calendar;



    public CustomAdapter(Context context, ArrayList<String> list, boolean side, int month, int year) {
        super();

        this.context=context;
        this.list=list;
        this.side=side;
        Position=0;

        calendar=new GregorianCalendar();
        calendar.set(Calendar.MONTH, month);
        calendar.set(GregorianCalendar.YEAR, year);

        this.length=calendar.getActualMaximum(calendar.DAY_OF_MONTH);
    }




    // This method is used by the Listview to determine the number of rows it will have to display.
    @Override
    public int getCount() {

       if(side){
           return length-16;
       }
        else{
           return 16;
       }
    }



    // Not important
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
        // Get correct day of the month in the listview
        if(side){
            position=position+16;
        }
        calendar.set(Calendar.DATE, position+1);

        //Open the database, you need it to retrieve saved data for a particular day
        //SQLiteDatabase sqLiteDatabase=context.openOrCreateDatabase("Workdays", context.MODE_PRIVATE, null);


        //Get system inflater and inflate .xml row description into a View object
        LayoutInflater inflater=(LayoutInflater)      context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.listview_row, null);


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
        int day=calendar.get(Calendar.DAY_OF_WEEK);

        // Convert Gregorian interpretation into yours.
        switch (day){
            case Calendar.SUNDAY:
                day=7;
                break;
            case Calendar.MONDAY:
                day=1;
                break;
            case Calendar.TUESDAY:
                day=2;
                break;
            case Calendar.WEDNESDAY:
                day=3;
                break;
            case Calendar.THURSDAY:
                day=4;
                break;
            case Calendar.FRIDAY:
                day=5;
                break;
            case Calendar.SATURDAY:
                day=6;
                break;

        }

        // Row GUI elements
        TextView dan=(TextView)v.findViewById(R.id.Day);
        TextView type=(TextView) v.findViewById(R.id.Type);
        ImageView number = (ImageView) v.findViewById(R.id.number);
        ImageView background_type = (ImageView) v.findViewById(R.id.background_type);




        switch (position + 1){
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

        //***********Fill those views with data***************************************************//
        // Here you should do the following:
        //                                    -According to your position and month you need to determine the day of the week
        //                                      -According to your position and month you need to read the value from the database


        // Fill the current day of the week with words that are inside a List
        dan.setText(list.get(day-1)      );
        dan.setTextColor(Color.BLACK);

        Cursor cursor=null;

            if(cursor!=null) {
                if ( !cursor.isClosed() ) cursor.close();
            }

            //Find the entry in the database for the particular date.
           cursor = MyDataBaseHelper.dbHelper.getWritableDatabase().rawQuery("SELECT * FROM workdays WHERE day=" + Integer.toString(position + 1) + " AND month="+ Integer.toString(calendar.get(Calendar.MONTH)+1) + " AND year=" + Integer.toString(calendar.get(calendar.YEAR)), null);
            //Always initialize the cursor after it receives a Query from a Database
            cursor.moveToFirst();


            if (cursor.getCount() != 0) {
                //TO DO: Cursor could be set even before the Query!
               // datum.setText(Integer.toString(cursor.getInt(0)) + "." + Integer.toString(cursor.getInt(1)) + "." + Integer.toString(cursor.getInt(2)));


                // Set type that you've read from the Database
                switch (cursor.getInt(3)) {
                    case 0:
                        type.setText("Empty");
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

        type.setTextColor(Color.BLACK);
        // If the day is Sunday make it red.
        if(day==7) {
        background_type.setImageResource(R.drawable.elemnt_rdec);

        }
        else{
            background_type.setImageResource(R.drawable.element_bel);
        }


        // Close the cursor.
       if(!cursor.isClosed() ) cursor.close();
        MyDataBaseHelper.dbHelper.close();
        //Return the View to the Listview.
        return v;
    }





}






