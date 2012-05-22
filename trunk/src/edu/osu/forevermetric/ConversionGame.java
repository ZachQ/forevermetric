package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class ConversionGame {
	
	private ArrayList<ConversionGameObject> objectsList;
	private ConversionGameObject curQuestion;
	ArrayList<String> MSquestionVals = new ArrayList<String>();
	ArrayList<String> HSquestionVals = new ArrayList<String>();
	private Double[][] conversionArray;
	
	int count = 1;
	int numOfQuestions = 10;
	int MSnumbers = 10000;
	int HSnumbers = 10000000;
	// used for conversion array
	int m = 0; int km = 1; int in = 2;
	int ft = 3; int mile = 4;

	
	ConversionGame(String gradeLevel) {
		// setup the conversion array
		// 			m			km 				in 			ft 			mile 
		// 		------------   -------------   ---------   ---------   ----------
		// m	|	0			.001			39.37		3.28		0.00062137
		// km	|	1000		0				39370.08	3280.84		0.6213711
		// in	|	0.0254		2.54 × 10-5		0			0.08333		1.5782 × 10-5
		// ft	|	0.3048		0.0003048		12			0			0.00018939
		// mile	|	1609.344	1.609344		63360		5280		0
		conversionArray = new Double[5][5];
		conversionArray[m][m] = 0.0; conversionArray[m][km] = 0.001; conversionArray[m][in] = 39.37;
		conversionArray[m][ft] = 3.28; conversionArray[m][mile] = 0.00062137; conversionArray[km][m] = 1000.0;
		conversionArray[km][km] = 0.0; conversionArray[km][in] = 39370.08; conversionArray[km][ft] = 3280.8399;
		conversionArray[km][mile] = 0.0; conversionArray[in][m] = 0.0254; conversionArray[in][km] = 0.0000254;
		conversionArray[in][in] = 0.0; conversionArray[in][ft] = 0.0833333; conversionArray[in][m] = 0.000015782;
		conversionArray[ft][m] = 0.3048; conversionArray[ft][km] = 0.0003048; conversionArray[ft][in] = 12.0;
		conversionArray[ft][ft] = 0.0; conversionArray[ft][mile] = 0.00018939; conversionArray[mile][m] = 1609.344;
		conversionArray[mile][km] = 1.609344; conversionArray[mile][in] = 63360.0; conversionArray[mile][ft] = 5280.0;
		conversionArray[mile][mile] = 0.0;
		
		
		
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
		int Qmes = curQuestion.getQMeasurement();
		int Ames = curQuestion.getAMeasurement();
		
		result = Double.valueOf(value) * conversionArray[Qmes][Ames];

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
