package edu.osu.forevermetric;


import edu.osu.forevermetric.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Splash extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        // Have the splash screen wait 5 seconds and then move onto the Menu
		final int sleepTime = 5000;
		// timer to sleep for 5 seconds
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
