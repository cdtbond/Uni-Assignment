package SharedValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Helpers.StandardDeviation;
import Objects.Project.Team;

public class StandardDeviationValues {
	private double sdGettingFirstNSecondPref = 0.0;
	private double sdSkillCompetency = 0.0;
	private double sdShortfall = 0.0;
	private static StandardDeviationValues _instance;
	private StandardDeviationValues() {}
	public static synchronized StandardDeviationValues getSingleton() {
		if(_instance == null) {
			_instance = new StandardDeviationValues();
		}
		return _instance;
	}
	
	
	@FunctionalInterface
	interface MyInterface{ 
		double display(ArrayList<Double> numbers);
	}
	
	
	//standard deviation percent members getting first and second project preferences
	public void computeSDGettingFirstNSecondPref(HashMap<String,Team> teams) {
		ArrayList<Double> percentages = new ArrayList<Double>();
		for(Map.Entry entry : teams.entrySet()) {
			percentages.add(((Team) entry.getValue()).getFirstAndSecondPrefPercent());
		}
		StandardDeviation obj = new StandardDeviation();
		MyInterface ref = obj::stDev; 
		sdGettingFirstNSecondPref = ref.display(percentages);
	}
	//standard deviation skill shortfall
	public void computeSDSkillShortfall(HashMap<String,Team> teams) {
		ArrayList<Double> percentages = new ArrayList<Double>();
		for(Map.Entry entry : teams.entrySet()) {
			percentages.add(((Team) entry.getValue()).getSkillShortfall());
		}
		StandardDeviation obj = new StandardDeviation();
		MyInterface ref = obj::stDev; 
		sdShortfall = ref.display(percentages);
	}
	//standard deviation skill competency
	public void computeSdSkillCompetency(HashMap<String,Team> teams) {
		ArrayList<Double> percentages = new ArrayList<Double>();
		for(Map.Entry entry : teams.entrySet()) {
			percentages.add(((Team) entry.getValue()).getAverageSkillCompetency());
		}
		StandardDeviation obj = new StandardDeviation();
		MyInterface ref = obj::stDev; 
		sdSkillCompetency = ref.display(percentages);
	}
	
	public double getSdShortfall() {
		return sdShortfall;
	}
	public double getSdSkillCompetency() {
		return sdSkillCompetency;
	}
	public double getSdGettingFirstNSecondPref() {
		return sdGettingFirstNSecondPref;
	}
	
	
	
}
