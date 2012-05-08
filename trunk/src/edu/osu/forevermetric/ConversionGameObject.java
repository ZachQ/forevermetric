package edu.osu.forevermetric;

public class ConversionGameObject {
	private String question, Qvalue;
	private Measurement Qmeasurement, Ameasurement;
	
	ConversionGameObject(String fullQuestion, Measurement questionMeasure, String questionValue, Measurement answerMeasure) {
		question = fullQuestion;
		Qmeasurement = questionMeasure;
		Ameasurement = answerMeasure;
		Qvalue = questionValue;
	}
	
	public String getQuestionText() {
		return question;
	}
	
	public Measurement getQMeasurement() {
		return Qmeasurement;
	}
	
	public Measurement getAMeasurement() {
		return Ameasurement;
	}
	
	public String getQuestionValue() {
		return Qvalue;
	}
}