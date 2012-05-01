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
import android.widget.EditText;
import android.widget.TextView;


public class DistanceGameActivity extends Activity implements OnClickListener{
	int correct;
	int incorrect;
	DistanceGame game;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		correct = 0;
		incorrect = 0;
		
		setContentView(R.layout.distance_game);
		//set question
		TextView textview = new TextView(this);
		textview = (TextView) findViewById(R.id.questionTextView);
		textview.setText("how many meters are you from the Ohio Stadium?" );
		
		//create button
		View guessButton = (Button) findViewById(R.id.distanceGameButton);
		guessButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.distanceGameButton:
			//get guess
			EditText editText = (EditText) findViewById(R.id.usersGuess);
			String userGuess = editText.getText().toString();
			//get users location
			LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		    Location userLocation = null;
		    userLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		    //set location of ohio stadium
			Location questionDestination = new Location("");
			questionDestination.setLatitude(40.001667);
			questionDestination.setLongitude(-83.019722);
			//get distance beween user and location
			float correctAnswer = userLocation.distanceTo(questionDestination);
			TextView display = new TextView(this);
			display = (TextView) findViewById(R.id.answerField);
			display.setText(Float.toString(correctAnswer));
			break;
			
		}
		
	}
	
	
	
}
