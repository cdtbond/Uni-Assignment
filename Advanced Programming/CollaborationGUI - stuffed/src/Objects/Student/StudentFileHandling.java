package Objects.Student;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectRangeException;
import Objects.FileHandling;

public class StudentFileHandling extends Student implements FileHandling {

	private static final long serialVersionUID = 6260480406915264686L;

	public StudentFileHandling(String ID, Integer progRank, Integer anRank, Integer netRank, Integer webRank)
			throws IncorrectRangeException, IDOutOfRangeException {
		super(ID, progRank, anRank, netRank, webRank);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return getID() + ";" + "P " + getSkills().getProgSkill() + ";" + "A " + getSkills().getAnSkill() + ";"+"N " + getSkills().getNetSkill() + ";"+"W "+getSkills().getWebSkill();
	}
}
