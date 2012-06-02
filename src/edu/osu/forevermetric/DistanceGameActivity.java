package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.Html;
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
	private String userN="Name input error";
	private ArrayList<String> guessList;
	private ArrayList<String> answerList;
	// Timer
	private long startTime = System.currentTimeMillis() / 1000;
	
	//points system
	private long points=100;
	
	//Number of Questions to be answered
	private int numQ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		totalPercentError = 0;
		avgPercentError = 0;
		questionNumber = 1;
		curGame = new DistanceGame("USA");
		answerList = new ArrayList<String>();
		guessList = new ArrayList<String>();
		
		// Log
		Log.i(TAG, "* Activity successful *");
		
		//testing param pass of Q
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			String value = extras.getString("numQuestions");
			String location = extras.getString("landmarkLocation");
			userN = extras.getString("userName");
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
		HashMap<String, String> rowData = new HashMap<String, String>();
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
			String unit = curGame.getUnit();
			try {
				double userGuessDub = Double.valueOf(userGuess); 
				double percentError = (Math.abs(((userGuessDub - correctAnswer) / correctAnswer) * 100));
				totalPercentError += percentError;
				avgPercentError = totalPercentError / questionNumber;
				// Timer
				long curr = (System.currentTimeMillis() / 1000) - startTime;
				//put guess in results for results activity
				String guess = userGuess + " " + unit;
				String answer = roundTwoDecimals(correctAnswer) + " " + unit;
				guessList.add(guess);
				answerList.add(answer);
				// get/display next question
				questionNumber++;
				if (questionNumber > numQ) {
					
					Bundle bun = new Bundle();
					bun.putDouble("percentError",avgPercentError );
					bun.putInt("numQuestions", questionNumber - 1);
					bun.putStringArrayList("guess", guessList);
					bun.putStringArrayList("answer", answerList);
					Intent i = new Intent(getApplicationContext(), ResultsActivity.class);
					
					//Publish Highscore
					//Creates Highscore Object
					HighscoreObject hScore = new HighscoreObject(this, "DGHS");
					points = (long) (points-avgPercentError)*numQ;
					bun.putLong("score", points);
					i.putExtras(bun);
					boolean worked = hScore.addScore(userN, points);
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
