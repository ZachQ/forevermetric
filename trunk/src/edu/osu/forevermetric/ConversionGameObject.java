package edu.osu.forevermetric;

public class ConversionGameObject {
	private String question;
	private Measurement measurement;
	
	ConversionGameObject(String fullQuestion, Measurement measure) {
		question = fullQuestion;
		measurement = measure;
	}
	
	public String getQuestionText() {
		return question;
	}
	
	public Measurement getMeasurement() {
		return measurement;
	}
}