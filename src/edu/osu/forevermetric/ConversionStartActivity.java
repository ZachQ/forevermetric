package edu.osu.forevermetric;

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


public class ConversionStartActivity extends Activity  implements OnClickListener{
	private String numQ, gradeLevel;
	private final String TAG = "ConversionStartActivity";
	private EditText userName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conversion_start);
		
		// Log
		Log.i(TAG, "* Activity successful *");
		
		View playButton = (Button) findViewById(R.id.playGameButton);
		playButton.setOnClickListener(this);
		// Setup for htp Button
		View aboutButton = (Button) findViewById(R.id.howToPlayButtonCon);
		aboutButton.setOnClickListener(this);
		// Setup for hs button
		View hsButton = (Button) findViewById(R.id.hsButtonCon);
		hsButton.setOnClickListener(this);
		// set up for edit text
		userName = (EditText) findViewById(R.id.etNameCon);
		
		// Setup Grade level selection dropdown
		Spinner spinner = (Spinner) findViewById(R.id.spinnerGradeLevel);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.selGradeLevel, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);	    
	    spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	    
	    // Setup Number of questions selection dropdown
		Spinner spinner2 = (Spinner) findViewById(R.id.spinnerNumQuestions);
	    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
	            this, R.array.selNumQuestions, android.R.layout.simple_spinner_item);
	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter2);
	    spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			switch (parent.getId()) {
				case R.id.spinnerGradeLevel:
					gradeLevel = parent.getItemAtPosition(pos).toString();
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
		switch(v.getId()) {
		case R.id.playGameButton:
			// user clicks play game button to go to Conversion Game
			Bundle bun = new Bundle();
			bun.putString("numQuestions", numQ);
			bun.putString("gradeLevel", gradeLevel);
			bun.putString("userName", userName.getText().toString());
			Intent i = new Intent(getApplicationContext(), ConversionGameActivity.class);
			i.putExtras(bun);
			startActivity(i);
			break;
		case R.id.howToPlayButtonCon:
			startActivity(new Intent(this,HowTPConversion.class));
			break;
		case R.id.hsButtonCon:
			startActivity(new Intent(this,HighscoreConversionGame.class));
			break;
			
		}
		
	}
}