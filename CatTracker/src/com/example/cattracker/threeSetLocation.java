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
import android.widget.TextView;

public class threeSetLocation extends Activity{

	static Cursor myCursorBus,myCursorTime;
	static TextView bus1,bus2,bus3;
	static TextView time1;
	static TextView time2;
	static TextView time3;
	Button nextThree,mainMenu;
	TextView locationTitle;
	
	public static void setTheBus(SQLiteDatabase database){
		myCursorBus = database.rawQuery("Select b_type From Location join Bus join Schedule Where s_time>'" + MainActivity.getTime() + "' and l_name = '" + searchByLocation.getselectedLocation()+ "' and Location._id=s_lid and Bus._id=s_bid Order by s_time", null);
		myCursorBus.moveToFirst();
		getNextThreeBuses(myCursorBus);
	}
	
	public static void setTheTimes(SQLiteDatabase database){
		myCursorTime = database.rawQuery("Select s_time From Schedule join Location Where s_time>'" + MainActivity.getTime() + "' and l_name = '" + searchByLocation.getselectedLocation()+ "' and Location._id=s_lid Order by s_time", null);
		myCursorTime.moveToFirst();
		getNextThreeTimes(myCursorTime);
	}
	
	public static void getNextThreeTimes(Cursor c){
		String time11,time12,time13;
		
		time11=c.getString(c.getColumnIndex("s_time"));
		c.moveToNext();
		time12=c.getString(c.getColumnIndex("s_time"));
		c.moveToNext();
		time13=c.getString(c.getColumnIndex("s_time"));
		c.moveToNext();
		time1.setText(time11);
		time2.setText(time12);
		time3.setText(time13);
	}
	
	public static void getNextThreeBuses(Cursor c){
		String bus11,bus12,bus13;
		bus11=c.getString(c.getColumnIndex("b_type"));
		c.moveToNext();
		bus12=c.getString(c.getColumnIndex("b_type"));
		c.moveToNext();
		bus13=c.getString(c.getColumnIndex("b_type"));
		c.moveToNext();
		
		bus1.setText(bus11);
		bus2.setText(bus12);
		bus3.setText(bus13);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		DataBaseHelper myDbHelper = new DataBaseHelper(threeSetLocation.this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threesetlocation);
		locationTitle = (TextView) findViewById(R.id.textView002);
		locationTitle.setText(searchByLocation.getselectedLocation());
		
		bus1 = (TextView) findViewById(R.id.textView006);
		bus2 = (TextView) findViewById(R.id.TextView01);
		bus3 = (TextView) findViewById(R.id.TextView0006);
		
		time1 = (TextView) findViewById(R.id.textView007);
		time2 = (TextView) findViewById(R.id.TextView0003);
		time3 = (TextView) findViewById(R.id.TextView0004);
		
		try {
			
			 myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		
		final SQLiteDatabase db = myDbHelper.getReadableDatabase();
		setTheBus(db);
		setTheTimes(db);
		
		
		
		
		nextThree = (Button) findViewById(R.id.button1);
		nextThree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getNextThreeBuses(myCursorBus);
				getNextThreeTimes(myCursorTime);
			}
		});
	

		mainMenu = (Button) findViewById(R.id.button2);
		mainMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.close();
				Intent ourIntent = new Intent(threeSetLocation.this, MainActivity.class);
				startActivity(ourIntent);
				finish();
			}
		});
		
		
		
	}

}
