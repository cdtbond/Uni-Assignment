package dataStorage;
import java.sql.*;
import java.util.ArrayList;

import Data.HashMaps;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.InvalidMemberException;
import Exceptions.InvalidProjectException;
import Exceptions.InvalidStudentException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.SQLExeception;
import Exceptions.StudentConflictException;
import Menu.MainMenu;
import Menu.Menu;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;
public class DatabaseInterface {
	private static HashMaps h1 = HashMaps.getSingleton();
	   private static String studentColumns = "INSERT INTO Students (ID,ProgRank,AnRank,NetRank,WebRank,PersonalityType,DifficultStudent1,DifficultStudent2,Pref1,Pref2,Pref3,Pref4)" + "\n" + "VALUES ";
	   private static String teamColumns = "INSERT INTO Team (ID,Member)" + "\n" + "VALUES ";
	   private static String companyColumns = "INSERT INTO Company (ID,Name,ABN,URL,Address)" + "\n" + "VALUES ";
	   private static String ownersColumns = "INSERT INTO Owner (ID,firstName,lastName,role,email,projectId,companyId)" + "\n" + "VALUES ";
	   private static String projectColumns = "INSERT INTO Project (ID,title,Description,OwnerId,Skill1,Skill2,Skill3,Skill4)" + "\n" + "VALUES ";
	   private static String shortlistColumns = "INSERT INTO Shortlist (ID)" + "\n" + "VALUES ";
	   
   private static Connection connect() {
      Connection conn = null;
	   try {
		 String url = "jdbc:sqlite:CollaborationDB.db";
         conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement();
      } catch( SQLException se ) {
         System.out.println("SQLError: " + 
           se.getMessage () + " code: " + se.getErrorCode ());
      } catch( Exception e ) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
	return conn;
	   
   }

   
   
   
   public static void deleteAllTables() {
   	deleteTable("Team");
   	deleteTable("Shortlist");
   	deleteTable("Company");
   	deleteTable("Project");
   	deleteTable("Owner");
   	deleteTable("Students");
   }

   public static void createAllObjectsFromSQL() {
	   	createCompanies();
	   	createProjects();
	   	createOwners();
	   	createStudents();
	   	createShortlist();
	   	createTeams();
		h1.computeSDGettingFirstNSecondPref();
		h1.computeSdSkillCompetency();
		h1.computeSDSkillShortfall();
	   	
	}
   
