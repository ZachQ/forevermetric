package edu.osu.forevermetric;

import java.text.DecimalFormat;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DistanceGameActivity extends Activity implements OnClickListener {
	private double totalPercentError;
	private double avgPercentError;
	private int questionNumber;
	private DistanceGame curGame;
	private LocationManager locationManager;

	// testing timer
	private long startTime = System.currentTimeMillis() / 1000;
	
	//tester question num
	private int numQ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		totalPercentError = 0;
		avgPercentError = 0;
		questionNumber = 1;
		curGame = new DistanceGame("USA");
		
		//testing param pass of Q
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
		String value = extras.getString("numQuestions");
		String location = extras.getString("landmarkLocation");
		curGame = new DistanceGame(location);
		numQ= Integer.parseInt(value);
		}
		

		setContentView(R.layout.distance_game);
		// set first question
		curGame.getNewQuestion();

		TextView textview = new TextView(this);
		textview = (TextView) findViewById(R.id.questionTextView);
		textview.setText("Question #" + questionNumber + " "+ curGame.getQuestionText());
		// create locationmanager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// create button
		View guessButton = (Button) findViewById(R.id.distanceGameButton);
		guessButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.distanceGameButton:
			// get guess
			EditText editText = (EditText) findViewById(R.id.usersGuess);
			String userGuess = editText.getText().toString();

			// get users location
			Location userLocation = null;
			Criteria criteria = new Criteria(); // TODO possibly set this to do
												// something later
			userLocation = locationManager.getLastKnownLocation(locationManager
					.getBestProvider(criteria, true));
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
				// display stuff
				TextView display = new TextView(this);
				display = (TextView) findViewById(R.id.answerField);
				display.setText("You are located at " + roundTwoDecimals(userLat)
						+ " " + roundTwoDecimals(userLong) + "\n"
						+ "You were within "
						+ Double.toString(roundTwoDecimals(percentError))
						+ "% of the correct answer\n" + "Your guess was "
						+ userGuess + ", correct answer was "
						+ roundTwoDecimals(correctAnswer)
						+ "\n Your current time: " + curr + "s");
	
				// get/display next question
				questionNumber++;
				if (questionNumber > numQ) {
					
					Bundle bun = new Bundle();
					bun.putDouble("percentError",avgPercentError );
					bun.putInt("numQuestions", questionNumber - 1);
					Intent i = new Intent(getApplicationContext(), ResultsActivity.class);
					i.putExtras(bun);
					startActivity(i);
				} else {
					curGame.getNewQuestion();
					TextView textview = new TextView(this);
					textview = (TextView) findViewById(R.id.questionTextView);
					textview.setText("Question #" + questionNumber + " "
							+ curGame.getQuestionText());
					// hide soft keyboard(had to put because soft keyboard would not
					// go away)
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(editText.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
					// clear editText field
					editText.setText("");
				}
			} catch(NumberFormatException e){
				TextView display = (TextView) findViewById(R.id.answerField);
				display.setText("Invalid entry, please enter a decimal number");
			}
			break;

		}

	}

	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}

}
