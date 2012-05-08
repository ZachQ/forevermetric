package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class ConversionGame {
	
	private ArrayList<ConversionGameObject> objectsList;
	private ConversionGameObject curQuestion;
	ArrayList<String> MSquestionVals = new ArrayList<String>();
	ArrayList<String> HSquestionVals = new ArrayList<String>();
	
	int count = 1;
	int numOfQuestions = 10;
	int MSnumbers = 10000;
	int HSnumbers = 10000000;

	
	ConversionGame(String gradeLevel) {
		// get the random numbers to be put into the arrayList
		Random rand = new Random();
		
		objectsList = new ArrayList<ConversionGameObject>();
		if(gradeLevel.compareTo("MiddleSchool") == 0) {
			// Populate the array with values for questions
			for(int i=1;i<numOfQuestions; i++) {
				MSquestionVals.add(Double.toString(roundTwoDecimals(rand.nextDouble() * MSnumbers)));
			}
			
			ConversionGameObject tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(0)+ " miles into feet",
					Measurement.MILE, MSquestionVals.get(0), Measurement.FOOT); objectsList.add(tempObject);

			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(1)+ " inches into feet",
					Measurement.INCH, MSquestionVals.get(1), Measurement.FOOT); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(2)+ " feet into miles",
					Measurement.FOOT, MSquestionVals.get(2), Measurement.MILE);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(3)+ " inches into miles",
					Measurement.INCH, MSquestionVals.get(3), Measurement.MILE);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(4)+ " feet into inches",
					Measurement.FOOT, MSquestionVals.get(4), Measurement.INCH);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many meters are in " +MSquestionVals.get(5)+ " kilometers?",
					Measurement.KILOMETER, MSquestionVals.get(5), Measurement.METER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many kilometers are in " +MSquestionVals.get(6)+ " meters?",
					Measurement.METER, MSquestionVals.get(6), Measurement.KILOMETER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many feet are in " +MSquestionVals.get(7)+ " miles?",
					Measurement.MILE, MSquestionVals.get(7), Measurement.FOOT);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many feet are in " +MSquestionVals.get(8)+ " meters?",
					Measurement.METER, MSquestionVals.get(8), Measurement.FOOT); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many miles are in " +MSquestionVals.get(8)+ " kilometers?",
					Measurement.KILOMETER, MSquestionVals.get(8), Measurement.MILE); objectsList.add(tempObject);
		} if(gradeLevel.compareTo("HighSchool") == 0) {
			// Populate the array with values for questions
			for(int i=0;i<numOfQuestions; i++) {
				HSquestionVals.add(Double.toString(roundTwoDecimals(rand.nextDouble() * HSnumbers)));
			}
			
			ConversionGameObject tempObject = new ConversionGameObject("The moon is on average, 238,857 miles from Earth. How many kilometers is that?", 
					Measurement.MILE, "238857", Measurement.KILOMETER);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("The moon is on average, 238,857 miles from Earth. How many inches is that?",
					Measurement.MILE, "238857", Measurement.INCH);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(0)+ " miles into feet",
					Measurement.MILE, HSquestionVals.get(0), Measurement.FOOT);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(1)+ " kilometers into feet",
					Measurement.KILOMETER, HSquestionVals.get(1), Measurement.FOOT); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(2)+ " meters into miles",
					Measurement.METER, HSquestionVals.get(2), Measurement.MILE); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(3)+ " meters into miles",
					Measurement.METER,HSquestionVals.get(3), Measurement.MILE); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many meters are in " +HSquestionVals.get(4)+ " miles?",
					Measurement.MILE, HSquestionVals.get(4), Measurement.METER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many feet are in " +HSquestionVals.get(5)+ " kilometers?",
					Measurement.KILOMETER, HSquestionVals.get(5), Measurement.FOOT); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many inches are in " +HSquestionVals.get(6)+ " kilometers?",
					Measurement.KILOMETER, HSquestionVals.get(6), Measurement.INCH); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many meters are in " +HSquestionVals.get(7)+ " inches?",
					Measurement.INCH, HSquestionVals.get(7), Measurement.METER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many kilometers are in " +HSquestionVals.get(8)+ " feet?",
					Measurement.FOOT, HSquestionVals.get(8), Measurement.KILOMETER); objectsList.add(tempObject);
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
		double result = 1;
		String value = curQuestion.getQuestionValue();
		switch(curQuestion.getQMeasurement()) {
			case METER:
				//convert meters into AnswerMeasurement
				switch(curQuestion.getAMeasurement()) {
					case KILOMETER:
						result = Double.valueOf(value) / 1000;
						break;
					case INCH:
						result = Double.valueOf(value) * 39.3700787;
						break;
					case FOOT:
						result = Double.valueOf(value) * 3.2808399;
						break;
					case MILE:
						result = Double.valueOf(value) * 0.00062137119;
						break;
					default:
						//do nothing
						break;
				}
				break; // break METER
			case KILOMETER:
				// convert kilometers into Answer measurement
				switch(curQuestion.getAMeasurement()) {
					case METER:
						result = Double.valueOf(value) * 1000;
						break;
					case INCH:
						result = Double.valueOf(value) * 39370.0787;
						break;
					case FOOT:
						result = Double.valueOf(value) * 3280.8399;
						break;
					case MILE:
						result = Double.valueOf(value) * 0.621371192;
						break;
					default:
						//do nothing
						break;
				}
				break; // break KILOMETER
			case INCH:
				// convert inches into Answer measurement
				switch(curQuestion.getAMeasurement()) {
					case METER:
						result = Double.valueOf(value) * 0.0254;
						break;
					case KILOMETER:
						result = Double.valueOf(value) * 0.0000254;
						break;
					case FOOT:
						result = Double.valueOf(value) * 0.0833333333;
						break;
					case MILE:
						result = Double.valueOf(value) * 0.0000157828283;
						break;
					default:
						//do nothing
						break;
				}
				break; // break INCH
			case FOOT:
				// convert feet into Answer measurement
				switch(curQuestion.getAMeasurement()) {
					case METER:
						result = Double.valueOf(value) * 0.3048;
						break;
					case KILOMETER:
						result = Double.valueOf(value) * 0.0003048;
						break;
					case INCH:
						result = Double.valueOf(value) * 12;
						break;
					case MILE:
						result = Double.valueOf(value) * 0.000189393939;
						break;
					default:
						//do nothing
						break;
				}
				break; // break FOOT
			case MILE:
				// convert miles into Answer measurement
				switch(curQuestion.getAMeasurement()) {
					case METER:
						result = Double.valueOf(value) * 1609.344;
						break;
					case KILOMETER:
						result = Double.valueOf(value) * 1.609344;
						break;
					case INCH:
						result = Double.valueOf(value) * 63360;
						break;
					case FOOT:
						result = Double.valueOf(value) * 5280;
						break;
					default:
						//do nothing
						break;
				}
				break; // break MILE
			default :
				// do nothing
				break;
		}
		return result;
	}
	
	public String getQuestionText() {
		return curQuestion.getQuestionText();
	}
	
	private double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
}
