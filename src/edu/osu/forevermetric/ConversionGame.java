package edu.osu.forevermetric;

import java.util.ArrayList;
import java.util.Random;

import android.location.Location;

public class ConversionGame {
	
	private ArrayList<ConversionGameObject> objectsList;
	private ConversionGameObject curQuestion;
	
	ConversionGame(String gradeLevel) {
		objectsList = new ArrayList<ConversionGameObject>();
		if(gradeLevel.compareTo("MiddleSchool") == 0) {
			ConversionGameObject tempObject = new ConversionGameObject("How many meters are in 564.7 kilometers?", Measurement.METER);
			objectsList.add(tempObject);
			tempObject = new ConversionGameObject("Convert 5403.2 miles into feet", Measurement.FOOT);
			objectsList.add(tempObject);
			tempObject = new ConversionGameObject("The moon is on average, 238,857 miles from Earth. How many kilometers is that?", Measurement.KILOMETER);
			objectsList.add(tempObject);			
		} if(gradeLevel.compareTo("HighSchool") == 0) {

		}
	}
	
	public ConversionGameObject getNewQuestion() {
		ConversionGameObject newObject = null;
		int upper = objectsList.size();
		int lower = 0;
		Random r = new Random();
		int index=r.nextInt(upper-lower) + lower;
		newObject = objectsList.remove(index);
		curQuestion = newObject;
		return newObject;
	}
	
	public double getAnswer() {
		
		return 0;
	}
	
	public String getQuestionText() {
		return curQuestion.getQuestionText();
	}
}
