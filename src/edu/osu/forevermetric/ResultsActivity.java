package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ResultsActivity extends Activity implements OnClickListener{
	private final String TAG = "ResultsActivity";
	private ArrayList<HashMap<String, String>> resultsList = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter resultsAdapter;
	
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
			ArrayList<String> answers = extras.getStringArrayList("answer");
			ArrayList<String> guess = extras.getStringArrayList("guess");
			long points = extras.getLong("score");
			boolean onHS = extras.getBoolean("madeHS");
			TextView topTextview = new TextView(this);
			topTextview = (TextView) findViewById(R.id.resultsTopTextView);
			//set results 
			int i = 0;
			ListView resultsListView = (ListView) findViewById(R.id.resultsListView);
			resultsAdapter = new SimpleAdapter(this, resultsList, R.layout.resultsrow, new String[] { "num", "guess", "answer"}, new int[] { R.id.col1, R.id.col2, R.id.col3});
			resultsListView.setAdapter(resultsAdapter);
			while(i < guess.size()) {
				HashMap<String, String> rowData = new HashMap<String, String>();
				rowData.put("num", Integer.toString(i+1));
				rowData.put("guess", guess.get(i));
				rowData.put("answer", answers.get(i));
				resultsList.add(rowData);
				i++;
			}
			topTextview.setText("You have earned a score of " + points + " points!");
			if(onHS){
				topTextview.append("\n NEW HIGH SCORE!!!");
			}
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
