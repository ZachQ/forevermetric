package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity implements OnClickListener{
	private final String TAG = "About";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		// Log
		Log.i(TAG, "* Activity Successful *");
		
		// Setup for Distance Game Button
		View backButton = (Button) findViewById(R.id.bBackToMenu);
		backButton.setOnClickListener(this);
		
		//testing text print
		TextView CGabout = (TextView)findViewById(R.id.tvCGabout);
		CGabout.setText(Html.fromHtml(getString(R.string.CGabout_html)));
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bBackToMenu:
			// if the user clicks distance game, start that activity
			finish();
			break;
		}
	} 

}
