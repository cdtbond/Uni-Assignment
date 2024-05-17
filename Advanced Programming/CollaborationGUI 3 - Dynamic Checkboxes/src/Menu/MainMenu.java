package Menu;

import Data.HashMaps;
import Menu.Options.EntryMenuOptions;
import Menu.Options.MainMenuOptions;
import dataStorage.DatabaseInterface;
import dataStorage.HandleFile;
import dataStorage.HandleSerialization;

public class MainMenu extends Menu{
	private ShortlistingMenu sl = new ShortlistingMenu();
	private AddResourcesMenu r1 = new AddResourcesMenu();
	private TeamMetricsMenu t1 = new TeamMetricsMenu();
	private UpdateStudentsMenu u1 = new UpdateStudentsMenu();
	
	public void mainMenu(HashMaps hm, HandleFile hf, HandleSerialization hs) {
	do {
	
		println("Make a Selection:");
		println("0: Add Company");
		println("1: Add Project Owner");
		println("2: Add Project");
		println("3: Add Student");
		println("4: Capture Student Personalities");		
		println("5: Add Student Preferences");
		println("6: Shortlist Projects");
		println("7: Form Teams");
		println("8: Display Team Fitness Metrics");
		println("9: Quit");			
		
		entryInt = intScanner();
		AddResourcesMenu mf = new AddResourcesMenu();
		MainMenuOptions selection = MainMenuOptions.values()[entryInt];
		
		switch (selection) {
		case AddCompany: 
			mf.addCompany(hm);
			break;
		case AddProjectOwner:
			mf.addOwner(hm);
			break;
		
		case AddProject:
			mf.addProject(hm);
			break;
			
		case AddStudent:
			mf.addPersonalities(hm);
			break;
			
		case CaptureStudentPersonalities:
			u1.addStudentInfo(hm);
			break;
			
		case AddStudentPreferences:
			u1.addStudentPreferences(hm);
			break;
			
		case ShortlistProjects:
			sl.makeShortlist(hm);
			sl.calculateAverageGrade(hm);
			break;
			
		case FormTeams:
			t1.formTeams();
			break;
			
		case DisplayFitnessMetrics:
			t1.displayFitnessMetrics();
			break;
			
		case Quit:
			
			break;
			
		default:
			println("That's not a valid selection, try again");
			
		}	
		
	}  while (entryInt != 9);


	entryInt = 0;
		DatabaseInterface di = new DatabaseInterface();
		println("How would you like to save your progress");
		println("0. Save to txt");
		println("1. Save to serialized file");
		println("2. Save to SQL");		
		println("3. Don't Save");		
		
		entryInt = intScanner();
		
		EntryMenuOptions selection = EntryMenuOptions.values()[entryInt];
		switch (selection) {
		//Choice 1: Save to txt files.
		case FromTxt:
			 hf.writeAllFiles(hm);
			break;
		//Choice 2: Save to Serialization	
		case FromSerial:
			hs.saveSerializedOutput(hm);		
			break;
		//Choice 2: Save to Serialization	
		case FromSQL:
				di.saveSQLOutput(hm);		
				break;	
			
		//Choice 3: Build from Scratch				
		case FromScratch:
			println("You have chosen not to save");		
			break;
			
		default:
			println("That's not a valid selection, not saving");				
			
		}
	
	}
}
