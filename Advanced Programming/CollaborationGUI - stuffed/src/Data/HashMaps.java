package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
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
import ObjectiveFunction.DefaultObjectiveWeighting;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.OwnerSQL;
import Objects.Project.Project;
import Objects.Project.ProjectFileHandling;
import Objects.Project.ProjectSQL;
import Objects.Project.Team;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;


public class HashMaps implements Serializable {	
	private static final long serialVersionUID = -9002152891627906657L;
	//stores every
	private StartupPhase sp = StartupPhase.getSingleton();
	private Computation comp = new Computation();
	private HashMap<String,Team> teams = new HashMap<String,Team>();
	private HashMap<String,Project> shortlist = new HashMap<String, Project>();
	private HashMap<String,StudentInfoWithPrefs> studentInfoWPrefs = new HashMap<String, StudentInfoWithPrefs>();
	//change to Student
	private HashMap<String,StudentInfoWithPrefs> unassignedStudents = new HashMap<String, StudentInfoWithPrefs>();

	
	
	private static HashMaps _instance;
	
	@FunctionalInterface
	interface MyInterface{ 
		double display(ArrayList<Double> numbers);
	}
	
	private HashMaps() {}
	public static synchronized HashMaps getSingleton() {
		if(_instance == null) {
			_instance = new HashMaps();
		}
		return _instance;
	}
	
	//push the teams metrics into another class
	

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

