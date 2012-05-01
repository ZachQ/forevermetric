package edu.osu.forevermetric;

import java.util.ArrayList;
import java.util.Random;

public class DistanceGame {
	ArrayList<DistanceGameObject> objectsList;
	
	DistanceGame(String landmarkLocation) {
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
		//TODO havent tested to see if this is correct bounds
		int index=r.nextInt(upper-lower) + lower;
		newObject = objectsList.remove(index);
		return newObject;
	}
	
	
	
	
}