   private static void createTeams() {
	   ResultSet rs = selectDistinctFromTableColumn("Team","ID");
	   
	   try {
		   while (rs.next()) {
			   String teamId = "Pr" + rs.getString("ID");
			  
			   
			   String sql = "SELECT Member FROM Team Where ID = " + rs.getString("ID");
			   ResultSet studentsResultSet = executeQuery(sql);
			   ArrayList<String> studentIds = new ArrayList<String>();
			   while (studentsResultSet.next() ) {
				   studentIds.add(studentsResultSet.getString("Member"));
			   }
			   

			   
			   Project proj = h1.getProject(teamId);
			   StudentInfoWithPrefs s1 = h1.getStudentInfoWPrefs("S"+ studentIds.get(0));
			   StudentInfoWithPrefs s2 = h1.getStudentInfoWPrefs("S"+studentIds.get(1));
			   StudentInfoWithPrefs s3 = h1.getStudentInfoWPrefs("S"+studentIds.get(2));
			   StudentInfoWithPrefs s4 = h1.getStudentInfoWPrefs("S"+studentIds.get(3));
			   
			   Team team = new Team(proj,s1,s2,s3,s4);
			   team.recalculateMetrics();
			   h1.addTeam(team);

		   } 
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (StudentConflictException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PersonalityImbalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepeatedMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoLeaderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidStudentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidProjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}







private static ResultSet selectDistinctFromTableColumn(String tableToSelect, String columnToSelect) {
	   String sql = "SELECT DISTINCT " + columnToSelect + " from " + tableToSelect;
	   ResultSet rs = executeQuery(sql);
	   return rs;
}

public void insertAllObjectsIntoSQL(HashMaps hm) {
	   	insertAllStudents(hm.getArrayStudentInfoWithPrefs());
	   	insertTeams(hm.getArrayTeams());
	   	insertCompanies(hm.getArrayCompanies());
	   	insertOwners(hm.getArrayOwners());
	   	insertProjects(hm.getArrayProjects());	
	   	insertShortlist(hm.getArrayShortList());	
	}

private static void createCompanies() {
	   ResultSet rs = selectTable("Company");
	   try {
		   while (rs.next()) {
			   h1.addCompany(new Company(rs));
			   
		   } 
		   } catch (IdAlreadyUsedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	   
   
   private static void createOwners() {
	   ResultSet rs = selectTable("Owner");
	   try {
		   while (rs.next()) {
			   h1.addOwner(new Owner(rs));
			 
		   } 
		   } catch (IdAlreadyUsedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	
   private static void createProjects() {
	   ResultSet rs = selectTable("Project");
	   try {
		   while (rs.next()) {
			   h1.addProject(new Project(rs));
			  
		   } 
		   } catch (IdAlreadyUsedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}	
   private static void createStudents() {
	   ResultSet rs = selectTable("Students");
	   try {
		   while (rs.next()) {
			   
				h1.addStudentInfoWithPreferences(new StudentInfoWithPrefs(rs));
				
		}
	   } catch (IncorrectPersonalityTypeException | IncorrectGradeException | IncorrectRangeException | SQLException | NumberFormatException | IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }   		   
}
   private static void createShortlist() {
	   ResultSet rs = selectTable("Shortlist");
	   try {
		   while (rs.next()) {
			   h1.addToShortlist("Pr" + rs.getInt("ID"));
			   
		}
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }   		   
}
   
	private static ResultSet selectTable(String tableToSelect) {
		   String sql = "SELECT * from " + tableToSelect;
		   ResultSet rs = executeQuery(sql);
		   return rs;
	   }
	   
   //To be used for Select Statement
   private static ResultSet executeQuery(String queryToExecute) {
	   ResultSet rs;
	   Connection conn = connect();
	   Statement stmt;
	try {
		stmt = conn.createStatement();
	    rs = stmt.executeQuery(queryToExecute);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		rs = null;
	}
	return rs;
   }
   
   //To be used for delete or insert into statement
   private static void executeUpdate(String queryToExecute) {
	   Connection conn = connect();
	   Statement stmt;
	try {
		stmt = conn.createStatement();
	    stmt.executeUpdate(queryToExecute);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
   private static void deleteTable(String tableToDelete) {
	   String sql = "DELETE FROM "+ tableToDelete +";";
	   executeUpdate(sql);
   }



		   
   private static void insertAllStudents(ArrayList<StudentInfoWithPrefs> studs) {
	   String sql = studentColumns;
	   for (int i = 0; i<studs.size();i++) {
		   StudentInfoWithPrefs stud = studs.get(i);
		   sql += "(" + stud.sqlFormat() + ")" ;
		   if(i==studs.size()-1) {
			   sql += ";";
		   } else {
			   sql += "," + "\n";
		   }
	   }
	   executeUpdate(sql);
   }
   private static void insertTeams(ArrayList<Team> teams) {
	   String sql = teamColumns;
	   for (int i = 0; i<teams.size();i++) {
		   Team team = teams.get(i);
		   ArrayList<StudentInfoWithPrefs> studs = team.getMembers();
		   for(int j=0;j<studs.size();j++) {
			   if (teams.size()-1==i && studs.size()-1 == j) {
				   sql +=  team.getSQL(j) + ";";				   
			   } else {
				   sql +=  team.getSQL(j) + ",";					   
			   }
		   }	   
	   }
	   
	   executeUpdate(sql);
   }
   private static void insertCompanies(ArrayList<Company> companies) {
	   String sql = companyColumns;
	   for (int i = 0; i<companies.size();i++) {	   
		   if (companies.size()-1==i) {
			   sql +=  companies.get(i).getSQL() + ";";				   
		   } else {
			   sql +=  companies.get(i).getSQL() + ",";					   
		   }
	   }
	   executeUpdate(sql);
   }
   private static void insertOwners(ArrayList<Owner> owners) {
	   String sql = ownersColumns;
	   for (int i = 0; i<owners.size();i++) {	   
		   if (owners.size()-1==i) {
			   sql +=  owners.get(i).getSQL() + ";";				   
		   } else {
			   sql +=  owners.get(i).getSQL() + ",";					   
		   }
	   }
	   executeUpdate(sql);
   }
   private static void insertProjects(ArrayList<Project> projects) {
	   String sql = projectColumns;
	   for (int i = 0; i<projects.size();i++) {	   
		   if (projects.size()-1==i) {
			   sql +=  projects.get(i).getSQL() + ";";				   
		   } else {
			   sql +=  projects.get(i).getSQL() + ",";					   
		   }
	   }
	   executeUpdate(sql);
   }
private static void insertShortlist(ArrayList<Project> arrayShortList) {
	   String sql = shortlistColumns;
	   for (int i = 0; i<arrayShortList.size();i++) {	   
		   if (arrayShortList.size()-1==i) {
			   sql +=  arrayShortList.get(i).getShortlistSQL() + ";";				   
		   } else {
			   sql +=  arrayShortList.get(i).getShortlistSQL() + ",";					   
		   }
	   }
	   
	   executeUpdate(sql);
}
public void loadSQL(HandleFile hf,HandleSerialization hs) {
	createAllObjectsFromSQL();
	deleteAllTables();
	MainMenu menu = new MainMenu();
	menu.mainMenu(h1, hf, hs);
}
public HashMaps loadSQL() {
	createAllObjectsFromSQL();
	deleteAllTables();
	return h1;
}


public void saveSQLOutput(HashMaps hm) {
	insertAllObjectsIntoSQL(hm);
}
   
   
}
