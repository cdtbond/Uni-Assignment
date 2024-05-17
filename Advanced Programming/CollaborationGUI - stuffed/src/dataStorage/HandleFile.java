package dataStorage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Data.HashMaps;
import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.InvalidEmailException;
import Menu.MainMenu;
import Menu.Menu;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;


public class HandleFile {
	Scanner scan = new Scanner(System.in);
	HashMaps hm = HashMaps.getSingleton();
	
	public HandleFile() {
	}
	
	
	public void writeAllFiles() {
		write(stringShortlist(hm), "shortlist");
		write(stringPreferences(hm), "preferences");
		write(stringStudents(hm), "students");
		write(stringStudentInfo(hm), "studentinfo");
		write(stringProjects(hm), "projects");
		write(stringOwners(hm), "owners");
		write(stringCompanies(hm), "companies");		
	}
	
	
	public void loadTxtInput() {
		MainMenu m = new MainMenu();

		
		hm.setCompanies(loadCompany());
		hm.setOwners(loadOwner());		
		hm.setStudentInfo(loadStudentInfo());
		hm.setProjectInfo(loadProjects());
		hm.setStudents(loadStudents());
		hm.setPreferences(loadPreferences());
		hm.setShortlist(loadShortlist());
        m.mainMenu();
	}
	
	

	private String stringShortlist(HashMaps hm) {
		return hm.stringShortlist();
	}
	private String stringPreferences(HashMaps hm) {
		return hm.stringStudentPreferences();
	}
	private String stringStudents(HashMaps hm) {
		return hm.stringStudents();
	}
	private String stringProjects(HashMaps hm) {
		return hm.stringProjects();
	}
	private String stringOwners(HashMaps hm) {
		return hm.stringOwners();
	}
	private String stringCompanies(HashMaps hm) {
		return hm.stringCompanies();
	}
	private String stringStudentInfo(HashMaps hm) {
		return hm.stringStudentInfo();
	}

	
	
	
	
	public HashMap<String, Student> loadStudents() {
		Map<String,Student> smap = new HashMap<String,Student>();
	try {
		Stream<String> rows = Files.lines(Paths.get("students.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					return new Student(x[0],(x[1]),(x[2]),(x[3]),(x[4]));
				} catch (NumberFormatException | IncorrectRangeException | IDOutOfRangeException e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, Student>) (smap);
	
	}
	
	public HashMap<String, Company> loadCompany() {
		Map<String,Company> smap = new HashMap<String,Company>();
	try {
		Stream<String> rows = Files.lines(Paths.get("companies.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					return new Company(x[0],(x[1]),(x[2]),(x[3]),(x[4]));
				} catch (NumberFormatException | ABNValuesIncorrectException | ABNLengthIncorrectException | IDOutOfRangeException e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, Company>) (smap);
	
	}
	
	public HashMap<String, Owner> loadOwner() {
		Map<String,Owner> smap = new HashMap<String,Owner>();
	try {
		Stream<String> rows = Files.lines(Paths.get("owners.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					return new Owner(x[0],(x[1]),(x[2]),(x[3]),(x[4]),(x[5]),x[6]);
				} catch (NumberFormatException | IDOutOfRangeException | InvalidEmailException e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, Owner>) (smap);
	
	}
	
	public HashMap<String, StudentInfo> loadStudentInfo() {
		Map<String,StudentInfo> smap = new HashMap<String,StudentInfo>();
	try {
		Stream<String> rows = Files.lines(Paths.get("studentinfo.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					Student stud =  new Student(x[0],(x[1]),(x[2]),(x[3]),(x[4]));
					return new StudentInfo(stud,x[5].charAt(0));
				} catch (NumberFormatException | IncorrectPersonalityTypeException | IncorrectGradeException | IncorrectRangeException | IDOutOfRangeException e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, StudentInfo>) (smap);
	
	}
	
	
	public HashMap<String, Preferences> loadPreferences() {
		Map<String,Preferences> smap = new HashMap<String,Preferences>();
	try {
		Stream<String> rows = Files.lines(Paths.get("preferences.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					return new Preferences(x[0],(x[1]),(x[2]),(x[3]),(x[4]));
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, Preferences>) (smap);
	
	}
	
	public HashMap<String, Project> loadProjects() {
		Map<String,Project> smap = new HashMap<String,Project>();
	try {
		Stream<String> rows = Files.lines(Paths.get("projects.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					return new Project(x[0],(x[1]),(x[2]),(x[3]),Integer.parseInt(x[4].substring(2)),Integer.parseInt(x[5].substring(2)),Integer.parseInt(x[6].substring(2)),Integer.parseInt(x[7].substring(2)));
				} catch (NumberFormatException | IncorrectRankException | IncorrectRangeException | IDOutOfRangeException e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, Project>) (smap);
	
	}
	
	public HashMap<String, Project> loadShortlist() {
		Map<String,Project> smap = new HashMap<String,Project>();
	try {
		Stream<String> rows = Files.lines(Paths.get("shortlist.txt"));
	      smap = rows.map(x -> x.split(";"))
	      .collect(Collectors.toMap(x -> x[0],
	          x -> {
				try {
					return new Project(x[0],(x[1]),(x[2]),(x[3]),Integer.parseInt(x[4].substring(2)),Integer.parseInt(x[5].substring(2)),Integer.parseInt(x[6].substring(2)),Integer.parseInt(x[7].substring(2)));
				} catch (NumberFormatException | IncorrectRankException | IncorrectRangeException | IDOutOfRangeException e) {
					e.printStackTrace();
					return null;
				}
			}));
	      rows.close();
	      System.out.println(smap);
	      

	      
	    } catch (IOException e) {
	      System.out.println("IO Exception Occurred.");
	      
	    }
		return (HashMap<String, Project>) (smap);
	
	}
	
	
	
	public void write(String entry,String fileName) {
		try {
		      FileWriter myWriter = new FileWriter( fileName + ".txt");
		      myWriter.write(entry);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void DeleteFile(String fileName) {
		  	File myObj = new File(fileName + ".txt"); 
		    if (myObj.delete()) { 
		      System.out.println("Deleted the file: " + myObj.getName());
		    } else {
		      System.out.println("Failed to delete the file.");
		    } 
		  } 
		
	
	
	
}
