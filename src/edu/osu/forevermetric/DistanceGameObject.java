package edu.osu.forevermetric;

public class DistanceGameObject {
	private String question;
	private Measurement measurement;
	private double latitude;
	private double longitude;
	
	DistanceGameObject(String fullQuestion, Measurement measure, double lat, double longi) {
		question = fullQuestion;
		measurement = measure;
		latitude = lat;
		longitude = longi;
	}
	
	
	public String getLocationName() {
		return question;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public Measurement getMeasurement() {
		return measurement;
	}
}
