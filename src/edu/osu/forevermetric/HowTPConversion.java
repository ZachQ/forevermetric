package edu.osu.forevermetric;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HowTPConversion extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtp_conversion);
		
		TextView tvCGHTP = (TextView)findViewById(R.id.tvCGHTP);
		tvCGHTP.setTextColor(Color.WHITE);
		tvCGHTP.setText(Html.fromHtml(getString(R.string.CGHTP_html)));
		
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
