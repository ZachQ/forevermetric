package edu.osu.forevermetric;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;
 

public class MapTestActivity extends MapActivity{
	private MapView mapView;
	MapController mc;
    GeoPoint p;
    
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.test);  
	     mapView = (MapView) findViewById(R.id.mapView2);      
	     mapView.setBuiltInZoomControls(true);
	     mc = mapView.getController();
	        String coordinates[] = {"1.352566007", "103.78921587"};
	        double lat = Double.parseDouble(coordinates[0]);
	        double lng = Double.parseDouble(coordinates[1]);
	 
	        p = new GeoPoint(
	            (int) (lat * 1E6), 
	            (int) (lng * 1E6));
	 
	        mc.animateTo(p);
	        mc.setZoom(17); 
	        mapView.invalidate();
	 }
		         
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
