package com.nejc.mamiapp.helpers;

/**
 * @author Nejc
 *
 * Description:
 * Methods for making SQL database usage easier.
 * Currently there are two methods in this:
 * -initializeDatabase (give the database a date and it will fill up the wanted content)
 * -SetBackDatabase (sets every entry to empty)
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nejc.mamiapp.helpers.DataBaseHelper;

/*********** REVISION HISTORY *****************
 *
 *
 *
 *
 *
 /***********************************************/

public class MyDataBaseHelper {
    public static DataBaseHelper dbHelper;
    public static void initializeDatabase(int day, int month, int year, SQLiteDatabase database, String databasename){

        Cursor cursor=database.rawQuery( "SELECT * FROM "+databasename+" WHERE day=" +
                Integer.toString(day)+" AND month="+Integer.toString(month)+" AND year="+Integer.toString(year),null);

        // If particular day not yet inside the database. Initialize it.
        if(cursor.getCount()==0){

            database.execSQL("INSERT INTO "+databasename+"(day,month,year,type) VALUES ("+Integer.toString(day)+","+
            Integer.toString(month)+","+Integer.toString(year)+",0)");
        }

        if(!cursor.isClosed() ) cursor.close();

    }
    public static void setBackDatabase(int month, int year, SQLiteDatabase database, String databasename){



            database.execSQL("UPDATE "+databasename+" SET type=0 WHERE month="+
                    Integer.toString(month)+" AND year="+Integer.toString(year));


    }

}
