package edu.osu.forevermetric;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class HighscoreDistanceGame extends Activity implements OnClickListener{
	private ArrayList<HashMap<String, String>> scoresList = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter scoreAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores);
		ListView scoreListView = (ListView) findViewById(R.id.scoresList);
		scoreAdapter = new SimpleAdapter(this, scoresList, R.layout.highscorerow, new String[] { "pos", "name", "score"}, new int[] { R.id.col1, R.id.col2, R.id.col3});
		scoreListView.setAdapter(scoreAdapter);
		//Highscore display
		HighscoreObject hScore= new HighscoreObject(this, "DGHS");
		Integer pos=0;
		Integer posM=-1;
		while(pos<10){
			HashMap<String, String> rowData = new HashMap<String, String>();
			TableRow tr = new TableRow(this);
			pos++;
			posM++;
			String position = pos.toString() +") ";
			String uName = hScore.getName(posM);
			String uScore = Long.toString(hScore.getScore(posM));
			rowData.put("pos",position);
			rowData.put("name",uName);
			rowData.put("score",uScore);
			scoresList.add(rowData);
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
