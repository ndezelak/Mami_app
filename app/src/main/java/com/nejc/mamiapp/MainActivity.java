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

import java.io.IOException;

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
 public String DATABASE_LOG="DATABASE";
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_home_screen);
  int i=0;

  // Create a new fresh instance of the DataBaseHelper class
  MyDataBaseHelper.dbHelper = new DataBaseHelper(getApplicationContext());


 // Copy the old database which is located under assets folder if no database already exists.
  try {
    MyDataBaseHelper.dbHelper.createDataBase();
  }catch(IOException e){
    Log.d(DATABASE_LOG, "Error while creating a database: "+ e.getMessage());
  }




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
