package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;


import Controllers.AddResourcesController;
import Controllers.MenuController;
import Controllers.TeamMetricsController;
import Controllers.UpdateStudentsController;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.InvalidMemberException;
import Exceptions.InvalidProjectException;
import Exceptions.InvalidSKeyException;
import Exceptions.InvalidStudentException;
import Exceptions.InvalidTeamException;
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
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Supporting.PrefCount;
import Objects.Supporting.PrefCountSorter;
import Objects.Supporting.Skills;
import SharedValues.AverageGrade;
import SharedValues.Count;
import SharedValues.StandardDeviationValues;


public class HashMaps implements Serializable, Cloneable {
	//hold only the parent class
	private TeamMetricsController tmControl = null;
	private MenuController mControl = null;
	private UpdateStudentsController usControl = null;
	private AddResourcesController arControl = null;

	
	// create one students hashmap then downcast
	
	private static final long serialVersionUID = -9002152891627906657L;
	private HashMap<String,Company> companies = new HashMap<String, Company>();
	private HashMap<String,Owner> owners = new HashMap<String, Owner>();
	//remove
	private HashMap<String,StudentInfo> studentInfo = new HashMap<String, StudentInfo>();
	private HashMap<String,Project> projects = new HashMap<String, Project>();
	private HashMap<String,Student> students = new HashMap<String, Student>();
	private HashMap<String,Preferences> prefs = new HashMap<String,Preferences>();
	private HashMap<String,Project> shortlist = new HashMap<String, Project>();
	//remove
	private HashMap<String,Team> teams = new HashMap<String, Team>();
	private HashMap<String,Team> tempTeams = new HashMap<String, Team>();
	
	private ArrayList<PrefCount> prefCounts = new ArrayList<PrefCount>();
	//remove
	private HashMap<String,StudentInfoWithPrefs> studentInfoWPrefs = new HashMap<String, StudentInfoWithPrefs>();
	//change to Student
	private HashMap<String,StudentInfoWithPrefs> unassignedStudents = new HashMap<String, StudentInfoWithPrefs>();
	
	private AverageGrade average = AverageGrade.getSingleton();
	private Count count = Count.getSingleton();
	private StandardDeviationValues sd = StandardDeviationValues.getSingleton();
	
	//private int studentCount = 0;
	//private int projectsCount = 0;
	//private double sdGettingFirstNSecondPref = 0.0;
	//private double sdSkillCompetency = 0.0;
	//private double sdShortfall = 0.0;
	//private double averageGrade = 0.0;
	
	//push the teams metrics into another class
	

	
	
private static HashMaps _instance;
	

	
	private HashMaps() {}
	public static synchronized HashMaps getSingleton() {
		if(_instance == null) {
			_instance = new HashMaps();
		}
		return _instance;
	}

	
	private void calculateAverageGrade() {
		average.calcAndSetAverageGrade(studentInfoWPrefs);

	}
	

	public Project getProject(String projectId) throws InvalidProjectException {

		if(shortlist.containsKey(projectId)) {
			Project project = shortlist.get(projectId);
			return project;
		}
		else {
			throw new InvalidProjectException();
		}
	}
	public Project getProjectNoErrors(String projectId) {
			Project project = shortlist.get(projectId);
			return project;
	}
	public HashMap<String,Project> getShortlist() {
		return shortlist;
	}
	
	public String[] getShortlistIds() {
		String []  ids = (String[]) shortlist.keySet().toArray(new String[0]);
		System.out.println("The ids size is " + ids.length + "vs Shortlist Size" + shortlist.size());
		return ids;
	}
	
	public Team getTeam(String projectId) throws InvalidTeamException {
		if (teams.containsKey(projectId)) {
			return teams.get(projectId);
		}
		else {
			throw new InvalidTeamException();
		}
	}
	public void addStudentToTeam(String teamId, String studentId) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, InvalidStudentException {
		if (unassignedStudents.get(studentId) == null) {
			throw new RepeatedMemberException();
		}
		else {
			unassignedStudents.remove(studentId);
			StudentInfoWithPrefs s1 = getStudentInfoWPrefs(studentId);		
			teams.get(teamId).addStudent(s1);
		}
	}
	
