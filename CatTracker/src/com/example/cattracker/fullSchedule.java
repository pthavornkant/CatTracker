package com.example.cattracker;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class fullSchedule extends Activity{
	TextView busName,driverName,licensePlate,days;
	Button mainMenu;
	Cursor busCursor;
	Cursor driverCursor;
	Cursor licensePlateCursor;
	Cursor daysCursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		DataBaseHelper myDbHelper = new DataBaseHelper(fullSchedule.this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullschedule); 
		
		
		try {
			Log.i("info", "3434343434434343434343434343434");
			 myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			Log.i("info", "5313513513513513513513515315");
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		
		final SQLiteDatabase db = myDbHelper.getReadableDatabase();
		driverCursor = db.rawQuery("Select d_name from Driver Where d_type='" + findMyBus.getSelectedBus() + "'", null);
		driverCursor.moveToFirst();
		String driver = driverCursor.getString(driverCursor.getColumnIndex("d_name"));
		
		licensePlateCursor = db.rawQuery("Select b_plate from Bus where b_type='" + findMyBus.getSelectedBus() + "'", null);
		licensePlateCursor.moveToFirst();
		String plate = licensePlateCursor.getString(licensePlateCursor.getColumnIndex("b_plate"));
		
		daysCursor = db.rawQuery("Select s_days from Schedule join Bus where Bus._id=s_bid and b_type='" +  findMyBus.getSelectedBus() + "'", null);
		daysCursor.moveToFirst();
		String day = daysCursor.getString(daysCursor.getColumnIndex("s_days"));
		
		busName = (TextView) findViewById(R.id.textView1);
		busName.setText(findMyBus.getSelectedBus());
		
		driverName = (TextView) findViewById(R.id.TextView02);
		driverName.setText(driver);
		
		licensePlate = (TextView) findViewById(R.id.TextView03);
		licensePlate.setText(plate);
		
		days = (TextView) findViewById(R.id.textView5);
		days.setText(day);
		
		db.close();
		
		mainMenu= (Button) findViewById(R.id.button1);
		
mainMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(fullSchedule.this, MainActivity.class);
				startActivity(i);
				finish();
				
			}
		});
	
		
		
	}

}
