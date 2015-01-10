package com.example.cattracker;

//import com.example.cattracker.R.string;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class searchByLocation extends ListActivity{
	static String selectedLocation;
	String locations [] ={"Scholars Lane at Mammoth Lakes Rd.",
			"Gallo Recreation & Wellness Center (Rec Center)",
			"Kolligian Library on Ranchers Road",
			"Emigrant Pass at Scholars Lane",
			"Moraga subdivision",
			"Starbucks/Promenade",
			"Village Landing Apartments",
			"Buena Vista",
			"Merced Mall/Target",
			"Villages Apartments",
			"The Bus Terminal",
			"Ironstone Dr.",
			"Bellevue Ranch on Bancroft Dr.",
			"El Redondo",
			"Merced Mall Theatre",
			"Amtrak Station",
			"Hollywood Theatres Mainplace",
			"Granville Luxury Apartments",
			"Save Mart/Barnes & Noble",
			"Rite Aid/Walgreens on G St.",
			"Millennium Sports Club",
			"Applebees",
			"La Hacienda II",
			"University Surgery Center",
			"Mercy Hospital",
			"Bellevue Ranch",
			 "Castle Air Park",
			 "Walmart",
			"Save Mart",
			"Swiss colony Apts.",
			"G St. & W. Alexander Ave.",
			"G St. and El Portal Dr.",
			"El Portal Plaza",
			"Merced Sun-Star",
			"Merced Floral"}; 
	
	
	
	protected void onCreate(Bundle findMyBusOnCreate) {
		// TODO Auto-generated method stub
		super.onCreate(findMyBusOnCreate);
	
		setListAdapter(new ArrayAdapter<String>(searchByLocation.this, android.R.layout.simple_list_item_1, locations));
	}
	
	public static void setselectedLocation(String s){
		selectedLocation = s;
	}
	
	public static String getselectedLocation(){
		return selectedLocation;
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id){
		
		super.onListItemClick(l, v, position, id);
		setselectedLocation(locations[position]);
		if(MainActivity.getFullSchedule()){
		Intent ourIntent = new Intent(searchByLocation.this, threeSetBus.class);
		startActivity(ourIntent);
		}else{
			Intent ourIntent = new Intent(searchByLocation.this, threeSetLocation.class);
			startActivity(ourIntent);
		}
		
		
		
	} 

}
