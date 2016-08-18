package com.nejc.mamiapp;
/**
 * @author Nejc
 *
 * Description:
 *
 * Main activity with 2 buttons to select the next activity the user wants to go to.
 *
 * It also initializes the SQL database if it does not yet exist
 *
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*********** REVISION HISTORY *****************
 *
 * 5/6/2016:
 *         + Comments and revision history template
 *
 * 18/08/2016:
 *          + Changed logo
 *          - commented some debugging stuff regarding database handling
 *
 /***********************************************/


public class MainActivity extends AppCompatActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_home_screen);

  int i=0;
  //Database initialization with spec ified name and mode (Private means only this app can use it)
  SQLiteDatabase sqLiteDatabase=getApplicationContext().openOrCreateDatabase("Workdays",this.MODE_PRIVATE,null);
  // SQLLite instruction to create a table with the name workdays if it still doesn't exist
  // (3) only means you reserve 3 Bytes for each entry
  sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS workdays (day INT(3), month INT(3), year INT(3), type INT(3))");
  //Type definition:
  // 0->Prazno
  // 1->Šank dopoldan
  // 2->Šank popoldan
  // 3->Kuhinja dopoldan
  // 4->Kuhinja popoldan
  // 5->Prosto
  // 6->Dopust


 /*
  //Read through the database using a Cursor. Select everything from the Database
  Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM workdays",null);
  int array=0;

  // If the cursor is not empty
  if(cursor.moveToFirst() && cursor!=null ) {
   // Store all the integers (day, month, year, type) into an array
   do{
    array = cursor.getInt(0);
    Log.i("Days in database:", Integer.toString(array));
    array = cursor.getInt(1);
    Log.i("Month in database:", Integer.toString(array));
    array = cursor.getInt(2);
    Log.i("Year in database:", Integer.toString(array));
    array = cursor.getInt(3);
    Log.i("Type in database:", Integer.toString(array));
   }while( cursor.moveToNext() );
  }

*/

  Button weekButton=(Button)findViewById(R.id.Button_week);
  Button monthButton=(Button)findViewById(R.id.Button_month);


  weekButton.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    Intent intent=new Intent(getApplicationContext(),InputActivity.class);
    startActivity(intent);
   }
  });


  monthButton.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    Intent intent=new Intent(getApplicationContext(),OverviewActivity.class);
    startActivity(intent);
   }
  });

 }



}
