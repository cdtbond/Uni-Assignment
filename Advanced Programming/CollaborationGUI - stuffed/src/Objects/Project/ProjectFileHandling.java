package Objects.Project;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Objects.FileHandling;

public class ProjectFileHandling extends Project implements FileHandling {

	private static final long serialVersionUID = 6672466389528050380L;
	public ProjectFileHandling(String ID, String title, String description, String ownerId, Integer progRank,
			Integer anRank, Integer netRank, Integer webRank) throws IncorrectRankException, IncorrectRangeException, IDOutOfRangeException {
		super(ID, title, description, ownerId, progRank, anRank, netRank, webRank);
	}
	public ProjectFileHandling(Project project) throws IncorrectRankException, IncorrectRangeException, IDOutOfRangeException {
		super(project.getID(),project.getTitle(),project.getDescription(),project.getOwnerId(),(int) project.getProgSkill(),(int) project.getAnSkill(),(int) project.getNetSkill(),(int) project.getWebSkill());
	}
	@Override
	public String toString() {
		return getID() + ";" + getTitle() + ";" + getDescription() + ";" + getOwnerId() + ";P "+  getSkills().getProgSkill() + ";A "+ getSkills().getAnSkill() + ";N " + getSkills().getNetSkill() + ";W " + getSkills().getWebSkill();
	}

}
