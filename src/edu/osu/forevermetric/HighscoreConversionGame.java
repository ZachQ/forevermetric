package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreConversionGame extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);
		
		//Highscore display
		HighscoreObject hScore= new HighscoreObject(this);
		TextView display = new TextView(this);
		display = (TextView) findViewById(R.id.hstv);
		display.setText("1) " + hScore.getName(0)+ " "+hScore.getScore(0));
		
		// Setup for back Button
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
