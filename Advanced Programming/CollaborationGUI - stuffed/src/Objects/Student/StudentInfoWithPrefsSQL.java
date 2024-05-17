package Objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Objects.SQL;

public class StudentInfoWithPrefsSQL extends StudentInfoWithPrefs implements SQL {

	private static final long serialVersionUID = -3936274475280995970L;

	public StudentInfoWithPrefsSQL(ResultSet rs) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, SQLException, DuplicatePreferenceException, IDOutOfRangeException {
		super(new StudentInfo("S" + rs.getInt("ID"),rs.getInt("ProgRank"),rs.getInt("AnRank"),rs.getInt("NetRank"),rs.getInt("WebRank"),rs.getString("PersonalityType").charAt(0),"S"+rs.getString("DifficultStudent1"),"S"+rs.getString("DifficultStudent2")),rs.getString("Pref1"),rs.getString("Pref2"),rs.getString("Pref3"),rs.getString("Pref4"));		
	}

	
	
	public StudentInfoWithPrefsSQL(StudentInfoWithPrefs studentInfoWithPrefs) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, DuplicatePreferenceException, IDOutOfRangeException {
		super(new StudentInfo(studentInfoWithPrefs.getID(),Integer.getInteger(studentInfoWithPrefs.getProgSkill()+""),Integer.getInteger(studentInfoWithPrefs.getAnSkill()+""),Integer.getInteger(studentInfoWithPrefs.getNetSkill()+""), Integer.getInteger(studentInfoWithPrefs.getWebSkill()+""),studentInfoWithPrefs.getPersonality().getPersonalityId(),studentInfoWithPrefs.getDifficultStudents().get(0),studentInfoWithPrefs.getDifficultStudents().get(1)),studentInfoWithPrefs.getPreferenceOne(),studentInfoWithPrefs.getPreferenceTwo(),studentInfoWithPrefs.getPreferenceThree(),studentInfoWithPrefs.getPreferenceFour());		
	}
	



	@Override
	public String getSQL() {
			return  Integer.parseInt(getID().replace("S", "")) + "," + (int)getProgSkill() + "," + (int)getAnSkill() + "," + (int)getNetSkill() + "," + (int)getWebSkill() + ",'" + getPersonality().getPersonalityId() + "'," + difficultStudent(0) + "," + difficultStudent(1) + ",'" + getPreference(0) +  "','" + getPreference(1) + "','" + getPreference(2) +  "','" + getPreference(3)+"'"; 
	}

}
