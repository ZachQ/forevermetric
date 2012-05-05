package edu.osu.forevermetric;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity implements OnClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_activity);
		//get extras from bundle
		Bundle extras = getIntent().getExtras();
		double percentError = extras.getDouble("percentError");
		int numQuestions = extras.getInt("numQuestions");
		TextView textview = new TextView(this);
		textview = (TextView) findViewById(R.id.resultsTextView);
		//set results text
		textview.setText("Your answered " + numQuestions + " questions with a total percent Error of " + roundTwoDecimals(percentError));
		
		//return button
		View returnButton = (Button) findViewById(R.id.bMenuReturn);
		returnButton.setOnClickListener(this);
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
