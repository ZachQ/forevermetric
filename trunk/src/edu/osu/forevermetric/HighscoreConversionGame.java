package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
		HighscoreObject hScore= new HighscoreObject(this, "CGHS");
		TextView display = new TextView(this);
		display = (TextView) findViewById(R.id.hstv);
		Integer pos=0;
		Integer posM=-1;
		display.setTextColor(Color.BLACK);
		display.append("\n");
		while(pos<10){
			pos++;
			posM++;
			display.append(pos.toString() +") " + hScore.getName(posM)+ ": "+hScore.getScore(posM) +"\n");
		}
		
		
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
