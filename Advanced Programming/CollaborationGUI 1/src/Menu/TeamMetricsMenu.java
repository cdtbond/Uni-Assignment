package Menu;

import Data.HashMaps;
import Exceptions.InvalidMemberException;
import Exceptions.InvalidProjectException;
import Exceptions.InvalidStudentException;
import Exceptions.InvalidTeamException;
import Exceptions.NoLeaderException;
import Exceptions.NotEnoughTeamsFormedException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Exceptions.StudentNotSubstitutedException;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;
import SharedValues.StandardDeviationValues;

public class TeamMetricsMenu extends Menu{
	private static int projectsNeededInt = 5;
	StandardDeviationValues sd = StandardDeviationValues.getSingleton();
	HashMaps hm = HashMaps.getSingleton();
	
	public void formTeams() {
		do {
		println("1: Enter new team");
		println("2: Swap student from team");
		println("3: Dissolve team");
		println("4: Auto-Form teams");
		
		println("5: Return to Main menu");


		entryInt = intScanner();
		
		
		if(entryInt == 1) {
			newTeam();
		}
		else if (entryInt == 2) {
			swapStudent();
		}
		else if (entryInt == 3) {
			dissolveTeam();
		}
		else if (entryInt == 4) {
			autoFormTeam();
		}
		
		
		} while(entryInt != 5);
		println("Returning to main menu");
	}
	private void autoFormTeam() {
		try {
		Project project = hm.getProject("Pr14");
		
		StudentInfoWithPrefs firstStudent = hm.getStudentInfoWPrefs("S7");
		StudentInfoWithPrefs secondStudent = hm.getStudentInfoWPrefs("S8");
		StudentInfoWithPrefs thirdStudent = hm.getStudentInfoWPrefs("S12");
		StudentInfoWithPrefs fourthStudent = hm.getStudentInfoWPrefs("S16");
		Team team1 = new Team(project,firstStudent,secondStudent,thirdStudent,fourthStudent);
		
		project = hm.getProject("Pr12");
		firstStudent = hm.getStudentInfoWPrefs("S3");
		secondStudent = hm.getStudentInfoWPrefs("S10");
		thirdStudent = hm.getStudentInfoWPrefs("S14");
		fourthStudent = hm.getStudentInfoWPrefs("S17");
		Team team2 = new Team(project,firstStudent,secondStudent,thirdStudent,fourthStudent);
		
		project = hm.getProject("Pr10");
		firstStudent = hm.getStudentInfoWPrefs("S4");
		secondStudent = hm.getStudentInfoWPrefs("S9");
		thirdStudent = hm.getStudentInfoWPrefs("S11");
		fourthStudent = hm.getStudentInfoWPrefs("S18");
		Team team3 = new Team(project,firstStudent,secondStudent,thirdStudent,fourthStudent);
		
		project = hm.getProject("Pr5");
		firstStudent = hm.getStudentInfoWPrefs("S5");
		secondStudent = hm.getStudentInfoWPrefs("S6");
		thirdStudent = hm.getStudentInfoWPrefs("S13");
		fourthStudent = hm.getStudentInfoWPrefs("S20");
		Team team4 = new Team(project,firstStudent,secondStudent,thirdStudent,fourthStudent);
		
		project = hm.getProject("Pr4");
		firstStudent = hm.getStudentInfoWPrefs("S1");
		secondStudent = hm.getStudentInfoWPrefs("S2");
		thirdStudent = hm.getStudentInfoWPrefs("S15");
		fourthStudent = hm.getStudentInfoWPrefs("S19");
		Team team5 = new Team(project,firstStudent,secondStudent,thirdStudent,fourthStudent);
		
		hm.addTeam(team1);
		hm.addTeam(team2);
		hm.addTeam(team3);
		hm.addTeam(team4);
		hm.addTeam(team5);
		
		} catch (InvalidStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProjectException e) {
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
		} catch (InvalidMemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void newTeam() {
		try {
		println("These are the shortlisted projects:");
		println(hm.stringShortlistId());
		println("Specify the project to form the team for:");
		print("Pr");
		int projectId = intScanner();

		Project project = hm.getProject("Pr" + projectId);
			
			
		println("Below are the students and their fitness for this project:");
			
		println(hm.displayStudentSuitability(project));
			
		print("Enter id for first team member: S");
		
		StudentInfoWithPrefs firstStudent = getStudentInfoWPrefs();
		print("Enter id for second team member: S");
		StudentInfoWithPrefs secondStudent = getStudentInfoWPrefs();
		print("Enter id for third team member: S");
		StudentInfoWithPrefs thirdStudent = getStudentInfoWPrefs();
		print("Enter id for fourth team member: S");
		StudentInfoWithPrefs fourthStudent = getStudentInfoWPrefs();
		
		
		//add team
		Team team = new Team(project,firstStudent,secondStudent,thirdStudent,fourthStudent);
		hm.addTeam(team);
			
		} catch (InvalidProjectException e) {
			println("Invalid Project ID");
		} catch (InvalidStudentException e) {
			println("Invalid Student");
		} catch (StudentConflictException e) {
			println("Students conflict with one another, aborting");
		} catch (PersonalityImbalanceException e) {
			println("Personality imbalance, must have at least 3 student personality types, aborting");
		} catch (RepeatedMemberException e) {
			println("Students added more than once, aborting");
		} catch (NoLeaderException e) {
			println("No leader amongst students, aborting");
		} catch (InvalidMemberException e) {
			println("Student already added to another team, aborting");
		}
		
		
	}
	
	private void dissolveTeam() {
		try {
		println("These are the teams:");
		println(hm.stringTeamId());
		println("Specify the project Id of the team you wish to dissolve:");
		Team team = getTeam();
		hm.dissolveTeam(team);
		
		} catch (InvalidTeamException e) {
			// TODO Auto-generated catch block
			println("Invalid Team, team not dissolved");
		}
	}
	private void swapStudent() {
		try {
		//team
		Team team = getTeam();

		println("Students in this team are:");

		team.getMemberIds();
		
		println("Which student do you want to substitute out?");
		print("S");
		StudentInfoWithPrefs oldStudent = getStudentInfoWPrefs();
		
		println("Which student do you want to substitute in?");
		print("S");
		StudentInfoWithPrefs newStudent;
		
			newStudent = getStudentInfoWPrefs();

		team.substituteMember(oldStudent,newStudent);
		} catch (InvalidStudentException e) {
			print("Invalid student, aborting");
		} catch (InvalidTeamException e) {
			print("Invalid team, aborting");
		} catch (StudentConflictException e) {
			// TODO Auto-generated catch block
			print("These students conflict with one another, aborting");
		} catch (PersonalityImbalanceException e) {
			// TODO Auto-generated catch block
			print("There's a personality imbalance, aborting");
		} catch (RepeatedMemberException e) {
			// TODO Auto-generated catch block
			print("Team member repeated, aborting");
		} catch (NoLeaderException e) {
			// TODO Auto-generated catch block
			print("There's no leader in this team, aborting");
		} catch (StudentNotSubstitutedException e) {
			// TODO Auto-generated catch block
			print("Students have not been substituted");
		}
	}
	private StudentInfoWithPrefs getStudentInfoWPrefs() throws InvalidStudentException {
		int studentId = intScanner();
		String studentString = "S" + studentId;
		//return student Info with Prefs
		return hm.getStudentInfoWPrefs(studentString);
	}
	private Team getTeam() throws InvalidTeamException {
		println("Select the team:");
		print("Pr");
		entryInt = intScanner();
		String projectId = "Pr" + entryInt;
		return hm.getTeam(projectId);
	}
	private void printTeamSpecificMetrics() throws InvalidTeamException {
		Team interimTeam = getTeam();
		println(interimTeam.getSuitabilityMetrics());
	}
	public void printOverallMetrics() {
		//calculate the metrics
		hm.computeSDGettingFirstNSecondPref();
		hm.computeSdSkillCompetency();
		hm.computeSDSkillShortfall();
		
		println("Overall metrics are:");
		println("Standard deviation of First and Second Preferences is: " + sd.getSdGettingFirstNSecondPref());
		println("Standard deviation of Skill Competency is: " + sd.getSdSkillCompetency());
		println("Standard deviation of Skill Shortfall is: " + sd.getSdShortfall());
	}
	public void displayFitnessMetrics() {
		//get Teams size vs student size
		int teamsFormed = hm.getTeams().size();
		
		if (projectsNeededInt == teamsFormed) {
			println("Select from the below:");
			println("1. Overall metrics");
			println("2. Team specific metrics");
			entryInt = intScanner();
			if(entryInt == 1) {
				printOverallMetrics();
			}
			if(entryInt == 2) {
				try {
					printTeamSpecificMetrics();
				} catch (InvalidTeamException e) {
					println("That's not a valid team");
				}
			}
			
		}
		else {
			try {
				throw new NotEnoughTeamsFormedException();
			} catch (NotEnoughTeamsFormedException e) {
				int remainingTeams = projectsNeededInt - teamsFormed;
				print("Not enough teams formed, " + remainingTeams + " remaining.");
			}
		}
		
		
	}

	
}
