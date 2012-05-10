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
			DistanceGameObject tempObject = new DistanceGameObject("How many Kilometers are you from The Eiffel Tower?", Measurement.KILOMETER, 48.8583, 2.2945);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Eiffel Tower?", Measurement.KILOMETER, 48.8583, 2.2945);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Colosseum?", Measurement.KILOMETER, 41.890169, 12.492269);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Big Ben?", Measurement.KILOMETER, 51.500611, -0.124611);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Leaning Tower of Pisa?", Measurement.KILOMETER, 43.722944, 10.396611);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Pantheon?", Measurement.KILOMETER, 37.971421, 23.726166);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Stonehenge?", Measurement.KILOMETER, 51.178844, -1.826189);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Buckingham Palace?", Measurement.KILOMETER, 51.501, -0.142);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Windsor Castle", Measurement.KILOMETER, 51.483889, -0.604444);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Sistine Chapel?", Measurement.KILOMETER, 41.903056, 12.454444);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Grand Canal in Venice?", Measurement.KILOMETER, 45.436667, 12.333056);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Neuschwanstein Castle?", Measurement.KILOMETER, 47.5575, 10.75);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Pompeii?", Measurement.KILOMETER, 40.751, 14.487);
			objectsList.add(tempObject);
		} if(landmarkLocation.compareTo("Asia") == 0 || landmarkLocation.compareTo("All") == 0) {
			DistanceGameObject tempObject = new DistanceGameObject("How many Kilometers are you from The Taj Mahal?", Measurement.KILOMETER, 27.174167, 78.042222);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Angkor Wat?", Measurement.KILOMETER, 13.4125, 103.866667);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Forbidden City?", Measurement.KILOMETER, 39.914722, 116.390556);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Temple of Heaven?", Measurement.KILOMETER, 39.882242, 116.406469);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Potala Palace?", Measurement.KILOMETER, 29.657778, 91.116944);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Mount Tai?", Measurement.KILOMETER, 36.255833, 117.1075);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Burj Khalifa?", Measurement.KILOMETER, 25.197139, 55.274111);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Mount Fuji?", Measurement.KILOMETER, 35.358, 138.731);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from The Petronas Towers?", Measurement.KILOMETER, 3.15785, 101.71165);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from Bangkok?", Measurement.KILOMETER, 13.752222, 100.493889);
			objectsList.add(tempObject);
			tempObject = new DistanceGameObject("How many Kilometers are you from beijing?", Measurement.KILOMETER, 39.913889, 116.391667);
			objectsList.add(tempObject);
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
	
	
	public String[] getQuestionLocation() {
		String[] coordinates = new String[2];
		coordinates[0] = String.valueOf(curQuestion.getLatitude());
		coordinates[1] = String.valueOf(curQuestion.getLongitude());
		return coordinates;
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
		} else if(curQuestion.getMeasurement().equals(Measurement.MILE)) {
			correctAnswer *= 0.00062137119;
		}
		return correctAnswer;
	}
	
	public String getQuestionText() {
		return curQuestion.getQuestionText();
	}
}
