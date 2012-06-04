package edu.osu.forevermetric;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

/**
 * This class is for the conversion game where the questions are created and the 
 * random values are set for the display.
 * @author Zach
 */
public class ConversionGame {
	
	private ArrayList<ConversionGameObject> objectsList;
	private ConversionGameObject curQuestion;
	ArrayList<String> MSquestionVals = new ArrayList<String>();
	ArrayList<String> HSquestionVals = new ArrayList<String>();
	private Double[][] conversionArray;
	
	int count = 1;
	int numOfQuestions = 10;
	int MSnumbers = 1000;
	int HSnumbers = 10000;
	// used for conversion array
	int m = 0, km = 1, mm = 2;
	int in = 3, ft = 4, mile = 5;

	
	ConversionGame(String gradeLevel) {
		// setup the conversion array
		// 			m			km 			mm			in 			ft 			mile 
		// 		------------   ----------  ---------- --------   ---------   ----------
		// m	|	0			.001		1000		39.37		3.28		0.00062137
		// km	|	1000		0			1000000		39370.08	3280.84		0.6213711
		// mm	|	.001		1.0x10-6	0			0.03937		0.003281	6.2137×10-7
		// in	|	0.0254		2.54×10-5	25.4		0			0.08333		1.5782×10-5
		// ft	|	0.3048		0.0003048	304.8		12			0			0.00018939
		// mile	|	1609.344	1.609344	1609344		63360		5280		0
		conversionArray = new Double[6][6];
		conversionArray[m][m] = 0.0; conversionArray[m][km] = 0.001; conversionArray[m][mm] = 1000.0;
		conversionArray[m][in] = 39.37; conversionArray[m][ft] = 3.28; conversionArray[m][mile] = 0.00062137;
		
		conversionArray[km][m] = 1000.0; conversionArray[km][km] = 0.0; conversionArray[km][mm] = 1000000.0; 
		conversionArray[km][in] = 39370.08; conversionArray[km][ft] = 3280.8399; conversionArray[km][mile] = 0.621371192;
		
		conversionArray[mm][m] = 0.001; conversionArray[mm][km] = 0.000001; conversionArray[mm][mm] = 0.0;
		conversionArray[mm][in] = 0.03937; conversionArray[mm][ft] = 0.003281; conversionArray[mm][mile] = 0.0000006214;
		
		conversionArray[in][m] = 0.0254; conversionArray[in][km] = 0.0000254; conversionArray[in][mm] = 25.4;
		conversionArray[in][in] = 0.0; conversionArray[in][ft] = 0.0833333; conversionArray[in][mile] = 0.000015782;
		
		conversionArray[ft][m] = 0.3048; conversionArray[ft][km] = 0.0003048; conversionArray[ft][mm] = 304.8;
		conversionArray[ft][in] = 12.0; conversionArray[ft][ft] = 0.0; conversionArray[ft][mile] = 0.00018939; 
		
		conversionArray[mile][m] = 1609.344; conversionArray[mile][km] = 1.609344; conversionArray[mile][mm] = 1609344.0;
		conversionArray[mile][in] = 63360.0; conversionArray[mile][ft] = 5280.0; conversionArray[mile][mile] = 0.0;
		
		
		
		// get the random numbers to be put into the arrayList
		Random rand = new Random();
		
		objectsList = new ArrayList<ConversionGameObject>();
		if(gradeLevel.compareTo("MiddleSchool") == 0) {
			// Populate the array with values for questions
			for(int i=1;i<numOfQuestions; i++) {
				MSquestionVals.add(Double.toString(roundTwoDecimals(rand.nextDouble() * MSnumbers)));
			}
			
			ConversionGameObject tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(0)+ " millimeters into feet",
					Measurement.MILLIMETER, MSquestionVals.get(0), Measurement.FOOT); objectsList.add(tempObject);
					
			tempObject = new ConversionGameObject("The diameter of the Earth is 7,926.41 miles. How many kilometers is that?",
					Measurement.MILE, "7926.41", Measurement.KILOMETER);	objectsList.add(tempObject);
					
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(1)+ " inches into feet",
					Measurement.INCH, MSquestionVals.get(1), Measurement.FOOT); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(2)+ " feet into miles",
					Measurement.FOOT, MSquestionVals.get(2), Measurement.MILE);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(3)+ " inches into miles",
					Measurement.INCH, MSquestionVals.get(3), Measurement.MILE);	objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(4)+ " feet into inches",
					Measurement.FOOT, MSquestionVals.get(4), Measurement.INCH);	objectsList.add(tempObject);
					
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(4)+ " millimeters into kilometers",
					Measurement.MILLIMETER, MSquestionVals.get(4), Measurement.KILOMETER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +MSquestionVals.get(5)+ " millimeters into meters",
					Measurement.MILLIMETER, MSquestionVals.get(5), Measurement.METER); objectsList.add(tempObject);		
			
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
					
			tempObject = new ConversionGameObject("The average radius of the moon is 1,737,100,000 mm. How many kilometers is that?",
					Measurement.MILLIMETER, "1737100000", Measurement.KILOMETER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("If the average radius of the moon is 1,737.1 km. What is the diameter in miles?",
					Measurement.KILOMETER, "1737.1", Measurement.MILE); objectsList.add(tempObject);
					
			tempObject = new ConversionGameObject("A penny is 1.55mm thick. How many inches is that?",
					Measurement.MILLIMETER, "1.55", Measurement.INCH);	objectsList.add(tempObject);
		
			tempObject = new ConversionGameObject("A penny has a diameter of  0.750 inches. How many millimters is that?",
					Measurement.INCH, "0.750", Measurement.MILLIMETER);	objectsList.add(tempObject);
					
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(0)+ " inches into millimeter",
					Measurement.INCH, HSquestionVals.get(0), Measurement.MILLIMETER); objectsList.add(tempObject);

			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(1)+ " millimeters into inches",
					Measurement.MILLIMETER, HSquestionVals.get(1), Measurement.INCH); objectsList.add(tempObject);		
			
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(2)+ " meters into miles",
					Measurement.METER, HSquestionVals.get(2), Measurement.MILE); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("Convert " +HSquestionVals.get(3)+ " millimeters into feet",
					Measurement.MILLIMETER,HSquestionVals.get(3), Measurement.FOOT); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many meters are in " +HSquestionVals.get(4)+ " miles?",
					Measurement.MILE, HSquestionVals.get(4), Measurement.METER); objectsList.add(tempObject);
			
			tempObject = new ConversionGameObject("How many feet are in " +HSquestionVals.get(5)+ " kilometers?",
					Measurement.KILOMETER, HSquestionVals.get(5), Measurement.FOOT); objectsList.add(tempObject);
			
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
	
	public String getAnswerUnits() {
		String result = curQuestion.getAUnits();
		return result;
	}
	
	public String getQuestionUnits() {
		String result = curQuestion.getQUnits();
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
