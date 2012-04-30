package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class DistanceStartActivity extends Activity  implements OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distance_start);
		//TODO i don't remember what this screen was supposed to look like so it will eventually be where user enters their options
		View playButton = (Button) findViewById(R.id.playGameButton);
		playButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.playGameButton:
			startActivity(new Intent(this, DistanceGameActivity.class));
			break;
		}
		
	}
	
	
	
}
