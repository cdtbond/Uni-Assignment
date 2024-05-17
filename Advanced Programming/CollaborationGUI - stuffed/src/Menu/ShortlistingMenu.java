package Menu;

import Data.HashMaps;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;

public class ShortlistingMenu extends Menu{
	HashMaps hm = HashMaps.getSingleton();
	Integer projectsNeededInt = 5;
	
	public ShortlistingMenu() {
	}
	
	public void makeShortlist() {
		Double projectsNeeded = (double) (hm.getStudentCount()/4);
		projectsNeededInt = (int) Math.ceil(projectsNeeded);
		Integer projectsToRemove = hm.getProjectsCount() - projectsNeededInt;
		hm.makeShortlist(hm.returnKeysToRemove(projectsToRemove));
		//finally merge the studentInfo and Student preferences into one object
		//add new students into unassignedStudents
		
		try {
			hm.mergeStudentInfoAndPreferences();
			hm.addAllStudentsToUnassignedStudentsHashMap();
		} catch (IncorrectPersonalityTypeException e) {
			println("Error in merging preferences and student info, Incorrect Personality Type.");
		} catch (IncorrectGradeException e) {
			println("Error in merging preferences and student info, Incorrect Grade.");
		} catch (IncorrectRangeException e) {
			println("Error in merging preferences and student info, Incorrect Range.");
		} catch (IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
