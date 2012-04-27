package edu.osu.forevermetric;


import edu.osu.forevermetric.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Splash extends Activity {
	
	Button start;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	}
	
	@Override
	protected void onPause() {
		// This will "pause" the activity aka kill itself so
		// the user can't press the "back" button and go back
		// to the splash screen.
		super.onPause();
		finish();
	}

}
