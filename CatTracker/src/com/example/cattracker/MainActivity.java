package com.example.cattracker;




import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
Button findMyBus,findMyLocation,fullschedule,favoriteRoutes;
static boolean viewFullSchedule;
static String time;

public static void setFullScheduleBoolean(int i){
	if(i==1)
		viewFullSchedule=true;
	else
		viewFullSchedule=false;
	
}

public static boolean getFullSchedule(){
	return viewFullSchedule;
}
public static void setTime(String s){
	time=s;
}

public static String getTime(){
	return time;
	
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DataBaseHelper myDbHelper = new DataBaseHelper(MainActivity.this);
		Date now = new Date();
		SimpleDateFormat militaryTime = new SimpleDateFormat("kk:mm");
		String st = militaryTime.format(now).toString();
		setTime(st);
		
		Log.i("info", time);
		Log.i("info", time);
		Log.i("info", time);
		Log.i("info", time);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		
	
		
		findMyBus= (Button) findViewById(R.id.Button01);
		findMyLocation = (Button) findViewById(R.id.Button02);
		fullschedule = (Button) findViewById(R.id.Button03);
		favoriteRoutes = (Button) findViewById(R.id.Button04);
		
		findMyBus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setFullScheduleBoolean(0);
				Intent i = new Intent(MainActivity.this, findMyBus.class);
				startActivity(i);
			}
		});
		
		findMyLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, searchByLocation.class);
				startActivity(i);
				
			}
		});
		
		fullschedule.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFullScheduleBoolean(1);
				Intent i = new Intent(MainActivity.this, findMyBus.class);
				startActivity(i);
				
			}
		});
		
		favoriteRoutes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
