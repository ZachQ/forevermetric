package edu.osu.forevermetric;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity implements OnClickListener{
	private final String TAG = "ResultsActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_activity);
		
		// Log
		Log.i(TAG, "* Activity successful *");
		
		//get extras from bundle
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			double percentError = extras.getDouble("percentError");
			int numQuestions = extras.getInt("numQuestions");
			String[] results = extras.getStringArray("results");
			long points = extras.getLong("score");
			TextView textview = new TextView(this);
			textview = (TextView) findViewById(R.id.resultsTextView);
			TextView topTextview = new TextView(this);
			topTextview = (TextView) findViewById(R.id.resultsTopTextView);
			//set results 
			int i = 0;
			while(i < results.length) {
				textview.append(results[i]);
				textview.append("\n");
				i++;
			}
			topTextview.setText("Your total percent Error was %" + roundTwoDecimals(percentError) + " earning you a score of " + points + " points!");
			textview.setMovementMethod(new ScrollingMovementMethod());
			//return button
			View returnButton = (Button) findViewById(R.id.bMenuReturn);
			returnButton.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bMenuReturn:
			Intent i = new Intent(getApplicationContext(), Menu.class);
			startActivity(i);
		}
		
	}
	
	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
}
