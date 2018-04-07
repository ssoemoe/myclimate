package com.bitcamp2018.myclimate.controller;// David Ahmed


public class WeatherEvaluator {

	// package private enums for the faces
	enum faces{
		SAD_FACE, HAPPY_FACE, MEH_FACE, HORROR_FACE
	}
	
	private  faces face;

	private  int score;
	
	private static WeatherEvaluator weather = null;
	
	private WeatherEvaluator() {
		face = null;
		score = 0;
	}

	// since it's a singleton class this makes sure there is only one instance of the class
	 static WeatherEvaluator getInstance() {
		if(weather == null)
			weather = new WeatherEvaluator();
		return weather;
	}
	
	int getScore(int prefTemp, int currTemp, int partition) {
		// algorithm for the calculating the score
		score = 10-(Math.abs(prefTemp-currTemp)/partition);
		if(score < 0)
			score = 0;
		return score;
	}

	// returns the face
	faces getWeatherScore(int prefTemp, int currTemp, int partition) {

		if(getScore(prefTemp, currTemp, partition) >= 7)
			face = faces.HAPPY_FACE;
		else if(getScore(prefTemp, currTemp, partition) >= 4)
			face = faces.MEH_FACE;
		else if(getScore(prefTemp, currTemp, partition) > 0)
			face = faces.SAD_FACE;
		else
			face = faces.HORROR_FACE;

		return face;



	}
}
