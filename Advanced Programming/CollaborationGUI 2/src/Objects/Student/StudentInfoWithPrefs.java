package Objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import ObjectiveFunction.ObjectiveFunction;

public class StudentInfoWithPrefs extends StudentInfo{
	private static final long serialVersionUID = -8926031778692761130L;
	private ArrayList<String> preferences = new ArrayList<String>();
	private HashMap<String,Double> objectiveValue = new HashMap<String,Double>();
	private ObjectiveFunction of = new ObjectiveFunction();
	private double averageGrade = 0.0;
	public StudentInfoWithPrefs(StudentInfo stud,String pref1,String pref2,String pref3,String pref4)
			throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		super(stud);
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		preferences.add(pref4);
		calculateAverageGrade();
	}
	
	public StudentInfoWithPrefs(StudentInfo stud,Preferences prefs)
			throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		super(stud);
		preferences.add(prefs.getFirstPreference());
		preferences.add(prefs.getSecondPreference());
		preferences.add(prefs.getThirdPreference());
		preferences.add(prefs.getFourthPreference());
		calculateAverageGrade();
	}
	
	public StudentInfoWithPrefs(ResultSet rs) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, SQLException, NumberFormatException, IDOutOfRangeException {
		super(rs.getInt("ID"),rs.getInt("ProgRank"),rs.getInt("AnRank"),rs.getInt("NetRank"),rs.getInt("WebRank"),rs.getString("PersonalityType").charAt(0),"S"+rs.getString("DifficultStudent1"),"S"+rs.getString("DifficultStudent2"));		
		preferences.add(rs.getString("Pref1"));
		preferences.add(rs.getString("Pref2"));
		preferences.add(rs.getString("Pref3"));
		preferences.add(rs.getString("Pref4"));
		calculateAverageGrade();
	}
	
	public void calculateAverageGrade() {
		double runningGrade = 0.0;
		runningGrade += getProgSkill();
		runningGrade += getAnSkill();
		runningGrade += getNetSkill();
		runningGrade += getWebSkill();
		this.setAverageGrade((runningGrade / 4));
	}
	
	public ArrayList<String> getPreferences() {
		return preferences;
	}
	public String getPreferenceOne() {
		return preferences.get(0);
	}
	public String getPreferenceTwo() {
		return preferences.get(1);
	}
	public String getPreferenceThree() {
		return preferences.get(2);
	}
	public String getPreferenceFour() {
		return preferences.get(3);
	}
	private Integer difficultStudent(int i) {
	
		if(getDifficultStudents().size()<=i) {
			return 0;
		}
		else if (getDifficultStudents().get(i)==null) {
			return 0;
		} else {
		
		return Integer.parseInt(getDifficultStudents().get(i).replace("S", ""));
		}
	}
	
	private String getPreference(int i) {
		return preferences.get(i);
	}
	
	public String sqlFormat() {
		return  Integer.parseInt(getID().replace("S", "")) + "," + (int)getProgSkill() + "," + (int)getAnSkill() + "," + (int)getNetSkill() + "," + (int)getWebSkill() + ",'" + getPersonality().getPersonalityId() + "'," + difficultStudent(0) + "," + difficultStudent(1) + ",'" + getPreference(0) +  "','" + getPreference(1) + "','" + getPreference(2) +  "','" + getPreference(3)+"'"; 
	}
	
	public double getObjectiveValue(String projId) {
		return this.objectiveValue.get(projId);
	}
	
	public void setObjectiveValue(String projId, double objectiveValue) {
		this.objectiveValue.put(projId,objectiveValue);
	}

	public void setOf(ObjectiveFunction of) {
		this.of = of;
	}
	public ObjectiveFunction getOf() {
		return of;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
}
