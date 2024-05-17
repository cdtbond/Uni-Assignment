package Objects.Student;

import Exceptions.IDOutOfRangeException;
import Objects.FileHandling;

public class StudentInfoFileHandling extends StudentInfo implements FileHandling {
	
	private static final long serialVersionUID = -6043587065134094133L;

	public StudentInfoFileHandling(StudentInfo stud) throws IDOutOfRangeException {
		super(stud);
	}

	@Override
	public String toString() {
		return getID() + ";" + "P " + getSkills().getProgSkill() + ";" + "A " + getSkills().getAnSkill() + ";"+"N " + getSkills().getNetSkill() + ";"+"W "+getSkills().getWebSkill() + ";" + getPersonality().getPersonalityId() + ";" + difficultStudentsToString();
	}
}
