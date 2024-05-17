package SharedValues;

import java.util.HashMap;
import java.util.Map;

import Objects.Student.StudentInfoWithPrefs;

public class AverageGrade {


	private double averageGrade = 0.0;
	
	private static AverageGrade _instance;
	private AverageGrade() {}
	public static synchronized AverageGrade getSingleton() {
		if(_instance == null) {
			_instance = new AverageGrade();
		}
		return _instance;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double grade) {
		this.averageGrade = grade;
	}

	public void calcAndSetAverageGrade(HashMap<String, StudentInfoWithPrefs> studentInfoWPrefs) {
		double runningGrade = 0.0;
		for(Map.Entry entry : studentInfoWPrefs.entrySet()) {
			runningGrade = (((StudentInfoWithPrefs) entry.getValue()).getAverageGrade());
		}
		runningGrade = runningGrade / studentInfoWPrefs.size();
		averageGrade = runningGrade;
	}

}
