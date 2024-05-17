package Objects.Student;

import java.util.ArrayList;

import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Objects.ParentObject;

public class Preferences extends ParentObject {
	

	private static final long serialVersionUID = 3299505572300828537L;
	private ArrayList<String> projectPreferences = new ArrayList<String>();
	private static String prefix = "S";
	private static String projectPrefix = "Pr";
	
	public Preferences(String ID, String highestRankedProject, String secondRankedProject, String thirdRankedProject, String lowestRankedProject) throws DuplicatePreferenceException {
		super(ID);
		duplicatePreference(highestRankedProject,secondRankedProject,thirdRankedProject,lowestRankedProject);
		this.projectPreferences.add(highestRankedProject);
		this.projectPreferences.add(secondRankedProject);		
		this.projectPreferences.add(thirdRankedProject);
		this.projectPreferences.add(lowestRankedProject);			
	}
	public Preferences(int ID, int highestRankedProject, int secondRankedProject, int thirdRankedProject, int lowestRankedProject) throws DuplicatePreferenceException, IDOutOfRangeException {
		super(ID);
		duplicatePreference(projectPrefix+highestRankedProject,projectPrefix+secondRankedProject,projectPrefix+thirdRankedProject,projectPrefix+lowestRankedProject);
		this.projectPreferences.add(projectPrefix+highestRankedProject);
		this.projectPreferences.add(projectPrefix+secondRankedProject);		
		this.projectPreferences.add(projectPrefix+thirdRankedProject);
		this.projectPreferences.add(projectPrefix+lowestRankedProject);			
	}
	
	public Preferences(int ID, ArrayList<Integer> prefs) throws DuplicatePreferenceException, IDOutOfRangeException {
		super(ID);
		duplicatePreference(projectPrefix+prefs.get(0),projectPrefix+prefs.get(1),projectPrefix+prefs.get(2),projectPrefix+prefs.get(3));
		for (int i=0;i<prefs.size();i++) {
			this.projectPreferences.add(projectPrefix+prefs.get(i));
		}		
	}
	
	private void duplicatePreference(String firstStr, String secondStr, String thirdStr, String fourthStr) throws DuplicatePreferenceException {
		if(firstStr.equals(secondStr)) {
			throw new DuplicatePreferenceException();
		}
		else if(firstStr.equals(thirdStr)) {
			throw new DuplicatePreferenceException();
		}
		else if(firstStr.equals(fourthStr)) {
			throw new DuplicatePreferenceException();
		}
		else if (secondStr.equals(thirdStr)) {
			throw new DuplicatePreferenceException();
		}
		else if (secondStr.equals(fourthStr)) {
			throw new DuplicatePreferenceException();
		}
		else if (thirdStr.equals(fourthStr)) {
			throw new DuplicatePreferenceException();
		}
		
	}
	
	public ArrayList<String> getProjectPreferences() {
		return projectPreferences;
	}

	
	public String getFirstPreference() {
		return projectPreferences.get(0);
	}
	public String getSecondPreference() {
		return projectPreferences.get(1);
	}
	public String getThirdPreference() {
		return projectPreferences.get(2);
	}
	public String getFourthPreference() {
		return projectPreferences.get(3);
	}
	
	public void setProjectPreferences(ArrayList<String> projectPreferences) {
		this.projectPreferences = projectPreferences;
	}


	public String toString() {
		return getID() + ";" + getProjectPreferences().get(0) + " " + 4 + ";" + getProjectPreferences().get(1) + " " + 3 + ";" + getProjectPreferences().get(2) + " " + 2 + ";" + getProjectPreferences().get(3) + " " + 1;
	}


	@Override
	public String addPrefix() {
		return prefix;
	}


	@Override
	public String removePrefix() {
		return getID().replace(prefix, "");
	}		
	
	
}
