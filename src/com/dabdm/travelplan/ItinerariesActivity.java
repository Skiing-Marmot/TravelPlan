package com.dabdm.travelplan;

import java.util.ArrayList;

import com.dabdm.travelplan.map.MapActivity;
import com.dabdm.travelplan.places.Place;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * An activity where you can choose one of the existing itineraries or create a new one
 * 
 */
public class ItinerariesActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_itineraries);
	
	test();

	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.fileList());
	setListAdapter(adapter);

	Button button = (Button) findViewById(R.id.open_map_button);
	button.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		//startActivity(new Intent(ItinerariesActivity.this.getApplicationContext(), DestinationActivity.class));
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_itineraries, menu);
	return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
	String item = (String) getListAdapter().getItem(position);
	Intent intent = new Intent(this, MapActivity.class);
	intent.putExtra("travelFileName", this.fileList()[position]);
	startActivity(intent);
    }
    
    public void test() {
	    /*Place p = new Place();
	    p.setFormatted_address("483 George Street, Sydney NSW, Australia");
	    Log.i("test", p.getFormatted_address());
	    Log.i("test", p.getAddressForRequest());*/
	    
	    Travel t = new Travel();
	    t.setDuration(2);
	    t.setPlaceCoordinates(new LatLng(-33.86, 151.205));
	    t.setPlaceName("Australia");
	    t.setRadius(20000);
	    t.setTransportMode("walking");
	    t.setTravelName("Australia" + System.currentTimeMillis());
	    ArrayList<Place> places = new ArrayList<Place>();
	    Place p1 = new Place();
	    p1.setFormatted_address("529 Kent Street, Sydney NSW, Australia");
	    Place.Geometry g1 = new Place.Geometry();
	    Place.Location l1 = new Place.Location();
	    l1.setLat(-33.8750460);
	    l1.setLng(151.2052720);
	    g1.setLocation(l1);
	    p1.setGeometry(g1);
	    p1.setIcon("http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png");
	    p1.setId("827f1ac561d72ec25897df088199315f7cbbc8ed");
	    p1.setName("Tetsuya's");
	    p1.setRating((float) 4.3);
	    places.add(p1);
	    
	    Place p2 = new Place();
	    p2.setFormatted_address("107 George Street, The Rocks NSW, Australia");
	    Place.Geometry g2 = new Place.Geometry();
	    Place.Location l2 = new Place.Location();
	    l2.setLat(-33.8597750);
	    l2.setLng(151.2085920);
	    g2.setLocation(l2);
	    p2.setGeometry(g2);
	    p2.setIcon("http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png");
	    p2.setId("7beacea28938ae42bcac04faf79a607bf84409e6");
	    p2.setName("Rockpool");
	    p2.setRating((float) 4.0);
	    places.add(p2);
	    
	    t.setPlaces(places);
	    
	    StorageHelper.saveTravelObject(getFilesDir(), t.getTravelName(), t);
	}

}
