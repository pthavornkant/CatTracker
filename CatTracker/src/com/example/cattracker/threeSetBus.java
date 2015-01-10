package com.example.cattracker;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class threeSetBus extends Activity {
	Button viewMore,nextThree;
	static TextView busTitle;
	static TextView location1;
	static TextView location2;
	static TextView location3;
	static TextView time1;
	static TextView time2;
	static TextView time3;
	static Cursor myCursorTime;
	static Cursor myCursorLocation;
	static Cursor destiny;
	
	
	public static void setDestined(SQLiteDatabase database){
		destiny = database.rawQuery("Select s_time From Location join Schedule join Bus where s_bid=Bus._id and s_lid=Location._id and s_time>'" + MainActivity.getTime() + "' and b_type='" + findMyBus.getSelectedBus() + "' and l_name='" + searchByLocation.getselectedLocation() + "'", null);
		
		destiny.moveToFirst();
		nextThreeDestined(destiny);
	}
	
	
	public static void nextThreeDestined(Cursor c){
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
		location1.setText(searchByLocation.getselectedLocation());
		location2.setText(searchByLocation.getselectedLocation());
		location3.setText(searchByLocation.getselectedLocation());
		
	}
	public static void setTheLocations(SQLiteDatabase database){
		myCursorLocation = database.rawQuery("Select l_name From Location join Schedule join Bus Where s_time>'" + MainActivity.getTime()+"' and s_lid=Location._id and b_type='" + findMyBus.getSelectedBus()+ "' and Bus._id=s_bid Order by s_time", null);
		myCursorLocation.moveToFirst();
		getNextThreeLocations(myCursorLocation);
	}
	
	public static void setTheTimes(SQLiteDatabase database){
		myCursorTime = database.rawQuery("Select s_time From Schedule join Bus Where s_time>'" + MainActivity.getTime() + "' and b_type='" + findMyBus.getSelectedBus()+ "' and Bus._id=s_bid Order by s_time", null);
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
	
	public static void getNextThreeLocations(Cursor c){
		String location11,location12,location13;
		location11=c.getString(c.getColumnIndex("l_name"));
		c.moveToNext();
		location12=c.getString(c.getColumnIndex("l_name"));
		c.moveToNext();
		location13=c.getString(c.getColumnIndex("l_name"));
		c.moveToNext();
		
		location1.setText(location11);
		location2.setText(location12);
		location3.setText(location13);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		
		
		
		DataBaseHelper myDbHelper = new DataBaseHelper(threeSetBus.this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threesetbus);
		busTitle = (TextView) findViewById(R.id.textView2);
		busTitle.setText(findMyBus.getSelectedBus());
		location1 = (TextView) findViewById(R.id.textView6);
		location2 = (TextView) findViewById(R.id.TextView02);
		location3 = (TextView) findViewById(R.id.TextView06);
		
		time1 = (TextView) findViewById(R.id.textView7);
		time2 = (TextView) findViewById(R.id.TextView03);
		time3 = (TextView) findViewById(R.id.TextView04);
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
		if(MainActivity.getFullSchedule()){
			setDestined(db);
		}else{
		setTheLocations(db);
		setTheTimes(db);
		}
		
		nextThree = (Button) findViewById(R.id.button1);
		nextThree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(MainActivity.getFullSchedule()){
					nextThreeDestined(destiny);
				}else{
				getNextThreeLocations(myCursorLocation);
				getNextThreeTimes(myCursorTime);
				}
			}
		});
	

		viewMore = (Button) findViewById(R.id.button2);
		viewMore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.close();
				Intent ourIntent = new Intent(threeSetBus.this, fullSchedule.class);
				startActivity(ourIntent);
			}
		});
		
		
		
	}

}