	public Team createInterimTeam(String team1Id, String oldStudent, String newStudent) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, StudentNotSubstitutedException, InvalidStudentException, InvalidProjectException {
		StudentInfoWithPrefs newStudentObject = getStudentInfoWPrefs(newStudent);
		ArrayList<StudentInfoWithPrefs> members = teams.get(team1Id).returnAllMembersApartFromSpecifiedMember(oldStudent);
		members.add(newStudentObject);
		Team interimTeam = new Team(getProject(team1Id),members.get(0),members.get(1),members.get(2),members.get(3));
		return interimTeam;
	}
	
	public Team createFinalTeam(String team1Id, String oldStudent, String newStudent) {
		StudentInfoWithPrefs newStudentObject = getStudentInfoWPrefsNoError(newStudent);
		ArrayList<StudentInfoWithPrefs> members = teams.get(team1Id).returnAllMembersApartFromSpecifiedMember(oldStudent);
		members.add(newStudentObject);
		Boolean noExceptionsThrown = true;
		//constructor that does not throw exceptions
		Team interimTeam = new Team(getProjectNoErrors(team1Id),members.get(0),members.get(1),members.get(2),members.get(3),noExceptionsThrown);
		return interimTeam;
	}
	
	
	public void removeTeamMember(String teamId, String studentId) {
		teams.get(teamId).removeMember(studentId);
		unassignedStudents.put(studentId, studentInfoWPrefs.get(studentId));
	}
	

	//Check a team of members
	public void invalidMemberCheck(ArrayList<StudentInfoWithPrefs> studs) throws InvalidMemberException {
		for(int i=0;i<studs.size();i++) {
			StudentInfoWithPrefs stud = studs.get(i);
			for(Map.Entry entry : teams.entrySet()) {
				if(((Team) entry.getValue()).getMembers().contains(stud)) {
					throw new InvalidMemberException();
				}
			}
		}
	}
	//Check an individual hasn't been already added
	public void invalidMemberCheck(StudentInfoWithPrefs stud) throws InvalidMemberException {
		for(Map.Entry entry : teams.entrySet()) {
			if(((Team) entry.getValue()).getMembers().contains(stud)) {
				throw new InvalidMemberException();
			} 
		}
	}	
	public void setCompanies(HashMap<String,Company> comps) {
		companies.putAll(comps);
	}
	public void setOwners(HashMap<String,Owner> own) {
		owners.putAll(own);
	}
	public void setStudentInfo(HashMap<String,StudentInfo> sInfo) {
		studentInfo.putAll(sInfo);
	}
	public void setProjectInfo(HashMap<String,Project> projs) {
		projects.putAll(projs);
	}
	public void setStudents(HashMap<String,Student> studs) {
		students.putAll(studs);
	}
	public void setPreferences(HashMap<String,Preferences> pref) {
		prefs.putAll(pref);
	}
	public void setShortlist(HashMap<String,Project> proj) {
		shortlist.putAll(proj);
	}
	public void addToShortlist(String shortlistProject) {
		shortlist.put(shortlistProject, projects.get(shortlistProject));
	}

