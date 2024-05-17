package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Data.HashMaps.MyInterface;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.InvalidProjectException;
import Exceptions.InvalidStudentException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Exceptions.StudentNotSubstitutedException;
import ObjectiveFunction.AssignStudentsViaObjectiveFunction;
import ObjectiveFunction.DefaultObjectiveWeighting;
import ObjectiveFunction.MatchTeamWithMembers;
import ObjectiveFunction.ObjectiveFunction;
import ObjectiveFunction.ProjectObjectiveFunction;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Supporting.Skills;
import Objects.Supporting.StandardDeviation;

public class Computation {

	private double sdGettingFirstNSecondPref = 0.0;
	private double sdSkillCompetency = 0.0;
	private double sdShortfall = 0.0;
	private double averageGrade = 0.0;
	private HashMap<String,Team> tempTeams = new HashMap<String, Team>();
	
	
	private void calculateAverageGrade(HashMap<String, Team> t) {
		double runningGrade = 0.0;
		Iterator it = t.entrySet().iterator();
		// go through list of students with projects
		
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			runningGrade = ((StudentInfoWithPrefs)pair.getValue()).getAverageGrade();
			
		}
		
		
		runningGrade = runningGrade / t.entrySet().size();
		
		setAverageGrade(runningGrade);
	}
	
	//standard deviation skill competency
	public void computeSdSkillCompetency(HashMap<String, Team> teams) {
		
		ArrayList<Double> percentages = new ArrayList<Double>();
		for(Map.Entry entry : teams.entrySet()) {
			percentages.add(((Team) entry.getValue()).getAverageSkillCompetency());
		}
		StandardDeviation obj = new StandardDeviation();
		MyInterface ref = obj::stDev; 
		sdSkillCompetency = ref.display(percentages);
	}

	
	public void recalculateAllTeamMetrics(HashMap<String,Team> teams) {
		teams.entrySet().forEach(entry->{
		    ( entry).getValue().recalculateMetrics();  
		 });
		
	}
	//standard deviation percent members getting first and second project preferences
	public void computeSDGettingFirstNSecondPref(HashMap<String, Team> teams) {
		ArrayList<Double> percentages = new ArrayList<Double>();
		for(Map.Entry entry : teams.entrySet()) {
			percentages.add(((Team) entry.getValue()).getFirstAndSecondPrefPercent());
		}
		StandardDeviation obj = new StandardDeviation();
		MyInterface ref = obj::stDev; 
		sdGettingFirstNSecondPref = ref.display(percentages);
	}
	//standard deviation skill shortfall
	public void computeSDSkillShortfall(HashMap<String, Team> teams) {
		ArrayList<Double> percentages = new ArrayList<Double>();
		for(Map.Entry entry : teams.entrySet()) {
			percentages.add(((Team) entry.getValue()).getSkillShortfall());
		}
		StandardDeviation obj = new StandardDeviation();
		MyInterface ref = obj::stDev; 
		sdShortfall = ref.display(percentages);
	}
	public double getFirstAndSecondPref() {
		return sdGettingFirstNSecondPref;
	}
	public double getSkillCompetency() {
		return sdSkillCompetency;
	}
	public double getShortfall() {
		return sdShortfall;
	}
	public double getSdGettingFirstNSecondPref() {
		return sdGettingFirstNSecondPref;
	}

	public void setSdGettingFirstNSecondPref(double sdGettingFirstNSecondPref) {
		this.sdGettingFirstNSecondPref = sdGettingFirstNSecondPref;
	}

	public double getSdSkillCompetency() {
		return sdSkillCompetency;
	}

	public void setSdSkillCompetency(double sdSkillCompetency) {
		this.sdSkillCompetency = sdSkillCompetency;
	}

	public double getSdShortfall() {
		return sdShortfall;
	}

	public void setSdShortfall(double sdShortfall) {
		this.sdShortfall = sdShortfall;
	}
	public String displayStudentSuitability(Project proj, HashMap<String,StudentInfoWithPrefs> unassignedStudents) {
		String outOutput = "";
		for(Map.Entry entry : unassignedStudents.entrySet()) {
			Skills skill = ((StudentInfoWithPrefs) entry.getValue()).getSkills();
			String pref1 = ((StudentInfoWithPrefs) entry.getValue()).getPreferenceOne();
			String pref2 = ((StudentInfoWithPrefs) entry.getValue()).getPreferenceTwo();
			String pref3 = ((StudentInfoWithPrefs) entry.getValue()).getPreferenceThree();
			String pref4 = ((StudentInfoWithPrefs) entry.getValue()).getPreferenceFour();
			
			int skillVariance = compareAllSkills(skill,proj.getSkills());
			double anSkillGap = 0.0;
			double netSkillGap = 0.0;
			double progSkillGap = 0.0;
			double webSkillGap = 0.0;
			if (proj.getAnSkill()>skill.getAnSkill()) {
				anSkillGap = proj.getAnSkill() - skill.getAnSkill();
			}
			if (proj.getNetSkill()>skill.getNetSkill()) {
				netSkillGap = proj.getNetSkill() - skill.getNetSkill();
			}
			if (proj.getProgSkill()>skill.getProgSkill()) {
				progSkillGap = proj.getProgSkill() - skill.getProgSkill();
			}
			if (proj.getWebSkill()>skill.getWebSkill()) {
				webSkillGap = proj.getWebSkill() - skill.getWebSkill();
			}
			
			String preferenceText = "not in this student's preferences";
			if(proj.getID()==pref1 || proj.getID()==pref2) {
				preferenceText = " in the student's top two preferences";
			}
			if(proj.getID()==pref3 || proj.getID()==pref4) {
				preferenceText = " in the student's bottom two preferences";
			}
			outOutput += "Student " + entry.getKey() + " has: \n"+"A skill variance of " + skillVariance + " for Project " +proj.getID()+ " \n";
			outOutput += "Criteria P " + proj.getProgSkill() +" N "+ proj.getNetSkill() + " A " + proj.getAnSkill() + " W " + proj.getWebSkill() + " \n";
			outOutput += "Analytics and Big Data skill is " + skill.getAnSkill() + "(Gap of = " + anSkillGap + ") \n";
			outOutput += "Networking and Security skill is " + skill.getNetSkill() + "(Gap of = " + netSkillGap + ") \n";
			outOutput += "Programming & Software Engineering skill is " + skill.getProgSkill() + "(Gap of = " + progSkillGap + ") \n";
			outOutput += "Web & Mobile applications skill is " + skill.getWebSkill() + "(Gap of = " + webSkillGap + ") \n";
			outOutput += "This project is " + preferenceText + " \n \n \n";
		}
		return outOutput;
	}
	
	private int compareAllSkills(Skills skills1, Skills skills2) {
		int anSkill = (int) Math.abs(skills1.getAnSkill() - skills2.getAnSkill());
		int netSkill = (int) Math.abs(skills1.getNetSkill() - skills2.getNetSkill());
		int progSkill = (int) Math.abs(skills1.getProgSkill() - skills2.getProgSkill());
		int webSkill = (int) Math.abs(skills1.getWebSkill() - skills2.getWebSkill());
		return anSkill+netSkill+progSkill+webSkill;
	}
	//calculate the current value
	private double calculateObjectiveFunction(DefaultObjectiveWeighting weighting, HashMap<String,Team> t) {
		calculateAverageGrade(t);
		double runningTotal = 0.0;
		ObjectiveFunction of;
		of = new ObjectiveFunction();
		Iterator it = t.entrySet().iterator();
		// O(n) students array complexity
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Team currentTeam = (Team)pair.getValue();
			runningTotal += of.calculateFinalObjectiveFunction(currentTeam, averageGrade);
			}
		return runningTotal;
	}
	
	public String autoCalculateObjectiveFunction(DefaultObjectiveWeighting weighting, HashMap<String,Team> teams, HashMap<String,StudentInfoWithPrefs> studs, HashMap<String,Project> shortlist) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		
		HashMap<String,StudentInfoWithPrefs> unassignedStuds = new HashMap<String,StudentInfoWithPrefs>();
		unassignedStuds.putAll(studs);
		calculateAverageGrade(teams);
		// leaders
		HashMap<String, ProjectObjectiveFunction> leaders = addleaderStudents(weighting,unassignedStuds, shortlist);
		HashMap<String, ProjectObjectiveFunction> secondStudent = addStudents(weighting,unassignedStuds, shortlist);
		HashMap<String, ProjectObjectiveFunction> thirdStudent = addStudents(weighting,unassignedStuds, shortlist);
		HashMap<String, ProjectObjectiveFunction> fourthStudent = addStudents(weighting,unassignedStuds, shortlist);
		
		
		//student, projectOf (projId,ObjectiveValue)
		HashMap<String,ArrayList<String>> project = new HashMap<String,ArrayList<String>>();
		project = addStudentsToHashMap(project,leaders,secondStudent,thirdStudent,fourthStudent);
		

		//assign to a structure and pass back to the controller
		//assign to temporary hashmap of teams, seperately allow controller replace main teams with tempTeams
		assignStudentsToTempTeams(project,teams,studs);
		double newObjValue = calculateObjectiveFunction(weighting, tempTeams);
		System.out.println("New Objective Value - " + newObjValue);
		
		//let controller decide to submit or not
		return stringAlgorithmsOutput(newObjValue, weighting, teams);
	}	
	
	private HashMap<String, ArrayList<String>> addStudentsToHashMap(HashMap<String,ArrayList<String>> project, HashMap<String, ProjectObjectiveFunction> leaders, HashMap<String, ProjectObjectiveFunction> secondStudent, HashMap<String, ProjectObjectiveFunction> thirdStudent, HashMap<String, ProjectObjectiveFunction> fourthStudent) {
		//leaders
		addToHashMaps(project,leaders);
		//secondStudent
		addToHashMaps(project,secondStudent);
		//thirdStudent
		addToHashMaps(project,thirdStudent);
		//fourthStudent
		addToHashMaps(project,fourthStudent);
		return project;
	}
	
	private void addToHashMaps(HashMap<String,ArrayList<String>> project, HashMap<String, ProjectObjectiveFunction> studentsToAdd) {
		Iterator it = studentsToAdd.entrySet().iterator();
		// O(n) students array complexity
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			ProjectObjectiveFunction currentTeam = (ProjectObjectiveFunction)pair.getValue();
			String stud = (String) pair.getKey();
			String proj = currentTeam.getProjectId();
			if(project.containsKey(proj)) {
				project.get(proj).add(stud);
			} else {
				ArrayList<String> studArray = new ArrayList<String>();
				studArray.add(stud);
				project.put(proj, studArray);
			}
		}
	}
	

	private String stringAlgorithmsOutput(double newObjectiveFunction, DefaultObjectiveWeighting functionWeights,HashMap<String,Team> teams) {
		//get the current objective function value
		double originalObjective = calculateObjectiveFunction(functionWeights, teams);
		System.out.println("Old Objective Value - " + originalObjective);		
		return "Original objective function value is " + originalObjective + ", new objective function value is " + newObjectiveFunction + ". Accept auto-switch?";
	}
	
	private HashMap<String,ProjectObjectiveFunction> addStudents(DefaultObjectiveWeighting objWeights,HashMap<String, StudentInfoWithPrefs> unassignedStuds, HashMap<String, Project> shortlist) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		HashMap<String, ProjectObjectiveFunction> studentMap = new HashMap<String, ProjectObjectiveFunction>();
		ArrayList<MatchTeamWithMembers> matches = new ArrayList<MatchTeamWithMembers>();
		
		shortlist.entrySet().forEach(entry->{
			//maybe launch new thread here in future
			
				MatchTeamWithMembers match = null;
				try {
					match = new MatchTeamWithMembers(( entry).getValue(), objWeights);
				
				match.compareUnassignedConstrained(unassignedStuds, averageGrade);
				//make this step synchronized, if another thread finishes 
				matches.add(match);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}); //hm

		//loop through the projects and assign students using sorted lists
		AssignStudentsViaObjectiveFunction assign = new AssignStudentsViaObjectiveFunction();
		studentMap.putAll(assign.assignProjectGreedyAlgorithm(matches));
		removeUnassignedStudents(unassignedStuds,studentMap);
		return studentMap;
	}

	//once assigned, remove the students from the queue
	private void removeUnassignedStudents(HashMap<String, StudentInfoWithPrefs> unassignedStuds, HashMap<String, ProjectObjectiveFunction> leaderMap) {
		Iterator it = leaderMap.entrySet().iterator();
		// go through list of students with projects
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			String stud =  (String) pair.getKey();
			unassignedStuds.remove(stud);
		}
	}
	
	private HashMap<String, ProjectObjectiveFunction> addleaderStudents(DefaultObjectiveWeighting objWeights, HashMap<String, StudentInfoWithPrefs> unassignedStuds,HashMap<String,Project> shortlist) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		HashMap<String, ProjectObjectiveFunction> leaderMap = new HashMap<String, ProjectObjectiveFunction>();
		ArrayList<MatchTeamWithMembers> matches = new ArrayList<MatchTeamWithMembers>();
		shortlist.entrySet().forEach(entry->{
			//maybe launch new thread here in future
			
				MatchTeamWithMembers match = null;
				try {
					match = new MatchTeamWithMembers(( entry).getValue(), objWeights);
				} catch (IDOutOfRangeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				match.compareUnassignedLeaders(unassignedStuds,averageGrade);
				//make this step synchronized, if another thread finishes 
				matches.add(match);
				//something else here 
			
		}); //hm
		
		//loop through the projects and assign students using sorted lists
		AssignStudentsViaObjectiveFunction assign = new AssignStudentsViaObjectiveFunction();
		leaderMap.putAll(assign.assignProjectGreedyAlgorithm(matches));
		removeUnassignedStudents(unassignedStuds,leaderMap);
		return leaderMap;
	}
	
	private ArrayList<Double> addObjectiveWeightsToFunction(double a, double b, double c) {
		ArrayList<Double> array = new ArrayList<Double>();
		array.add(a);
		array.add(b);
		array.add(c);
		return array;
	}
	
	public void setAverageGrade(double runningGrade) {
		this.averageGrade = runningGrade;
		
	}
	private void assignStudentsToTempTeams(HashMap<String,ArrayList<String>> project, HashMap<String, Team> teams, HashMap<String, StudentInfoWithPrefs> studs) {
		try {
		Iterator it = project.entrySet().iterator();
		// go through list of students with projects
		
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			ArrayList<String> studentString = (ArrayList<String>) pair.getValue();
			String projId = (String) pair.getKey();
			//get the student Objects
			ArrayList<StudentInfoWithPrefs> students = new ArrayList<StudentInfoWithPrefs>();
			for(int i=0; i<studentString.size();i++) {
					StudentInfoWithPrefs s = studs.get(studentString.get(i));
					students.add(s);
			}
			//add them to a team
			Team team = new Team(teams.get(projId),students);
			tempTeams.put(projId,team);
		}
		}  catch (IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, Team> getTempTeams() {
		return tempTeams;
	}
	
}
