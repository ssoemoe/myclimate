package com.bitcamp2018.myclimate.controller;

public class WeatherEvaluator {

	public static String evaluateWeatherFromObs(int prefTemp, int curTemp, int partValue) {
		int pointDeduction = Math.abs(prefTemp - curTemp) / partValue;
		int finalScore = 10 - pointDeduction;
		if(finalScore >= 7) {
			return "happy";
		}
		else if(finalScore >= 4 && finalScore < 7) {
			return "ok";
		}
		else {
			return "sad";
		}
	}

}