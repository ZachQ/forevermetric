package edu.osu.forevermetric;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
		super.onResume();
		//check for connectivity
				boolean connected = isOnline();
				if(!connected)  {
					AlertDialog alertDialog = new AlertDialog.Builder(this).create();
					alertDialog.setTitle("Connectivity");
					alertDialog.setMessage("No network connectivity was detected.  You may experience issues with the map while playing the distance game.");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					   public void onClick(DialogInterface dialog, int which) {
					      // here you can add functions
					   }
					});
					alertDialog.show();
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
