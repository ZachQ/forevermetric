package edu.osu.forevermetric;

import java.util.ArrayList;
import java.util.Random;

import android.location.Location;

public class DistanceGame {
	private ArrayList<DistanceGameObject> objectsList;
	private DistanceGameObject curQuestion;
	
	DistanceGame(String landmarkLocation) {
		objectsList = new ArrayList<DistanceGameObject>();
		if(landmarkLocation.compareTo("USA") == 0 || landmarkLocation.compareTo("All") == 0) {
			DistanceGameObject tempObject = new DistanceGameObject("How many meters are you from The Ohio Stadium?", Measurement.METER, 40.001667, -83.019722);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from The Ohio Stadium?", Measurement.KILOMETER, 40.001667, -83.019722);
			objectsList.add(tempObject);
			
		} if(landmarkLocation.compareTo("Europe") == 0 || landmarkLocation.compareTo("All") == 0) {
			
		} if(landmarkLocation.compareTo("Asia") == 0 || landmarkLocation.compareTo("All") == 0) {
			
		}
	}
	
	public DistanceGameObject getNewQuestion() {
		DistanceGameObject newObject = null;
		int upper = objectsList.size();
		int lower = 0;
		Random r = new Random();
		int index=r.nextInt(upper-lower) + lower;
		newObject = objectsList.remove(index);
		curQuestion = newObject;
		return newObject;
	}
	
	
	
	public double checkAnswer(String userGuess, double userLat, double userLong) {
		double percentError = 0;
		
		 //set location of current question
		Location questionDestination = new Location("");
		questionDestination.setLatitude(curQuestion.getLatitude());
		questionDestination.setLongitude(curQuestion.getLongitude());

		//get distance between user and location
		Location userLocation = new Location("");
		userLocation.setLatitude(userLat);
		userLocation.setLongitude(userLong);
		float correctAnswer = userLocation.distanceTo(questionDestination);
		//convert to Kilometer if current question deals with kilometers
		if(curQuestion.getMeasurement().equals(Measurement.KILOMETER)) {
			correctAnswer /= 1000;
		}
		double userGuessDub = Double.valueOf(userGuess); //TODO put in try catch for invalid strings
		percentError = (((userGuessDub-correctAnswer)/correctAnswer)*100);
		return percentError;
	}
	
	public String getQuestionText() {
		return curQuestion.getQuestionText();
	}
}
