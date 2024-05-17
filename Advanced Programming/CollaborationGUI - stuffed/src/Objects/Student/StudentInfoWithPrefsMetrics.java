package Objects.Student;

import java.util.HashMap;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;

public class StudentInfoWithPrefsMetrics extends StudentInfoWithPrefs {
	private static final long serialVersionUID = -1170508284218185360L;

	public StudentInfoWithPrefsMetrics(StudentInfoWithPrefs stud)
			throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		super((StudentInfo)stud, stud.getPreferences());
		// TODO Auto-generated constructor stub
	}

	private HashMap<String,Double> objectiveValue = new HashMap<String,Double>();
	
	public double getObjectiveValue(String projId) {
		return this.objectiveValue.get(projId);
	}
	
	public void setObjectiveValue(String projId, double objectiveValue) {
		this.objectiveValue.put(projId,objectiveValue);
	}
	
}
