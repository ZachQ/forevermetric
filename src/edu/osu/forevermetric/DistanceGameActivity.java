package edu.osu.forevermetric;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
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
	private int correct;
	private int incorrect;
	private DistanceGame curGame;
	private LocationManager locationManager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		correct = 0;
		incorrect = 0;
		curGame = new DistanceGame("USA");
		
		setContentView(R.layout.distance_game);
		//set first question
		curGame.getNewQuestion();
		
		TextView textview = new TextView(this);
		textview = (TextView) findViewById(R.id.questionTextView);
		textview.setText(curGame.getQuestionText());
		//create locationmanager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
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
			
			//check correctness
			//get users location
		    Location userLocation = null;
		    Criteria criteria = new Criteria(); //TODO possibly set this to do something later
		    userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
		    double userLat = userLocation.getLatitude();
		    double userLong = userLocation.getLongitude();
		    double percentError = curGame.checkAnswer(userGuess, userLat, userLong);
			TextView display = new TextView(this);
			display = (TextView) findViewById(R.id.answerField);
			display.setText(Double.toString(percentError) + " " + userLocation.getLatitude() + " " + userLocation.getLongitude());
			
			//TODO get/display next question
			break;
			
		}
		
	}
	
	
	
}
