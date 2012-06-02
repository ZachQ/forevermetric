package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConversionGameActivity extends Activity implements OnClickListener {
	private double totalPercentError;
	private double avgPercentError;
	private int questionNumber;
	private ConversionGame curGame;
	private LocationManager locationManager;
	private String[] results;
	private final String TAG = "ConversionGameActivity";
	private ArrayList<String> guessList;
	private ArrayList<String> answerList;
	
	// Timer
	private long startTime = System.currentTimeMillis() / 1000;
	
	//Point system
	private long points=100;
	public static final String CGHS = "CGHS";
	private String userN="Name input error";
	

	
	//Number of questions to be answered
	private int numQ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		totalPercentError = 0;
		avgPercentError = 0;
		questionNumber = 1;
		curGame = new ConversionGame("MiddleSchool");
		answerList = new ArrayList<String>();
		guessList = new ArrayList<String>();
		
		// Log that actvity successful
		Log.i(TAG, "* Activity successful *");
		
		//testing param pass of Q
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			String value = extras.getString("numQuestions");
			String gradeLevel = extras.getString("gradeLevel");
			userN = extras.getString("userName");
			curGame = new ConversionGame(gradeLevel);
			numQ = Integer.parseInt(value);
		}
		results = new String[numQ];
		setContentView(R.layout.conversion_game);
		
		// set first question
		curGame.getNewQuestion();

		TextView textview = new TextView(this);
		textview = (TextView) findViewById(R.id.questionTextView);
		textview.setText("Question #" + questionNumber + " "+ curGame.getQuestionText());

		// create button
		View guessButton = (Button) findViewById(R.id.conversionGameButton);
		guessButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.conversionGameButton:
			// get guess
			EditText editText = (EditText) findViewById(R.id.usersGuess);
			String userGuess = editText.getText().toString();
			
			// get correct answer
			double correctAnswer = curGame.getAnswer();
			// get the units for display purposes
			String Aunits = curGame.getAnswerUnits();
			String Qunits = curGame.getQuestionUnits();
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
				
				// this is for printing out commas to make the output look nice
				Log.i("ConversionGameAct" ,Double.toString(roundTwoDecimals(correctAnswer)));
				String correctPP = prettyPrint(Double.toString(roundTwoDecimals(correctAnswer)));
				Log.i("ConverstionGameCorrectPP", correctPP);
				
				display.setText("* You were within "
						+ Double.toString(roundTwoDecimals(percentError))
						+ "% of the correct answer\n" + "* Your guess was "
						+ prettyPrint(userGuess) + Aunits + "\n * Correct answer was "
						+ correctPP + Aunits
						+ "\n * Your current time: " + curr + "s");
	
				guessList.add(prettyPrint(userGuess) + " " + Aunits);
				answerList.add(correctPP + " " + Aunits);

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
					HighscoreObject hScore = new HighscoreObject(this, "CGHS");
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
				Log.w(TAG, "* Invalid entry *");
				Log.i("ConversionGame Catch", userGuess);
				display.setText("Invalid entry, please enter a decimal number");
			}
			break;
		}
	}

	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	
	/**
	 * This is to format the number to have commas for printing
	 * @param userguess
	 * @return
	 */
	private String prettyPrint(String userguess){
		String result = "";	
		result = NumberFormat.getInstance().format(Double.valueOf(userguess));
		return result;	
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}

}
