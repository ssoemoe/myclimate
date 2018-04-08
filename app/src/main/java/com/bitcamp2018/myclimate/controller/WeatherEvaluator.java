// David Ahmed

package com.bitcamp2018.myclimate.controller;

public class WeatherEvaluator {

	// package private enums for the faces
	// Hi this is a comment
	public enum faces{
		SAD_FACE, HAPPY_FACE, MEH_FACE;
	}

	public enum precipitation{
		RAINY, CLOUDY, SNOWY, SUNNY;
	}

	public enum range{
		COLD, COOL, WARM, HOT;
	}
	public enum bound{
		LOWER, UPPER;
	}

	public enum system{
		FARHENHEIT, CELCIUS;
	}

	final private precipitation[] precipArr = {precipitation.SNOWY, precipitation.RAINY, precipitation.CLOUDY, precipitation.SUNNY};


	private static WeatherEvaluator weather = null;

	private double lowRange, highRange;

	private boolean is_f = true;

	private WeatherEvaluator() {
		lowRange = 0;
		highRange = 0;
	}

	// since it's a singleton class this makes sure there is only one instance of the class
	public static WeatherEvaluator getInstance() {
		if(weather == null)
			weather = new WeatherEvaluator();
		return weather;
	}

	// Returns the tempreature range the temprature is. system is an enum for either Fahrenheit or Celcius
	public range fromNum(double num, system system) {
		if(system == system.FARHENHEIT) {
			if(num <= 30)
				return range.COLD;
			else if(num <= 50)
				return range.COOL;
			else if(num <= 80)
				return range.WARM;
			else
				return range.HOT;
		}
		else {
			if(num <= -1.11 )
				return range.COLD;
			else if(num <= 10)
				return range.COOL;
			else if(num <= 26.66)
				return range.WARM;
			else
				return range.HOT;
		}
	}


	//converts  celcius to fahrenheit
	void to_f() {
		if(is_f == false) {
			lowRange = ((lowRange * (9.0/5) + 32));
			highRange = ((highRange * (9.0/5) + 32));
			is_f = true;
		}
	}
	
	// returns a temperature converted to celcius
	public double to_f(double c) {
		return c * (9/5) + 32;
	}

	void to_c() {
		if(is_f == true) {
			lowRange =  ((lowRange-32) * (5.0/9));
			highRange =  ((highRange-32) * (5.0/9));
			is_f = false;
		}
	}

	public double to_c(double f) {
		return (f-32) * (5/9);
	}
	
	// returns the current upper or lower bounds
	public double getRange(bound bound) {
		if(bound == bound.UPPER)
			return highRange;
		else
			return lowRange;
	}


	//allows the user to change ranges from cold to warm or hot
	private void changeRange(range range) {
		if(range == range.COLD) {
			lowRange = -999;
			highRange = 30;
			if(is_f == false) {
				is_f = true;
				to_c();
			}
		}
		else if(range == range.COOL) {
			lowRange = 31;
			highRange = 50;
			if(is_f == false) {
				is_f = true;
				to_c();
			}
		}
		else if(range == range.WARM) {
			lowRange = 51;
			highRange = 80;
			if(is_f == false) {
				is_f = true;
				to_c();
			}
		}
		else {
			lowRange = 81;
			highRange = 999 ;
			if(is_f == false) {
				is_f = true;
				to_c();
			}
		}
	}

	// checks if everything is set fo Fahrenheit
	public boolean is_f() {
		return is_f;
	}


	public int getScore(range range, double currTemp) {
		// algorithm for the calculating the temperate score

		double temp = currTemp;
		range result;
		if(is_f() == false)
			result = fromNum(temp, system.CELCIUS);
		else result = fromNum(temp, system.FARHENHEIT);
		
		if(result == range)
			return 6;
		return 0;

	}

	// gets the score from precipitation
	public int getPrecipScore(precipitation prefPrecip, precipitation currPrecip) {
		int score;
		int index = 0;
		int secIndex = 0;
		int difference;
		if(prefPrecip == currPrecip)
			score = 10;
		else {
			for(int i = 0; i < precipArr.length; i++) {
				if(precipArr[i] == prefPrecip)
					index = i;
				else if(precipArr[i] == currPrecip)
					secIndex = i;
			}

			difference = Math.abs(index-secIndex);
			score = 10-(3*difference);
		}

		return score;


	}
	//

	// returns the face
	// currTemp depends whether you're using Farhenheit or not. Use is_f() to determine what you're using
	//combines the temp score and the precip score to get a new score for the faces.
	public faces getWeatherScore(range range, int currTemp, precipitation prefPrecip, precipitation currPrecip) {
		faces face;

		//computes a face based on temperature
		if((getScore(range, currTemp) + getPrecipScore(prefPrecip, currPrecip))/2 >= 7)
			face = faces.HAPPY_FACE;
		else if((getScore(range, currTemp) + getPrecipScore(prefPrecip, currPrecip))/2 >= 4)
			face = faces.MEH_FACE;
		else
			face = faces.SAD_FACE;
		return face;
	}



}
