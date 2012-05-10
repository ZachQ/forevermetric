package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
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
	
}
