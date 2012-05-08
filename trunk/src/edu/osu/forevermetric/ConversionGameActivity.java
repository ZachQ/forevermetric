package edu.osu.forevermetric;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
	private String gradeLevel = "MiddleSchool"; // HARDCODED NOW BUT CHANGE TO BE HIGH SCHOOL

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
		curGame = new ConversionGame(gradeLevel);
		
		//testing param pass of Q
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			String value = extras.getString("numQuestions");
			String gradeLevel = extras.getString("gradeLevel");
			curGame = new ConversionGame(gradeLevel);
			numQ = Integer.parseInt(value);
		}
		
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
				display.setText("You were within "
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
