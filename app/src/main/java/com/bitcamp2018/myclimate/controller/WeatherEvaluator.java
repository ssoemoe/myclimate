import java.util.Math;

public enum EmojiFace {
	SAD_FACE,
	MEH_FACE,
	HAPPY_FACE
}

public class WeatherEvaluator {

	public static EmojiFace evaluateWeatherFromObs(int prefTemp, int curTemp, int partValue) {
		int pointDeduction = Math.abs(prefTemp - curTemp) / partValue;
		int finalScore = 10 - pointDeduction;
		if(finalScore >= 7) {
			return HAPPY_FACE;
		}
		else if(finalScore >= 4 && finalScore < 7) {
			return MEH_FACE;
		}
		else {
			return SAD_FACE;
		}
	}

}