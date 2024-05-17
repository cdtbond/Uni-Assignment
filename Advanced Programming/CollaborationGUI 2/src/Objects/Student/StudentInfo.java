package Objects.Student;
import java.util.ArrayList;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Objects.Supporting.PersonalityType;

public class StudentInfo extends Student {

	private static final long serialVersionUID = -5123491769256915225L;
	private PersonalityType personality;
	private ArrayList<String> difficultStudents = new ArrayList<String>();

	
	
	public StudentInfo(Student stud, char personalityType, String enemyStudentOne, String enemyStudentTwo) throws  IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		super(stud.getID(), stud.getSkills());
		this.setPersonality(new PersonalityType(personalityType));
		//can't do a check of other students in case they are entered later
		this.difficultStudents.add(enemyStudentOne);
		this.difficultStudents.add(enemyStudentTwo);

	}
	
	//two enemy students
	public StudentInfo(int studId,Integer progRank, Integer anRank, Integer netRank, Integer webRank, char personalityType, String enemyStudentOne, String enemyStudentTwo) throws  IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, NumberFormatException, IDOutOfRangeException {
		super(studId, progRank,anRank,netRank,webRank);
		this.setPersonality(new PersonalityType(personalityType));
		//can't do a check of other students in case they are entered later
		addEnemyStudentCheckForVoid(enemyStudentOne,enemyStudentTwo);
	}
	
	//checks for SQL blank students and excludes them
	private void addEnemyStudentCheckForVoid(String enemyStudentOne, String enemyStudentTwo) {
		if(enemyStudentOne=="S0" && enemyStudentTwo=="S0") {
			
		} else if(enemyStudentOne=="S0"){
			this.difficultStudents.add(enemyStudentOne);
		} else {
			this.difficultStudents.add(enemyStudentOne);
			this.difficultStudents.add(enemyStudentTwo);
		}
	}
	
	
	//just one enemy student
	public StudentInfo(int studId,Integer progRank, Integer anRank, Integer netRank, Integer webRank, char personalityType, String enemyStudentOne) throws  IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, NumberFormatException, IDOutOfRangeException {
		super(studId, progRank,anRank,netRank,webRank);
		this.setPersonality(new PersonalityType(personalityType));
		//can't do a check of other students in case they are entered later
		this.difficultStudents.add(enemyStudentOne);

	}
	
	//No enemy student
	public StudentInfo(int studId,int progRank, int anRank, int netRank, int webRank, char personalityType) throws  IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, NumberFormatException, IDOutOfRangeException {
		super(studId, progRank,anRank,netRank,webRank);
		this.setPersonality(new PersonalityType(personalityType));

	}
	
	//With just one enemy student
	public StudentInfo(Student stud, char personalityType, String enemyStudentOne) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		super(stud.getID(), stud.getSkills());
		this.setPersonality(new PersonalityType(personalityType));
		
		//can't do a check of other students in case they are entered later
		this.difficultStudents.add(enemyStudentOne);
				
	}
	
	//With no enemy student
	public StudentInfo(Student stud, char personalityType) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		super(stud.getID(), stud.getSkills());
		this.setPersonality(new PersonalityType(personalityType));
				
	}
	
	public StudentInfo(StudentInfo stud) {
		super(stud.getID(),stud.getSkills());
		this.setPersonality(stud.getPersonality());
		this.difficultStudents = stud.getDifficultStudents();
	}

	public PersonalityType getPersonality() {
		return personality;
	}

	public String difficultStudentsToString() {
		String students = "";
		for (int i = 0; i < difficultStudents.size();i++) {
			students += difficultStudents.get(i)+" ";
		}
		return students;
	}
	
	
	//needs to trigger
	public void setPersonality(PersonalityType personality) {
		this.personality = personality;
	}


	public ArrayList<String> getDifficultStudents() {
		return difficultStudents;
	}
	// the student exists in the difficult student ArrayList
	public boolean compareDifficultStudents(String student) {
		for(int i=0;i<difficultStudents.size();i++) {
			
			if(difficultStudents.get(i)==student) {
				return true;
			}
		}
		return false;
	}


	public void setDifficultStudents(ArrayList<String> difficultStudents) {
		this.difficultStudents = difficultStudents;
	}

	public String toString() {
		return getID() + ";" + "P " + getSkills().getProgSkill() + ";" + "A " + getSkills().getAnSkill() + ";"+"N " + getSkills().getNetSkill() + ";"+"W "+getSkills().getWebSkill() + ";" + personality.getPersonalityId() + ";" + difficultStudentsToString();
	}
	
	public boolean isLeader() {
		return personality.getPersonalityId() =='A';
	}
	public boolean isSocializer() {
		return personality.getPersonalityId() =='B';
	}
	public boolean isThinker() {
		return personality.getPersonalityId() =='C';
	}
	public boolean isSupporter() {
		return personality.getPersonalityId() =='D';
	}
	
	
}
