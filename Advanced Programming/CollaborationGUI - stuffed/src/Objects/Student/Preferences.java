package Objects.Student;

import java.util.ArrayList;

import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Objects.ParentObject;

public class Preferences extends ParentObject {
	

	private static final long serialVersionUID = 3299505572300828537L;
	private ArrayList<String> projectPreferences = new ArrayList<String>();
	// in future hold in projects arraylist
	//private ArrayList<Project> projectPreferences = new ArrayList<Project>();
	
	public Preferences(String ID, String highestRankedProject, String secondRankedProject, String thirdRankedProject, String lowestRankedProject) throws DuplicatePreferenceException, IDOutOfRangeException {
		super(ID);
		duplicatePreference(highestRankedProject,secondRankedProject,thirdRankedProject,lowestRankedProject);
		this.projectPreferences.add(highestRankedProject);
		this.projectPreferences.add(secondRankedProject);		
		this.projectPreferences.add(thirdRankedProject);
		this.projectPreferences.add(lowestRankedProject);			
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


	
	
	
}
