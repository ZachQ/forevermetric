package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DistanceGameActivity extends MapActivity implements OnClickListener {
	private double totalPercentError;
	private double avgPercentError;
	private int questionNumber;
	private DistanceGame curGame;
	private LocationManager locationManager;
	private String[] results;
	private TextView textview;
	private EditText editText;
	private MapView mapView;
	private MapController mc;
    private GeoPoint p;
    private DrawOverlay locationOverlay;
    private Location userLocation;
    private final String TAG = "DistanceGameActivity";
    
	// Timer
	private long startTime = System.currentTimeMillis() / 1000;
	
	//points system
	private int points=1;
	
	//Number of Questions to be answered
	private int numQ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		totalPercentError = 0;
		avgPercentError = 0;
		questionNumber = 1;
		curGame = new DistanceGame("USA");
		
		// Log
		Log.i(TAG, "* Activity successful *");
		
		//testing param pass of Q
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
		String value = extras.getString("numQuestions");
		String location = extras.getString("landmarkLocation");
		curGame = new DistanceGame(location);
		numQ= Integer.parseInt(value);
		}
		results = new String[numQ];
		
		setContentView(R.layout.distance_game);
		// set first question
		curGame.getNewQuestion();
		//create text view for question
		textview = new TextView(this);
		textview = (TextView) findViewById(R.id.questionTextView);
		//create mapView for displaying map;
		mapView = (MapView) findViewById(R.id.mapView);
		//set text and map
		textview.setText("Question #" + questionNumber + " "+ curGame.getQuestionText());
		// create locationmanager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
		mapIt();
		// create button
		View guessButton = (Button) findViewById(R.id.distanceGameButton);
		guessButton.setOnClickListener(this);
		//set editText button to keypad only
		editText = (EditText) findViewById(R.id.usersGuess);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.distanceGameButton:
			// get guess
			String userGuess = editText.getText().toString();
			

			// get users location
			userLocation = null;
			Criteria criteria = new Criteria(); // TODO possibly set this to do something later
			userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
			double userLat = userLocation.getLatitude();
			double userLong = userLocation.getLongitude();
			// get correct answer
			double correctAnswer = curGame.getAnswer(userLat, userLong);
			try {
				double userGuessDub = Double.valueOf(userGuess); 
				double percentError = (Math.abs(((userGuessDub - correctAnswer) / correctAnswer) * 100));
				totalPercentError += percentError;
				avgPercentError = totalPercentError / questionNumber;
				// Timer
				long curr = (System.currentTimeMillis() / 1000) - startTime;
				//put guess in results for results activity
				results[questionNumber - 1] = "#" + questionNumber + ") guess: "  + userGuess + " answer: " + roundTwoDecimals(correctAnswer);
				
				
				// get/display next question
				questionNumber++;
				if (questionNumber > numQ) {
					
					Bundle bun = new Bundle();
					bun.putDouble("percentError",avgPercentError );
					bun.putInt("numQuestions", questionNumber - 1);
					bun.putStringArray("results", results);
					Intent i = new Intent(getApplicationContext(), ResultsActivity.class);
					i.putExtras(bun);
					startActivity(i);
					finish();
				} else {
					curGame.getNewQuestion();
					TextView textview = new TextView(this);
					textview = (TextView) findViewById(R.id.questionTextView);
					textview.setText("Question #" + questionNumber + " "+ curGame.getQuestionText());
					mapIt();
					// hide soft keyboard(had to put because soft keyboard would not
					// go away)
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(editText.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
					// clear editText field
					editText.setText("");
				}
			} catch(NumberFormatException e){
				//thrown with bad input
				Log.w(TAG, "* Invalid entry *");
			} catch(NullPointerException e2) {
				//thrown when nothing entered, or just '.'
				Log.w(TAG, "* Invalid entry *");
			}
			break;
		}
	}

	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	
	void mapIt() {
		 mapView.setBuiltInZoomControls(true);
		 mc = mapView.getController();
		 
		 String[] coor = curGame.getQuestionLocation();
		 String coordinates[] = {coor[0], coor[1]};
		 double lat = Double.parseDouble(coordinates[0]);
		 double lng = Double.parseDouble(coordinates[1]);
		 
		 p = new GeoPoint((int) (lat * 1E6),(int) (lng * 1E6));
		 
		 mc.animateTo(p);
		 mc.setZoom(5); 
		 
		locationOverlay = new DrawOverlay(getApplicationContext());
		locationOverlay.setLocation(userLocation, coor);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(locationOverlay);
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}

}
