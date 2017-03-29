package com.nejc.mamiapp.helpers;/***********
 * @author Nejc
 * <p/>
 * Description:
 * Helper Class used for restoring the Workdays database used once by the app
 * <p/>
 * <p>
 * /*********** REVISION HISTORY *****************
 * <p/>
 * 30.8.2016:
 * - Added custom methods to the class (updateRow, readRow)
 * 29.03.2017:
 * - Added static method getDB() in order to prevent making instances of DataBaseHelper
 * - Changed updateRow and readRow so that possible more than 1 entry can be handeled correctly
 * <p/>
 * <p>
 * /
 ***********************************************/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nejc.mamiapp.BuildConfig;

public class DataBaseHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String PACKAGE_NAME = BuildConfig.APPLICATION_ID;
    private static String DB_PATH = "/data/data/" + PACKAGE_NAME + "/databases/";
    // Data Base Name.
    private static final String DATABASE_NAME = "Workdays";
    private static final String TABLE_NAME = "workdays";
    // Data Base Version.
    private static final int DATABASE_VERSION = 1;


    public Context context;
    static SQLiteDatabase sqliteDataBase;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * It also remembers the database name
     *
     * @param context Parameters of super() are    1. Context
     *                2. Data Base Name.
     *                3. Cursor Factory.
     *                4. Data Base Version.
     */
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        openDataBase();
    }

    /**
     * Creates an empty database on the system and rewrites it with your own database.
     * By calling this method and empty database will be created into the default system path
     * of your application so we are gonna be able to overwrite that database with our database.
     * If the database already exists, nothing happens.
     */
    public void createDataBase() throws IOException {
        //check if the database already exists
        boolean databaseExist = checkDataBase();

        if (databaseExist) {
            // Do Nothing.
        } else {
            this.getWritableDatabase();
            copyDataBase();
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {
        // Tries to copy the file under the given path into the File class instance
        File databaseFile = new File(DB_PATH + DATABASE_NAME);
        return databaseFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transferring byte stream.
     */
    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the input file to the output file using a 1KB buffer
        byte[] buffer = new byte[1024];
        int length;

        // As long as the buffer is not empty from the inputstream write
        // the content into the outputstream
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * This method opens the data base connection.
     * First it create the path up till data base of the device.
     * Then create connection with data base.
     */
    public static void openDataBase() {
        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        try {
            sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteCantOpenDatabaseException e) {
            Log.d("DB", "Could not open Database when callin openDataBase()");
        }
    }

    public static SQLiteDatabase getDB() {
        return sqliteDataBase;
    }

    /**
     * This Method is used to close the data base connection.
     */
    @Override
    public synchronized void close() {
        if (sqliteDataBase != null)
            sqliteDataBase.close();
        super.close();
    }


    // Updates the content of the specified row with the given value
    public static int updateRow(int day, int month, int year, int value) {
        ContentValues values = new ContentValues();
        // Check if the given row already exists
        int back = readRow(day, month, year);
        values.put("day", Integer.toString(day));
        values.put("month", Integer.toString(month));
        values.put("year", Integer.toString(year));
        values.put("type", Integer.toString(value));
        String selection = "day=? AND month=? AND year=?";
        String[] selectionArgs = {Integer.toString(day), Integer.toString(month), Integer.toString(year)};
        long result = sqliteDataBase.update(TABLE_NAME, values, selection, selectionArgs);
        // Something went wrong
        if (result == -1) {
            return 0;
            // Row does not yet exist - use insert instead
        } else if (result == 0) {
            result = sqliteDataBase.insert(TABLE_NAME, null, values);
            if (result < 0) {return 0;}
            return 1;
        } else {
            return 1;
        }
        /*} else {
            //use update
            values.put("type", Integer.toString(value));
            String clause = "day=? AND month=? AND year=?";
            String[] args = {Integer.toString(day), Integer.toString(month), Integer.toString(year)};
            int res=sqliteDataBase.update(TABLE_NAME, values, clause, args);
            return 1;
        }
        */
    }

    // Reads a particular row in the DataBase
    // Makes sure there is no more than one entry for each tuple of values
    // Returns the content of the row.
    // Returns -1 if no value has been found
    public static int readRow(int day, int month, int year) {
        // String[] columns = {"type"};
        String selection = "day=? AND month=? AND year=?";
        String[] selectionArgs = {Integer.toString(day), Integer.toString(month), Integer.toString(year)};
        // Read row
        Cursor cursor = sqliteDataBase.query(TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        // Debug code
        Log.d("Database", "Cursor content:" + DatabaseUtils.dumpCursorToString(cursor));

        // Delete all entries if more than 1 result is returned
        if (cursor.getCount() > 1) {
            int affectedRows = sqliteDataBase.delete(TABLE_NAME, selection, selectionArgs);
            if (affectedRows < 1) {
                Log.d("Database", "No rows have been deleted!");
            }
            return -1;
        }
        // If no row deletion was needed
        int index = cursor.getColumnIndex("type");
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(index);
        } else {
            return -1;
        }
    }

    // TODO: Finish this method!
    public int readRows(int month, int year) {
        String[] columns = {"type"};
        String selection = "month=? AND year=?";
        String[] selectionArgs = {Integer.toString(month), Integer.toString(year)};
        Cursor cursor = sqliteDataBase.query(DATABASE_NAME, columns, selection, selectionArgs, null, null, null, null);
        int length = cursor.getCount();

        return 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No need to write the create table query.
        // As we are using Pre built data base.
        // Which is ReadOnly.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No need to write the update table query.
        // As we are using Pre built data base.
        // Which is ReadOnly.
        // We should not update it as requirements of application.
    }
}