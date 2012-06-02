package edu.osu.forevermetric;

/**
 * This class is used to create a Conversion object 
 * @author Zach
 */
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
	
	/**
	 * This method is called in ConversionGame.java used to get the 
	 * correct matrix value
	 * @return integer representing the proper row/column in the matrix
	 */
	public int getQMeasurement() {
		int result = 0;
		// These values are used in
		if(Qmeasurement == Measurement.METER){
			result = 0;
		} else if(Qmeasurement == Measurement.KILOMETER) {
			result = 1;
		} else if(Qmeasurement == Measurement.MILLIMETER) {
			result = 2;
		} else if(Qmeasurement == Measurement.INCH) {
			result = 3;
		} else if(Qmeasurement == Measurement.FOOT) {
			result = 4;
		}  else if(Qmeasurement == Measurement.MILE) {
			result = 5;
		}	
		return result;
	}
	
	/**
	 * This method is called in ConversionGame.java used to get the 
	 * correct matrix value
	 * @return integer representing the proper row/column in the matrix
	 */
	public int getAMeasurement() {
		int result = 0;
		if(Ameasurement == Measurement.METER){
			result = 0;
		} else if(Ameasurement == Measurement.KILOMETER) {
			result = 1;
		} else if(Ameasurement == Measurement.MILLIMETER) {
			result = 2;
		} else if(Ameasurement == Measurement.INCH) {
			result = 3;
		} else if(Ameasurement == Measurement.FOOT) {
			result = 4;
		}  else if(Ameasurement == Measurement.MILE) {
			result = 5;
		}
		return result;
	}
	
	/**
	 * This method returns a string value of 'm' 'km' 'miles' 'in' or 'ft'
	 * @return String representation of the question's units
	 */
	public String getQUnits() {
		String result = "";
		if(Qmeasurement == Measurement.METER){
			result = "m";
		} else if(Qmeasurement == Measurement.KILOMETER) {
			result = "km";
		}else if(Qmeasurement == Measurement.MILLIMETER) {
			result = "mm";
		} else if(Qmeasurement == Measurement.INCH) {
			result = "in";
		} else if(Qmeasurement == Measurement.FOOT) {
			result = "ft";
		}  else if(Qmeasurement == Measurement.MILE) {
			result = "miles";
		}
		return result;
	}
	
	/**
	 * This method returns a string value of 'm' 'km' 'miles' 'in' or 'ft'
	 * @return String representation of the answer's units
	 */
	public String getAUnits() {
		String result = "";
		if(Ameasurement == Measurement.METER){
			result = "m";
		} else if(Ameasurement == Measurement.KILOMETER) {
			result = "km";
		} else if(Ameasurement == Measurement.MILLIMETER) {
			result = "mm";
		} else if(Ameasurement == Measurement.INCH) {
			result = "in";
		} else if(Ameasurement == Measurement.FOOT) {
			result ="ft";
		}  else if(Ameasurement == Measurement.MILE) {
			result = "miles";
		}
		return result;
	}
	
	/**
	 * This gives the String value of the question number
	 * @return String representation of the random question number
	 */
	public String getQuestionValue() {
		return Qvalue;
	}
}