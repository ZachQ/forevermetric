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
			tempObject = new DistanceGameObject("How many kilometers are you from The Empire State Building?", Measurement.KILOMETER, 40.748433, -73.985656);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from Mount Rushmore?", Measurement.KILOMETER, 43.878947, -103.459825);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from the Space Needle?", Measurement.KILOMETER, 47.6204, -122.3491);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from Niagara Falls?", Measurement.KILOMETER, 43.08, -79.071);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from The White House?", Measurement.KILOMETER, 38.897669, -77.03655);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from The Liberty Bell?", Measurement.KILOMETER, 39.949486, -75.150294);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from The Golden Gate bridge", Measurement.KILOMETER, 37.819722, -122.478611);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from The Statue of Liberty", Measurement.KILOMETER, 40.689167, -74.044444);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from Hoover Dam", Measurement.KILOMETER, 36.015556, -114.737778);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many kilometers are you from The Gateway Arch", Measurement.KILOMETER, 38.62452, -90.18471);
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
	
	
	
	public double getAnswer(double userLat, double userLong) {
		
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
		return correctAnswer;
	}
	
	public String getQuestionText() {
		return curQuestion.getQuestionText();
	}
}
