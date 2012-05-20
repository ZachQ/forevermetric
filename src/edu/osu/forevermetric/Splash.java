package edu.osu.forevermetric;


import edu.osu.forevermetric.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Splash extends Activity {
	private final String TAG = "Splash";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        // Initial Log
        Log.i(TAG, "* ForeverMetric successfully started *");
        
        // Have the splash screen wait 3 seconds and then move onto the Menu
		final int sleepTime = 3000;
		// timer to sleep for 3 seconds
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					Intent openStartingPoint = new Intent("edu.osu.forevermetric.MENU");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
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
