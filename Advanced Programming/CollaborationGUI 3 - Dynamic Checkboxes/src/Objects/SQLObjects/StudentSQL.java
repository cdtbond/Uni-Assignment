package Objects.SQLObjects;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Objects.SQL;
import Objects.Student.Preferences;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;

public class StudentSQL extends StudentInfoWithPrefs implements SQL {

	private static final long serialVersionUID = 3393319331053908348L;
	public StudentSQL(ResultSet rs) throws IncorrectPersonalityTypeException, IncorrectGradeException,
			IncorrectRangeException, SQLException, NumberFormatException, IDOutOfRangeException {
			super(new StudentInfo(rs.getInt("ID"),rs.getInt("ProgRank"),rs.getInt("AnRank"),rs.getInt("NetRank"),rs.getInt("WebRank"),rs.getString("PersonalityType").charAt(0),"S"+rs.getString("DifficultStudent1"),"S"+rs.getString("DifficultStudent2")),rs.getString("Pref1"),rs.getString("Pref2"),rs.getString("Pref3"),rs.getString("Pref4"));

	}
	public StudentSQL(StudentInfoWithPrefs s) throws NumberFormatException, IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException, DuplicatePreferenceException {
		super(new StudentInfo(Integer.parseInt(s.removePrefix()),(int)s.getProgSkill(),(int)s.getAnSkill(),(int)s.getNetSkill(),(int)s.getWebSkill(),s.getPersonality().getPersonalityId()),new Preferences(s.getID(),s.getPreferenceOne(),s.getPreferenceTwo(),s.getPreferenceThree(),s.getPreferenceFour()));
	}
	@Override
	public String getSQL() {
		return  Integer.parseInt(getID().replace("S", "")) + "," + (int)getProgSkill() + "," + (int)getAnSkill() + "," + (int)getNetSkill() + "," + (int)getWebSkill() + ",'" + getPersonality().getPersonalityId() + "'," + difficultStudent(0) + "," + difficultStudent(1) + ",'" + getPreference(0) +  "','" + getPreference(1) + "','" + getPreference(2) +  "','" + getPreference(3)+"'"; 
	}
}