	public String stringShortlistId() {
		String outOutput = "";
		for(Map.Entry entry : shortlist.entrySet()) {
			 outOutput += entry.getKey() + "\n";
		}
		return outOutput;
	}
	public String stringTeamId() {
		String outOutput = "";
		for(Map.Entry entry : teams.entrySet()) {
			 outOutput += entry.getKey() + "\n";
		}
		return outOutput;
	}
	public String stringProjects() {
		String outOutput = "";
		for(Map.Entry entry : projects.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
	public String stringShortlist() {
		String outOutput = "";
		for(Map.Entry entry : shortlist.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		
		return outOutput;
	}	
	public String stringStudents() {
		String outOutput = "";
		for(Map.Entry entry : students.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
	public String stringCompanies() {
		String outOutput = "";
		for(Map.Entry entry : companies.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
	public String stringOwners() {
		String outOutput = "";
		for(Map.Entry entry : owners.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
 	public String stringStudentInfo() {
		String outOutput = "";
		for(Map.Entry entry : studentInfo.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
	public String stringStudentPreferences() {
		String outOutput = "";
		for(Map.Entry entry : prefs.entrySet()) {
			 outOutput += entry.getValue().toString() + "\n";
		}
		return outOutput;
	}
	public double getFirstAndSecondPref() {
		return sd.getSdGettingFirstNSecondPref();
	}
	public double getSkillCompetency() {
		return sd.getSdSkillCompetency();
	}
	public double getShortfall() {
		return sd.getSdShortfall();
	}
	public void makeShortlist(ArrayList<String> removeKeys) {
		shortlist.putAll(projects);
		for (int i=0;i<removeKeys.size();i++) {
			shortlist.remove(removeKeys.get(i));
		}
	}

	
	public ArrayList<String> returnKeysToRemove(Integer qtyToRemove) {
		
		PrefCountSorter prefCountSorter = new PrefCountSorter(prefCounts);
		ArrayList<PrefCount> sortedPrefCount = prefCountSorter.getSortedPrefCountByValue();
		
		ArrayList<String> projectsToRemove = new ArrayList<String>();
		
		for(int i=0;i<qtyToRemove;i++) {
			projectsToRemove.add(sortedPrefCount.get(i).getID());
		}
		
		return projectsToRemove;
	}
	
	public String stringPrefCount() {
		String outOutput = "";
		for (int i=0;i<prefCounts.size();i++) {
			 outOutput += prefCounts.get(i).toString() + "\n";
		}
		return outOutput;
	}
	
	public String stringStudentIds() {
		String outOutput = "";
		Iterator iter = students.entrySet().iterator();
		while (iter.hasNext()) {
	        Map.Entry pair = (Map.Entry)iter.next();
		}
		return outOutput;
	}
	
	public void addPreferences(Preferences prefs) throws IdAlreadyUsedException {
		prefsDuplicate(prefs.getID());
		this.prefs.put(prefs.getID(), prefs);
		
		addFour(prefs.getProjectPreferences().get(0));
		addThree(prefs.getProjectPreferences().get(1));
		addTwo(prefs.getProjectPreferences().get(2));
		addOne(prefs.getProjectPreferences().get(3));
	}
	
	public void addFour(String str) {
		
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 4;
				prefCounts.get(i).setCount(value);
			}
		}
	}
	
	public void addThree(String str) {
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 3;
				prefCounts.get(i).setCount(value);
			}
		}
	}

	public void addTwo(String str) {
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 2;
				prefCounts.get(i).setCount(value);
			}
		};
	}
	
	public void addOne(String str) {
		
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 1;
				prefCounts.get(i).setCount(value);
			}
		}
	}
		
	public void hasKey(String key) throws InvalidSKeyException {
		if (students.containsKey(key)) {	
		}
		else {
			throw new InvalidSKeyException();
		}
			
	}
	
	public Student getStudent(String key) throws InvalidSKeyException {
		hasKey(key);
		return students.get(key);
	}
	
	public void addCompany(Company comp) throws IdAlreadyUsedException {
		companiesDuplicate(comp.getID());
		companies.put(comp.getID(), comp);
	}
	public void addOwner(Owner own) throws IdAlreadyUsedException {
		ownersDuplicate(own.getID());
		owners.put(own.getID(), own);
	}
	public void addStudInfo(StudentInfo sInfo) throws IdAlreadyUsedException {
		prefsDuplicate(sInfo.getID());
		studentInfo.put(sInfo.getID(), sInfo);
	}	
	
	public void addProject(Project proj) throws IdAlreadyUsedException {
		projectDuplicate(proj.getID());
		projects.put(proj.getID(), proj);
		PrefCount pc = new PrefCount(proj.getID(),0);
		prefCounts.add(pc);
		setProjectCount(projects.size());
	}
	
	public void addStudents(Student proj) throws IdAlreadyUsedException {
		studentsDuplicate(proj.getID());
		students.put(proj.getID(), proj);
		setStudentCount(students.size());
	}		
	

	
	public void projectDuplicate(String ID) throws IdAlreadyUsedException {
			if (projects.containsKey(ID)) {
			throw new IdAlreadyUsedException();
			}	
	}	
	
	public void companiesDuplicate(String ID) throws IdAlreadyUsedException {
		if (companies.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}
	
	
	public void ownersDuplicate(String ID) throws IdAlreadyUsedException {
		if (owners.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}	
	
	
	public void prefsDuplicate(String ID) throws IdAlreadyUsedException {
		if (prefs.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}

	
	public void studentsDuplicate(String ID) throws IdAlreadyUsedException {
		if (students.containsKey(ID)) {
			throw new IdAlreadyUsedException();
		}		
}

	public Integer getStudentCount() {
		return count.getStudentCount();
	}

	public void setStudentCount(Integer studentCount) {
		count.setStudentCount(studentCount);
	}

	public Integer getProjectsCount() {
		return count.getProjectCount();
	}

	public void setProjectCount(Integer projectsCount) {
		count.setProjectCount(projectsCount);
	}

	public HashMap<String, Team> getTeams() {
		return teams;
	}
	
	public void dissolveTeam(Team team) {
		if(teams.containsValue(team)) {
			//get all the students
			ArrayList<StudentInfoWithPrefs> studs = teams.get(team.getID()).getMembers();
			//add them back to the unassigned list
			for(int i = 0; i<studs.size();i++) {
				unassignedStudents.put(studs.get(i).getID(),studs.get(i));
			}
			//dissolve team
			teams.remove(team.getID(), team);
		}
	}
	public String displayStudentSuitability(Project proj) {
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
	
	public StudentInfoWithPrefs getStudentInfoWPrefs(String student) throws InvalidStudentException {
		if (studentInfoWPrefs.containsKey(student)) {
			return studentInfoWPrefs.get(student);
		}
		else {
			throw new InvalidStudentException();
		}
	}
	public StudentInfoWithPrefs getStudentInfoWPrefsNoError(String student) {
		return studentInfoWPrefs.get(student);
	}
	
	public void setStudentInfoWPrefs(HashMap<String,StudentInfoWithPrefs> studentInfoWPrefs) {
		this.studentInfoWPrefs = studentInfoWPrefs;
	}
	public void mergeStudentInfoAndPreferences() throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		for(Map.Entry entry : studentInfo.entrySet()) {
			if (prefs.containsKey(entry.getKey())) {
				StudentInfo studInfo = (StudentInfo) entry.getValue();
				Preferences preferences = prefs.get(entry.getKey());
				StudentInfoWithPrefs withPrefs = new StudentInfoWithPrefs(studInfo,preferences);
				studentInfoWPrefs.put((String) entry.getKey(),withPrefs);
			}
		}
	}
	public void addStudentInfoWithPreferences(StudentInfoWithPrefs studentWPrefs) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
		studentInfoWPrefs.put(studentWPrefs.getID(),studentWPrefs);
	}
	
	
	public void addTeam(Team team) throws InvalidMemberException {
		invalidMemberCheck(team.getMembers());
		ArrayList<StudentInfoWithPrefs> students = team.getMembers();
		
		//remove students from the unassigned pool
		for(int i = 0; i < students.size();i++) {
			unassignedStudents.remove(students.get(i).getID());			
		}
		this.teams.put(team.getID(), team);
	}


	   public void setMetricsController(TeamMetricsController control) // A handle to the controller
	   {	   this.tmControl = control;
	   }

	   public void setMenuController(MenuController control) // A handle to the controller
	   {	   this.mControl = control;
	   }
	   
	   public void setUpdateStudentsController(UpdateStudentsController control) // A handle to the controller
	   {	   this.usControl = control;
	   }
	   
	   public void setAddResourcesController(AddResourcesController control) // A handle to the controller
	   {	   this.arControl = control;
	   }
	   
	   
	   //adds all the students into a hashmap that can be used to assign to projects
	   public void addAllStudentsToUnassignedStudentsHashMap() {
		   unassignedStudents.putAll(studentInfoWPrefs);
	}
	

	   //adds all the students into a hashmap that can be used to assign to projects
	   public ArrayList<String> getUnassignedStudents() {
		   if (unassignedStudents.size()==0) {
			   ArrayList<String> keys = new ArrayList<String>();
			   keys.add("");
			   return keys;
		   }
		   else {
			   ArrayList<String> keys = new ArrayList<>(unassignedStudents.keySet());
			   return keys;  
		   }

	}
	   
	   //adds all the students into a ArrayList that can be written to a database
	   public ArrayList<StudentInfoWithPrefs> getArrayStudentInfoWithPrefs() {
			   ArrayList<StudentInfoWithPrefs> values = new ArrayList<>(studentInfoWPrefs.values());
			   return values;  
	}
	   //adds all the students into a ArrayList that can be written to a database
	   public HashMap<String, StudentInfoWithPrefs> getStudentInfoWithPrefs() {
			   return studentInfoWPrefs;  
	}
	   
	   //adds all the Teams into a ArrayList that can be written to a database
	   public ArrayList<Team> getArrayTeams() {
			   ArrayList<Team> values = new ArrayList<>(teams.values());
			   return values;  
	}
	   
	   //adds all the Companies into a ArrayList that can be written to a database
	   public ArrayList<Company> getArrayCompanies() {
			   ArrayList<Company> values = new ArrayList<>(companies.values());
			   return values;  
	}
	   //adds all the Projects into a ArrayList that can be written to a database
	   public ArrayList<Project> getArrayProjects() {
			   ArrayList<Project> values = new ArrayList<>(projects.values());
			   return values;  
	}
	   //adds all the Projects into a ArrayList that can be written to a database
	   public ArrayList<Owner> getArrayOwners() {
			   ArrayList<Owner> values = new ArrayList<>(owners.values());
			   return values;  
	}
	public ArrayList<Project> getArrayShortList() {
		   ArrayList<Project> values = new ArrayList<>(shortlist.values());
		   return values;  
	}
	   
	public void printCurrentHashMaps() {
		 System.out.println();
		 System.out.println("Companies:");
			companies.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			System.out.println();
			System.out.println("Owners:");
			owners.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			System.out.println();
			System.out.println("StudentInfo:");
			studentInfo.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			System.out.println();
			System.out.println("Projects:");
			projects.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			System.out.println();
			System.out.println("Shortlist:");
			shortlist.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			System.out.println();
			System.out.println("Teams:");
			teams.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			teams.entrySet().forEach(entry->{
			    System.out.print(( entry).getValue().getMemberIds());  
			 }); //hm
			
			
			
			System.out.println();
			System.out.println("StudentInfoWPrefs:");
			studentInfoWPrefs.entrySet().forEach(entry->{
			    System.out.print(( entry).getKey());  
			 }); //hm
			
	 }
	
	
	public void reverseSwap(String team1, String team2, String stud1, String stud2) {
		try {
			//
			if(getTeam(team1).hasStudent(stud2)) {
				getTeam(team1).substituteStudent(stud2, getStudentInfoWPrefs(stud1));
			}
			if(getTeam(team2).hasStudent(stud1)) {
				getTeam(team2).substituteStudent(stud1, getStudentInfoWPrefs(stud2));
			}
		} catch (InvalidTeamException | InvalidStudentException e) {
			e.printStackTrace();
		}
		
	}
	public void recalculateAllTeamMetrics() {
		teams.entrySet().forEach(entry->{
		    ( entry).getValue().recalculateMetrics();  
		 });
		
	}
	public void replaceTeam(String oldTeamId, Team newTeam) {
		teams.entrySet().forEach(entry->{
		    if(( entry).getKey().equals(oldTeamId)) {
		    	( entry).setValue(newTeam);
		    }
		 });
	}

	private ArrayList<Double> addObjectiveWeightsToFunction(double a, double b, double c) {
		ArrayList<Double> array = new ArrayList<Double>();
		array.add(a);
		array.add(b);
		array.add(c);
		return array;
	}
	
	private HashMap<String, ProjectObjectiveFunction> addleaderStudents(DefaultObjectiveWeighting objWeights, HashMap<String, StudentInfoWithPrefs> unassignedStuds) {
		HashMap<String, ProjectObjectiveFunction> leaderMap = new HashMap<String, ProjectObjectiveFunction>();
		ArrayList<MatchTeamWithMembers> matches = new ArrayList<MatchTeamWithMembers>();
		shortlist.entrySet().forEach(entry->{
			//maybe launch new thread here in future
			
				MatchTeamWithMembers match = new MatchTeamWithMembers(( entry).getValue(), objWeights);
				match.compareUnassignedLeaders(unassignedStuds,average.getAverageGrade());
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
	
	
	private HashMap<String,ProjectObjectiveFunction> addStudents(DefaultObjectiveWeighting objWeights,HashMap<String, StudentInfoWithPrefs> unassignedStuds) {
		HashMap<String, ProjectObjectiveFunction> studentMap = new HashMap<String, ProjectObjectiveFunction>();
		ArrayList<MatchTeamWithMembers> matches = new ArrayList<MatchTeamWithMembers>();
		
		shortlist.entrySet().forEach(entry->{
			//maybe launch new thread here in future
			
				MatchTeamWithMembers match = null;
				match = new MatchTeamWithMembers(( entry).getValue(), objWeights);
				match.compareUnassignedConstrained(unassignedStuds, average.getAverageGrade());
				//make this step synchronized, if another thread finishes 
				matches.add(match);
	}); //hm

		//loop through the projects and assign students using sorted lists
		AssignStudentsViaObjectiveFunction assign = new AssignStudentsViaObjectiveFunction();
		studentMap.putAll(assign.assignProjectGreedyAlgorithm(matches));
		removeUnassignedStudents(unassignedStuds,studentMap);
		return studentMap;
	}
	
	
	private void assignStudentsToTempTeams(HashMap<String,ArrayList<String>> project) {
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
					StudentInfoWithPrefs s = getStudentInfoWPrefs(studentString.get(i));
					students.add(s);
			}
			//add them to a team
			Team team = new Team(getProject(projId),students);
			tempTeams.put(projId,team);
		}
		} catch (InvalidProjectException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (InvalidStudentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	
	public String autoCalculateObjectiveFunction(DefaultObjectiveWeighting weighting) {
		
		HashMap<String,StudentInfoWithPrefs> unassignedStuds = new HashMap<String,StudentInfoWithPrefs>();
		unassignedStuds.putAll(studentInfoWPrefs);
		average.calcAndSetAverageGrade(studentInfoWPrefs);
		// leaders
		HashMap<String, ProjectObjectiveFunction> leaders = addleaderStudents(weighting,unassignedStuds);
		HashMap<String, ProjectObjectiveFunction> secondStudent = addStudents(weighting,unassignedStuds);
		HashMap<String, ProjectObjectiveFunction> thirdStudent = addStudents(weighting,unassignedStuds);
		HashMap<String, ProjectObjectiveFunction> fourthStudent = addStudents(weighting,unassignedStuds);
		
		
		//student, projectOf (projId,ObjectiveValue)
		HashMap<String,ArrayList<String>> project = new HashMap<String,ArrayList<String>>();
		project = addStudentsToHashMap(project,leaders,secondStudent,thirdStudent,fourthStudent);
		

		//assign to a structure and pass back to the controller
		//assign to temporary hashmap of teams, seperately allow controller replace main teams with tempTeams
		assignStudentsToTempTeams(project);
		double newObjValue = calculateObjectiveFunction(weighting, tempTeams);
		System.out.println("New Objective Value - " + newObjValue);
		
		//let controller decide to submit or not
		return stringAlgorithmsOutput(newObjValue, weighting);
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
	
//	public void giveTeamsObjectiveFunction(DefaultObjectiveWeighting obWeight) {
//		try {
//		ObjectiveFunction of = new ObjectiveFunction(obWeight);
//		Iterator it = teams.entrySet().iterator();
//		// O(n) students array complexity
//		while(it.hasNext()) {
//			Map.Entry pair = (Map.Entry)it.next();
//			Team currentTeam = (Team)pair.getValue();
//			of.calculateFinalObjectiveFunction(currentTeam, averageGrade);
//			currentTeam.setOf(of);
//			}
//		} catch (NotOneHundredPercentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	
	//change this to call something in the controller to display current value
	
	public void assignTempTeamsToTeams() {
		teams = tempTeams;
	}
	
	
//	public String compareBadTeams() {
		DefaultObjectiveWeighting obWeight = DefaultObjectiveWeighting.getInstance();
		
//		giveTeamsObjectiveFunction();
		
		//order teams by objective function
//		for(int i=0;i<teams.size();i++) {
			
//		}
		//get the worst student
		
		//new thread
		
		//get the next worst team
		
		//continue until half way through teams
//	}
	
	
	private String stringAlgorithmsOutput(double newObjectiveFunction, DefaultObjectiveWeighting functionWeights) {
		//get the current objective function value
		double originalObjective = calculateObjectiveFunction(functionWeights, teams);
		System.out.println("Old Objective Value - " + originalObjective);		
		return "Original objective function value is " + originalObjective + ", new objective function value is " + newObjectiveFunction + ". Accept auto-switch?";
	}
	
	//calculate the current value
	private double calculateObjectiveFunction(DefaultObjectiveWeighting weighting, HashMap<String,Team> t) {
		calculateAverageGrade();
		double runningTotal = 0.0;
		ObjectiveFunction of;
		of = new ObjectiveFunction();
		Iterator it = t.entrySet().iterator();
		// O(n) students array complexity
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Team currentTeam = (Team)pair.getValue();
			runningTotal += of.calculateFinalObjectiveFunction(currentTeam);
			}
		return runningTotal;
	}
	public void computeSDGettingFirstNSecondPref() {
		sd.computeSDGettingFirstNSecondPref(teams);
	}
	public void computeSdSkillCompetency() {
		sd.computeSdSkillCompetency(teams);
	}  
	public void computeSDSkillShortfall() {
		sd.computeSDSkillShortfall(teams);
	}  
		
}
