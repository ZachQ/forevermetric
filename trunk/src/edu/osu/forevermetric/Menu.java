package edu.osu.forevermetric;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		View distanceButton = (Button) findViewById(R.id.bDistance);
		distanceButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bDistance:
			startActivity(new Intent(this, DistanceStartActivity.class));
			break;
		}
	} 
	
}
