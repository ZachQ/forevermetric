package edu.osu.forevermetric;

import edu.osu.forevermetric.ConversionStartActivity.MyOnItemSelectedListener;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DistanceStartActivity extends Activity implements OnClickListener {
	private String numQ;
	private String  landmarkLocation;
	private final String TAG = "DistanceStartActivity";
	private EditText userName;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distance_start);
		
		// Log
		Log.i(TAG, "* Activity successful *");
				
		View playButton = (Button) findViewById(R.id.playGameButton);
		playButton.setOnClickListener(this);
		// Setup for htp Button
		View aboutButton = (Button) findViewById(R.id.howToPlayButtondis);
		aboutButton.setOnClickListener(this);
		//setup HS button
		View hsButton = (Button) findViewById(R.id.hsButtondis);
		hsButton.setOnClickListener(this);
		// set up for edit text
		userName = (EditText) findViewById(R.id.etNameDis);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinnerLandMarkLoc);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.selLandMarkLoc,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

		// Setup Number of questions selection dropdown
		Spinner spinner2 = (Spinner) findViewById(R.id.spinnerNumQuestions);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.selNumQuestions,
				android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			switch (parent.getId()) {
			case R.id.spinnerLandMarkLoc:
				landmarkLocation = parent.getItemAtPosition(pos).toString();
				break;
			case R.id.spinnerNumQuestions:
				numQ = parent.getItemAtPosition(pos).toString();
				break;
			}
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.playGameButton:
			Bundle bun = new Bundle();
			bun.putString("numQuestions", numQ);
			bun.putString("landmarkLocation", landmarkLocation);
			bun.putString("userName", userName.getText().toString());
			Intent i = new Intent(getApplicationContext(), DistanceGameActivity.class);
			i.putExtras(bun);
			startActivity(i);
			break;
		case R.id.howToPlayButtondis:
			startActivity(new Intent(this, HowTPDistance.class));
			break;
		case R.id.hsButtondis:
			startActivity(new Intent(this, HighscoreDistanceGame.class));
			break;
		}

	}
}
