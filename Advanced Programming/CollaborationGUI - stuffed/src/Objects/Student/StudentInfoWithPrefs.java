package Objects.Student;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;

public class StudentInfoWithPrefs extends StudentInfo{
	private static final long serialVersionUID = -8926031778692761130L;
	private Preferences preferences;
	
	public StudentInfoWithPrefs(StudentInfo stud,String pref1,String pref2,String pref3,String pref4)
			throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, DuplicatePreferenceException, IDOutOfRangeException {
		super(stud);
		preferences = new Preferences(stud.getID(),pref1,pref2,pref3,pref4);
	}
	
	public StudentInfoWithPrefs(StudentInfo stud,Preferences prefs)
			throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		super(stud);
		this.preferences = prefs;
	}
	
	
	public Preferences getPreferences() {
		return preferences;
	}
	public String getPreferenceOne() {
		return preferences.getFirstPreference();
	}
	public String getPreferenceTwo() {
		return preferences.getSecondPreference();
	}
	public String getPreferenceThree() {
		return preferences.getThirdPreference();
	}
	public String getPreferenceFour() {
		return preferences.getFourthPreference();
	}
	protected Integer difficultStudent(int i) {
	
		if(getDifficultStudents().size()<=i) {
			return 0;
		}
		else if (getDifficultStudents().get(i)==null) {
			return 0;
		} else {
		
		return Integer.parseInt(getDifficultStudents().get(i).replace("S", ""));
		}
	}
	
	protected String getPreference(int i) {
		if(i==0) {
			return getPreferenceOne();
		}
		else if(i==1) {
			return getPreferenceTwo();
		}
		else if(i==2) {
			return getPreferenceThree();
		}
		else if(i==4) {
			return getPreferenceFour();
		}
		return null;
	}





	
}
