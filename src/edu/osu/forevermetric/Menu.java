package edu.osu.forevermetric;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity implements OnClickListener{
	private final String TAG = "Menu";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		// Log
		Log.i(TAG, "* Activity successful *");
		
		// Setup for Distance Game Button
		View distanceButton = (Button) findViewById(R.id.bDistance);
		distanceButton.setOnClickListener(this);
		
		// Setup for Conversion Game Button
		View conversionButton = (Button) findViewById(R.id.bConversion);
		conversionButton.setOnClickListener(this);
		
		// Setup for About Button
		View aboutButton = (Button) findViewById(R.id.bAbout);
		aboutButton.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
		super.onResume();
		if(!Network.isNetworkAvailable(this) && !manager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
			 // prepare the alert box
           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

           // set the message to display
           alertbox.setMessage("No network connectivity or GPS was detected.  You may experience issues with the map while playing the distance game.");

           // add a neutral button to the alert box and assign a click listener
           alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

               // click listener on the alert box
               public void onClick(DialogInterface arg0, int arg1) {
                   // window closes
               }
           });

           // show it
           alertbox.show();
		} else if(!Network.isNetworkAvailable(this)) {
			 // prepare the alert box
	           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

	           // set the message to display
	           alertbox.setMessage("No network connectivity was detected.  You may experience issues while playing the distance game.");

	           // add a neutral button to the alert box and assign a click listener
	           alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

	               // click listener on the alert box
	               public void onClick(DialogInterface arg0, int arg1) {
	                   // window closes
	               }
	           });

	           // show it
	           alertbox.show();
			} else if(!manager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
				 // prepare the alert box
		           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

		           // set the message to display
		           alertbox.setMessage("No GPS was detected.  You may experience issues with your location while playing the distance game.");

		           // add a neutral button to the alert box and assign a click listener
		           alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

		               // click listener on the alert box
		               public void onClick(DialogInterface arg0, int arg1) {
		                   // window closes
		               }
		           });

		           // show it
		           alertbox.show();
				}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bDistance:
			// if the user clicks distance game, start that activity
			startActivity(new Intent(this, DistanceStartActivity.class));
			break;
		case R.id.bConversion:
			// if the user clicks conversion game, start that activity
			startActivity(new Intent(this,ConversionStartActivity.class));
			break;
		case R.id.bAbout:
			//if the user clicks conversion game, start that activity
			startActivity(new Intent(this, About.class));
			break;
		}
	} 
	
	public boolean isOnline() {
	    ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

	    return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
}
