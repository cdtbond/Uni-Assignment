package Objects.Student;

import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Objects.FileHandling;

public class PreferencesFileHandling extends Preferences implements FileHandling {

	private static final long serialVersionUID = -7766863673206473235L;

	public PreferencesFileHandling(String ID, String highestRankedProject, String secondRankedProject,
			String thirdRankedProject, String lowestRankedProject) throws DuplicatePreferenceException, IDOutOfRangeException {
		super(ID, highestRankedProject, secondRankedProject, thirdRankedProject, lowestRankedProject);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return getID() + ";" + getProjectPreferences().get(0) + " " + 4 + ";" + getProjectPreferences().get(1) + " " + 3 + ";" + getProjectPreferences().get(2) + " " + 2 + ";" + getProjectPreferences().get(3) + " " + 4;
	}	
}
