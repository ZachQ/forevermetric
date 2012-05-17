package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HowTPConversion extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtp_conversion);
		
		View backButton = (Button) findViewById(R.id.buttonBacktoConv);
		backButton.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonBacktoConv:
			// back to conversion game activity
			finish();
			break;
		}
	} 

}
