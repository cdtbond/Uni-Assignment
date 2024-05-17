package Menu;
import java.sql.SQLException;

import Data.HashMaps;
import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidMemberException;
import Exceptions.InvalidNamingException;
import Exceptions.InvalidProjectException;
import Exceptions.InvalidSKeyException;
import Exceptions.InvalidStudentException;
import Exceptions.InvalidTeamException;
import Exceptions.NoLeaderException;
import Exceptions.NotEnoughTeamsFormedException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Exceptions.StudentNotSubstitutedException;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;

public class AddResourcesMenu extends Menu{
	private int rangeRoof = 20;
	private int rangeFloor = 0;
	Integer role = 20;

	public void addCompany(HashMaps hm) {
	
		try {
		println("Enter the company ID:");	
		print("C");
		
		//Inbuilt int checker
		entryInt = intScanner();

		//check Id is within the allowable range
		//put into company ID format
		
		int id = entryInt;
		
		println("Enter the company name:");		
		stringScanner();
		//Need to check is string
		String name = stringScanner();
		
		//ABN
		println("Enter the ABN:");	
		//Need to check...
			// 1. Not already used - not yet implemented
			// 4. First two digits are a checksum ... implement later  outlined here --> https://abr.business.gov.au/Help/AbnFormat
		entryString = stringScanner();



		String abn = entryString;
		
		println("Enter the URL:");
		print("www.");
		//Need to check...
			// 1. Not already used
		
		String url = stringScanner();
		
		
		
		println("Enter the address:");		
		//Need to check...
			// 1. Not already used
		
		
		String address = stringScanner();
		
		//load into hashmap
		
		Company comp = new Company(id,name,abn,url,address);
		
		hm.addCompany(comp);
		
		
		} catch (IdAlreadyUsedException e) {
			println("Company ID already in use");
		} catch (IDOutOfRangeException e) {
			println("ID out of allowable range. Floor is " + rangeFloor + ". Roof is " + rangeRoof );
		
		} catch (ABNLengthIncorrectException e) {
			println("Incorrect length for ABN, should be 11 characters");
		} catch (ABNValuesIncorrectException e) {
			println("Incorrect values entered for ABN, should be characters from 0-9");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addPersonalities(HashMaps hm) {
			try {
				println("Enter the student ID:");	
				print("S");
				//Need to check...
				

				//Inbuilt int checker
				entryInt = intScanner();

				//check Id is within the allowable range
				//put into company ID format
				
				int id = entryInt;

				
				//Inbuilt int checker
				println("Enter the value between 0-4 for each of the below that represents the average mark you received for each subject area:");
				
				print("Programming & Software Engineering: ");
				int progRank = intScanner();
				
				print("Analytics and Big Data: ");			
				int anRank = intScanner();	
				
				print("Networking and Security: ");			
				int netRank = intScanner();				
				
				print("Web & Mobile applications: ");			
				int webRank = intScanner();				

				//load into hashmap
				
				Student stud = new Student(id,progRank,anRank,netRank,webRank);
				
				hm.addStudents(stud);
				
				
				} catch (IdAlreadyUsedException e) {
					println("Student ID already in use");
				} catch (IDOutOfRangeException e) {
					println("ID out of allowable range. Floor is " + rangeFloor + ". Roof is " + rangeRoof );
				} catch (IncorrectRangeException e) {
					println("The values for the skills you entered are outside the valid range 0 - 4, try again.");
				}
		}
	public void addProject(HashMaps hm) {
			try {
				println("Enter the project ID:");	
				print("Pr");
				//Need to check...
				

				//Inbuilt int checker
				entryInt = intScanner();


				//put into company ID format
				
				int id = entryInt;
				
				print("Enter title:");
				
				entryString = stringScanner();
				String title = stringScanner();			
				
				
				print("Enter description:");
				
				entryString = stringScanner();
				String description = stringScanner();	
				
				
				//Inbuilt int checker
				entryInt = intScanner();


				//put into company ID format
				
				String ownId = "Own" + entryInt;
				
				//Inbuilt int checker
				println("Enter the rank between 1-4 for each of the below, each number can only appear once:");
				print("How much do you value Programming & Software Engineering skills? ");
				
				
				int progRank = intScanner();
				
				print("How much do you value Analytics and Big Data skills? ");			
				int anRank = intScanner();	
				
				print("How much do you value Networking and Security skills? ");			
				int netRank = intScanner();				
				
				print("How much do you value Web & Mobile applications skills? ");			
				int webRank = intScanner();				

				//load into hashmap
				
				Project proj = new Project(id,title,description,ownId,progRank,anRank,netRank,webRank);
				
				hm.addProject(proj);
				
				
				} catch (IdAlreadyUsedException e) {
					println("Project ID already in use");
				} catch (IDOutOfRangeException e) {
					println("ID out of allowable range. Floor is " + rangeFloor + ". Roof is " + rangeRoof );
				} catch (IncorrectRankException e) {
					// TODO Auto-generated catch block
					println("You have incorrectly entered the ranking, redo but ensure that the number 1,2,3 & 4 appear only once each.");
				} catch (IncorrectRangeException e) {
					// TODO Auto-generated catch block
					println("The values for the skills you entered are outside the valid range 0 - 4, try again.");
				}
		}
	public void addOwner(HashMaps hm) {
			try {
				println("Enter the owner ID:");	
				print("Own");
				//Need to check...
				//Inbuilt int checker
				entryInt = intScanner();

				//check Id is within the allowable range
				//put into company ID format
				
				int id = entryInt;
				
				print("Enter the owner first and last name:");		
				
				//Need to check is string
				entryString = stringScanner();
				String name = stringScanner();
				
				
				print("Enter role:");	
				
				String role = stringScanner();
				
				print("Enter email:");			
				
				String email = stringScanner();
				
				print("Enter associated Project Id:Pr");
				entryInt = intScanner();
				
				//check project Id is within allowable range		
				String projectId = "Pr" + entryInt;
				
				
				print("Enter associated Company Id:C");
				entryInt = intScanner();
				
				//check project Id is within allowable range		
				String companyId = "C" + entryInt;
				
				
				//load into hashmap
				
				Owner own = new Owner(id,name,role,email,projectId, companyId);
				
				hm.addOwner(own);
				
				
				} catch (IdAlreadyUsedException e) {
					println("Company ID already in use");
				} catch (IDOutOfRangeException e) {
					println("ID out of allowable range. Floor is " + rangeFloor + ". Roof is " + rangeRoof );
				} catch (InvalidNamingException e) {
					println("Invalid name, must have space");
				} catch (InvalidEmailException e) {
					println("Invalid email, must include @ & . signs");
				}
			
		}	
	


}
