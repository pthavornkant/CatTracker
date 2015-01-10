package com.example.cattracker;

//import com.example.cattracker.R.string;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class findMyBus extends ListActivity{
	static String selectedBus;
	String buses [] ={"FastCat","AB", "C1", "C2", "E", "NiteCat", "E1", "E2"}; 
	
	
		
	
	protected void onCreate(Bundle findMyBusOnCreate) {
		// TODO Auto-generated method stub
		super.onCreate(findMyBusOnCreate);
	
		setListAdapter(new ArrayAdapter<String>(findMyBus.this, android.R.layout.simple_list_item_1, buses));
	}
	
	public static void setSelectedBus(String s){
		
		selectedBus = s;
	}
	
	public static String getSelectedBus(){
		return selectedBus;
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id){
		
		super.onListItemClick(l, v, position, id);
		setSelectedBus(buses[position]);
		if(MainActivity.getFullSchedule()){
			Intent ourIntent = new Intent(findMyBus.this, searchByLocation.class);
			startActivity(ourIntent);
		}else{
		Intent ourIntent = new Intent(findMyBus.this, threeSetBus.class);
		startActivity(ourIntent);
		}
		
		
	} 

}
