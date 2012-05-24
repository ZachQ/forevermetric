package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HowTPDistance extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtp_distance);
		
		TextView tvDGHTP = (TextView)findViewById(R.id.tvDGHTP);
		tvDGHTP.setText(Html.fromHtml(getString(R.string.DGHTP_html)));
		// Setup for Distance Game Button
		View backButton = (Button) findViewById(R.id.buttonBacktoDist);
		backButton.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonBacktoDist:
			// if the user clicks distance game, start that activity
			finish();
			break;
		}
	} 

}