	public void setShortlist(HashMap<String,Project> proj) {
		shortlist.putAll(proj);
	}
	public HashMap<String, Team> getTeams() {
		return teams;
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
	//adds all the students into a hashmap that can be used to assign to projects
	public HashMap<String,StudentInfoWithPrefs> getUnassignedStudents() {
		   return unassignedStudents;
	}
	//adds all the students into a hashmap that can be used to assign to projects
    public ArrayList<String> getUnassignedStudentsInArray() {
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
	public ArrayList<StudentInfoWithPrefs> getStudentInfoWithPrefsArray() {
			   ArrayList<StudentInfoWithPrefs> values = new ArrayList<>(studentInfoWPrefs.values());
			   return values;  
	}

	public ArrayList<Project> getArrayShortList() {
		   ArrayList<Project> values = new ArrayList<>(shortlist.values());
		   return values;  
	}
	   //adds all the Teams into a ArrayList that can be written to a database
	   public ArrayList<Team> getArrayTeams() {
			   ArrayList<Team> values = new ArrayList<>(teams.values());
			   return values;  
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
	

	
	
//	public String compareBadTeams() {
//		DefaultObjectiveWeighting obWeight = DefaultObjectiveWeighting.getInstance();
		
//		giveTeamsObjectiveFunction();
		
		//order teams by objective function
//		for(int i=0;i<teams.size();i++) {
			
//		}
		//get the worst student
		
		//new thread
		
		//get the next worst team
		
		//continue until half way through teams
//	}
	
	


	public HashMap<String,StudentInfoWithPrefs> getStudentInfoWPrefs() {
		return studentInfoWPrefs;
	}
	public void setTeams(HashMap<String, Team> tempTeams) {
		this.teams = tempTeams;
	}
	public void computeSDGettingFirstNSecondPref() {
		comp.computeSDGettingFirstNSecondPref(teams);
	}
	public void computeSdSkillCompetency() {
		comp.computeSdSkillCompetency(teams);
	}
	public void computeSDSkillShortfall() {
		comp.computeSDSkillShortfall(teams);
	}
	public double getSdGettingFirstNSecondPref() {
		return comp.getSdGettingFirstNSecondPref();
	}
	public double getSdShortfall() {
		return comp.getSdShortfall();
	}
	public double getSdSkillCompetency() {
		return comp.getSdSkillCompetency();
	}
	public void recalculateAllTeamMetrics() {
		comp.recalculateAllTeamMetrics(teams);
	}
	
	
	//pass through to relevant class
	public String autoCalculateObjectiveFunction(DefaultObjectiveWeighting weighting) {
		try {
			return comp.autoCalculateObjectiveFunction(weighting,teams,studentInfoWPrefs,shortlist);
		} catch (IncorrectPersonalityTypeException | IncorrectGradeException | IncorrectRangeException
				| IDOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<Company> getArrayCompanies() {
		return sp.getArrayCompanies();
	}  
	public ArrayList<Owner> getArrayOwners() {
		return sp.getArrayOwners();
	} 	
	public ArrayList<Project> getArrayProjects() {
		return sp.getArrayProjects();
	}
	public void addCompany(Company company) throws IdAlreadyUsedException {
		sp.addCompany(company);
		
	}
	public void addOwner(OwnerSQL ownerSQL) throws IdAlreadyUsedException {
		sp.addOwner(ownerSQL);
	}
	public void addProject(ProjectSQL projectSQL) throws IdAlreadyUsedException, IDOutOfRangeException {
		sp.addProject(projectSQL);
		
	}
	public void setCompanies(HashMap<String, Company> loadCompany) {
		sp.setCompanies(loadCompany);
	}
	public void setOwners(HashMap<String, Owner> loadOwner) {
		sp.setOwners(loadOwner);
	}
	public void setStudentInfo(HashMap<String, StudentInfo> loadStudentInfo) {
		sp.setStudentInfo(loadStudentInfo);
	}
	public void setProjectInfo(HashMap<String, Project> loadProjects) {
		sp.setProjectInfo(loadProjects);
	}
	public void setStudents(HashMap<String, Student> loadStudents) {
		sp.setStudents(loadStudents);
	}
	public void setPreferences(HashMap<String, Preferences> loadPreferences) {
		sp.setPreferences(loadPreferences);
	}
	public String stringStudentPreferences() {
		return sp.stringStudentPreferences();
	}
	public String stringStudents() {
		return sp.stringStudents();
	}
	public String stringProjects() {
		return sp.stringProjects();
	}
	public String stringOwners() {
		return sp.stringOwners();
	}
	public String stringCompanies() {
		return sp.stringCompanies();
	}
	public String stringStudentInfo() {
		return sp.stringStudentInfo();
	}
	public void addOwner(Owner own) throws IdAlreadyUsedException {
		sp.addOwner(own);
	}
	public void addStudInfo(StudentInfo studInfo) throws IdAlreadyUsedException {
		sp.addStudInfo(studInfo);
	}
	public void addStudents(Student stud) throws IdAlreadyUsedException {
		sp.addStudents(stud);
	}
	public void addProject(Project pr) throws IdAlreadyUsedException, IDOutOfRangeException {
		sp.addProject(pr);
	}
	public void addPreferences(Preferences pref) throws IdAlreadyUsedException {
		sp.addPreferences(pref);
	}
	public void stringStudentIds() {
		sp.stringStudentIds();
		
	}
	public Student getStudent(String string) throws InvalidSKeyException {
		return sp.getStudent(string);
	}
	public int getStudentCount() {
		return sp.getStudentCount();
	}
	public Integer getProjectsCount() {
		return sp.getProjectsCount();
	}
	public ArrayList<String> returnKeysToRemove(Integer projectsToRemove) {
		// TODO Auto-generated method stub
		return sp.returnKeysToRemove(projectsToRemove);
	}
	public String displayStudentSuitability(Project project) {
		return comp.displayStudentSuitability(project,studentInfoWPrefs);
	}
	public void setAverageGrade(double runningGrade) {
		comp.setAverageGrade(runningGrade);
	}

	public void mergeStudentInfoAndPreferences() throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		for(Map.Entry entry : sp.getStudentInfo().entrySet()) {
			if (sp.getPrefs().containsKey(entry.getKey())) {
				StudentInfo studInfo = (StudentInfo) entry.getValue();
				Preferences preferences = sp.getPrefs().get(entry.getKey());
				StudentInfoWithPrefs withPrefs = new StudentInfoWithPrefs(studInfo,preferences);
				getStudentInfoWPrefs().put((String) entry.getKey(),withPrefs);
			}
		}
	}
	public String stringShortlist() {
		String outOutput = "";
		for(Map.Entry entry : getShortlist().entrySet()) {
			  ProjectFileHandling proj;
			try {
				proj = new ProjectFileHandling(((Project)entry.getValue()));
			
			  outOutput += proj.toString() + "\n";
			} catch (IncorrectRankException | IncorrectRangeException | IDOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return outOutput;
	}

	public String stringTeamId() {
		String outOutput = "";
		for(Map.Entry entry : getTeams().entrySet()) {
			 outOutput += ((Team)entry.getValue()).getID() + "\n";
		}
		return outOutput;
	}
	public void makeShortlist(ArrayList<String> removeKeys) {
		shortlist.putAll(sp.getProjects());
		for (int i=0;i<removeKeys.size();i++) {
			shortlist.remove(removeKeys.get(i));
		}
	}
	public void addToShortlist(String shortlistProject) {
		shortlist.put(shortlistProject, sp.getProjects().get(shortlistProject));
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
	public void replaceTeam(String oldTeamId, Team newTeam) {
		getTeams().entrySet().forEach(entry->{
		    if(( entry).getKey().equals(oldTeamId)) {
		    	( entry).setValue(newTeam);
		    }
		 });
	}
	public void dissolveTeam(Team team) {
		if(getTeams().containsValue(team)) {
			//get all the students
			ArrayList<StudentInfoWithPrefs> studs = getTeams().get(team.getID()).getMembers();
			//add them back to the unassigned list
			for(int i = 0; i<studs.size();i++) {
				getUnassignedStudents().put(studs.get(i).getID(),studs.get(i));
			}
			//dissolve team
			teams.remove(team.getID(), team);
		}
	}
	public void addTeam(Team team) throws InvalidMemberException {
		invalidMemberCheck(team.getMembers());
		ArrayList<StudentInfoWithPrefs> students = team.getMembers();
		
		//remove students from the unassigned pool
		for(int i = 0; i < students.size();i++) {
			getUnassignedStudents().remove(students.get(i).getID());			
		}
		teams.put(team.getID(), team);
	}
	   
	   //adds all the students into a hashmap that can be used to assign to projects
	   public void addAllStudentsToUnassignedStudentsHashMap() {
		   unassignedStudents.putAll(studentInfoWPrefs);
	}

		public void addStudentInfoWithPreferences(StudentInfoWithPrefs studentWPrefs) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException {
			studentInfoWPrefs.put(studentWPrefs.getID(),studentWPrefs);
		}
		//Check an individual hasn't been already added
		public void invalidMemberCheck(StudentInfoWithPrefs stud) throws InvalidMemberException {
			for(Map.Entry entry : teams.entrySet()) {
				if(((Team) entry.getValue()).getMembers().contains(stud)) {
					throw new InvalidMemberException();
				} 
			}
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
		
		public void removeTeamMember(String teamId, String studentId) {
			teams.get(teamId).removeMember(studentId);
			unassignedStudents.put(studentId, studentInfoWPrefs.get(studentId));
		}
		public String stringShortlistIds() {
			String st = "";
			for (Map.Entry entry : shortlist.entrySet()) {
				st += ((Project) entry.getValue()).getID();
			}
			return st;
		}
		
		public Team createInterimTeam(String team1Id, String oldStudent, String newStudent) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, StudentNotSubstitutedException, InvalidStudentException, InvalidProjectException, IDOutOfRangeException {
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
			Team interimTeam = null;
			try {
				interimTeam = new Team(getProjectNoErrors(team1Id),members.get(0),members.get(1),members.get(2),members.get(3),noExceptionsThrown);
			} catch (IDOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return interimTeam;
		}

		public void assignTempTeamsToTeams() {
			setTeams(comp.getTempTeams());
		}
		
}
