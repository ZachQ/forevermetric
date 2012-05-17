package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HighscoreConversionGame extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		// Setup for Distance Game Button
		View backButton = (Button) findViewById(R.id.bRetFromHS);
		backButton.setOnClickListener(this);
		
		//testing in progress
		
		
		//end of testing in progress
		
		
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bRetFromHS:
			// returns to previous screen
			finish();
			break;
		}
	} 

}
