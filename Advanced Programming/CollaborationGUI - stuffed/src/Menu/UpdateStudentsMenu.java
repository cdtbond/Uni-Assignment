package Menu;

import Data.HashMaps;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.InvalidSKeyException;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;

public class UpdateStudentsMenu extends Menu{
HashMaps hm = HashMaps.getSingleton();
	public void addStudentInfo() {
		try {
		println("Below are listed the Student Id's to select from:");
		hm.stringStudentIds();
		println("Enter the id of the student you wish to add student info for:");
		print("S");
		entryInt = intScanner();
		
		Student stud = hm.getStudent("S" + entryInt);
		
		println("What's the personality type of the student? (A - Director, B - Socialiser, C - Thinker, D - Supporter)");
		
		char personality = charScanner();
		
		println("Is there any student this student can't work with? (leave this blank if there are none)");
		
		print("S");
		entryInt = intScanner();
		
		String enemyStudent1 = "S" + entryInt;
		String enemyStudent2 = "";
		
		// if enemy student entered
		if (enemyStudent1.length() != 1) {
			println("Is there another student this student can't work with? (leave this blank if there are none)");
			print("S");
			entryInt = intScanner();
			
			enemyStudent2 = "S" + entryInt;
			
			//if enemy two students entered
			if (enemyStudent2.length() != 1) {
				
				StudentInfo prefs = new StudentInfo(stud,personality,enemyStudent1,enemyStudent2);
				hm.addStudInfo(prefs);
			}
			//one enemy student added
			else {
				StudentInfo prefs = new StudentInfo(stud,personality,enemyStudent1);
				hm.addStudInfo(prefs);
			}
			//
		}// no enemy student added
		else {
			StudentInfo prefs = new StudentInfo(stud,personality);
			hm.addStudInfo(prefs);
		}
		
		
		} catch (InvalidSKeyException e) {
			println("Enter a student number in the displayed list");
		} catch (IncorrectPersonalityTypeException e) {
			println("Personality Types are A-D, enter a correct character");
		} catch (IncorrectGradeException e) {
			println("Grades are 0-4, enter a correct integer");
		} catch (IncorrectRangeException e) {
			println("Grades are 0-4, enter a correct integer");
		} catch (IdAlreadyUsedException e) {
			println("Id already in use in Student Preferences. Try again with a different student");
		} catch (IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public void addStudentPreferences() {
		try {
		println("Below are listed the Student Id's to select from:");
		hm.stringStudentIds();
		println("Enter the id of the student you wish to add student info for:");
		print("S");
		entryInt = intScanner();
		
		Student stud = hm.getStudent("S" + entryInt);
		
		println("What projects is this student interested in?");
		print("First Preference: Pr");
		
		String firstPref = "Pr" +intScanner();
		
		print("Second Preference: Pr");
		
		String secondPref = "Pr" +intScanner();
		
		print("Third Preference: Pr");
		
		String thirdPref = "Pr" +intScanner();
		
		print("Fourth Preference: Pr");
		
		String fourthPref = "Pr" + intScanner();
		
		Preferences prefs = new Preferences(stud.getID(),firstPref,secondPref,thirdPref,fourthPref);
		
		hm.addPreferences(prefs);
		
		} catch (InvalidSKeyException e) {
			println("Enter a student number in the displayed list");
		} catch (DuplicatePreferenceException e) {
			println("Id already in use in Student Preferences. Try again with a different student");
		} catch (IdAlreadyUsedException e) {
			println("Id already in use in Student Preferences. Try again with a different student");
		} catch (IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
