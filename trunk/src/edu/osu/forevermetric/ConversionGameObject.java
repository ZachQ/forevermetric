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
	
	public int getQMeasurement() {
		int result = (Integer) null;
		// These values are used in
		if(Qmeasurement == Measurement.METER){
			result = 0;
		} else if(Qmeasurement == Measurement.KILOMETER) {
			result = 1;
		} else if(Qmeasurement == Measurement.INCH) {
			result = 2;
		} else if(Qmeasurement == Measurement.FOOT) {
			result = 3;
		}  else if(Qmeasurement == Measurement.MILE) {
			result = 4;
		}	
		return result;
	}
	
	public int getAMeasurement() {
		int result = (Integer) null;
		if(Ameasurement == Measurement.METER){
			result = 0;
		} else if(Ameasurement == Measurement.KILOMETER) {
			result = 1;
		} else if(Ameasurement == Measurement.INCH) {
			result = 2;
		} else if(Ameasurement == Measurement.FOOT) {
			result = 3;
		}  else if(Ameasurement == Measurement.MILE) {
			result = 4;
		}
		return result;
	}
	
	public String getQuestionValue() {
		return Qvalue;
	}
}