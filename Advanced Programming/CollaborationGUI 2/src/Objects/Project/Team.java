package Objects.Project;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;

import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Exceptions.StudentNotSubstitutedException;
import Helpers.QuickSortStudent;
import ObjectiveFunction.ObjectiveFunction;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Supporting.PersonalityType;
import Objects.Supporting.PersonalityTypes;
import Objects.Supporting.Skill;
import Objects.Supporting.Skills;

public class Team extends Project implements Serializable{

	private static final long serialVersionUID = 5594880440389638361L;
	private Skills averageStudentSkillValue;
	private Skills detailedSkillShortFall;
	private ArrayList<PersonalityType> personalities = new ArrayList<PersonalityType>();
	private ArrayList<StudentInfoWithPrefs> members = new ArrayList<StudentInfoWithPrefs>();
	private double firstAndSecondPrefsPercent;
	private double averageSkillCompetency;
	private double skillShortFall;
	private ObjectiveFunction of;
	private QuickSortStudent qs;
	
	public Team(Project proj,StudentInfoWithPrefs s1, StudentInfoWithPrefs s2,StudentInfoWithPrefs s3,StudentInfoWithPrefs s4) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException {
		super(proj);
		members.add(s1);
		members.add(s2);
		members.add(s3);
		members.add(s4);
		personalityClashCheck(members);
		personalityTypeCheck(members);
		checkNotAdded(members);
		leaderCheck(members);
		recalculateMetrics();
	}
	
	public Team(Project proj, StudentInfoWithPrefs s1, StudentInfoWithPrefs s2,StudentInfoWithPrefs s3, StudentInfoWithPrefs s4, boolean b) {
		super(proj);
		members.add(s1);
		members.add(s2);
		members.add(s3);
		members.add(s4);
		recalculateMetrics();
	}
	
	public Team(Project proj, ArrayList<StudentInfoWithPrefs> studs) {
		super(proj);
		members.add(studs.get(0));
		members.add(studs.get(1));
		members.add(studs.get(2));
		members.add(studs.get(3));
		recalculateMetrics();
	}
	
	public Project getProject() throws IncorrectRankException, IncorrectRangeException {		
		Project proj = new Project(getID(),getTitle(),getDescription(),getOwnerId(),getSkills().getProgSkill().intValue(),(int)getSkills().getAnSkill().intValue(),(int)getSkills().getNetSkill().intValue(),(int)getSkills().getWebSkill().intValue());
		return proj;
	}
	

	public ArrayList<StudentInfoWithPrefs> returnAllMembersApartFromSpecifiedMember(String student) {
		ArrayList<StudentInfoWithPrefs> students = new ArrayList<StudentInfoWithPrefs>();
		for(int i=0;i<members.size();i++) {
			if(members.get(i).getID().equals(student)) {		
			} else {
				students.add(members.get(i));
			}
		}
		return students;
		
	}
	
	public void substituteStudent(String studentToRemove,StudentInfoWithPrefs studentToAdd) {
		for(int i = 0;i<members.size();i++) {
			if(studentToRemove.equals(members.get(i).getID())) {
				members.add(i,studentToAdd);
			}
		}
	}
	
	public boolean hasStudent(String stud) {
		for(int i=0;i<members.size();i++) {
			if (members.get(i).getID().equals(stud)) {
				return true;
			}
		}
		return false;
	}

	public void addStudent(StudentInfoWithPrefs s1) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException {
		members.add(s1);
		personalityClashCheck(members);
		personalityTypeCheck(members);
		checkNotAdded(members);
		computeAverageSkillCompetency();
		leaderCheck(members);
		computeFirstAndSecondPrefs(members);
		computeSkillShortfall();
	}
	
	public ArrayList<StudentInfoWithPrefs> getMembers() {
		return members;
	}
	
	public void removeMember(String studentToRemove) {
		for(int i = 0; i<members.size();i++) {
			if(members.get(i).getID().equals(studentToRemove)) {
				members.remove(i);
				return;
			}
		}
	}
	
	public void substituteMember(StudentInfoWithPrefs oldStudent, StudentInfoWithPrefs newStudent) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, StudentNotSubstitutedException {
		boolean studentSubstituted = false;
		for(int i = 0; i<members.size();i++) {
			if(members.get(i).getID().equals(oldStudent.getID())) {
				members.set(i, newStudent);
				studentSubstituted=true;
			}
		}
		if(studentSubstituted) {
			personalityClashCheck(members);
			personalityTypeCheck(members);
			checkNotAdded(members);
			computeAverageSkillCompetency();
			leaderCheck(members);
			computeFirstAndSecondPrefs(members);
		}
		else {
			throw new StudentNotSubstitutedException();
		}
	}
    public double getAverageSkillCompetency() {
    	return averageSkillCompetency;
    }
	public void computeSkillShortfall() {
		double anSkill;
		double netSkill;
		double progSkill;
		double webSkill;
		
		if(getAnSkill()>averageStudentSkillValue.getAnSkill()) {
			anSkill = getAnSkill() - averageStudentSkillValue.getAnSkill();
		}
		else {
			anSkill = 0.0;
		}
		if(getNetSkill()>averageStudentSkillValue.getNetSkill()) {
			netSkill = getNetSkill() - averageStudentSkillValue.getNetSkill();
		}
		else {
			netSkill = 0.0;
		}
		if (getProgSkill()>averageStudentSkillValue.getProgSkill()) {
			progSkill = getProgSkill() - averageStudentSkillValue.getProgSkill();	
		}
		else {
			progSkill = 0.0;
		}
		if (getWebSkill()>averageStudentSkillValue.getWebSkill()) {
			webSkill = getWebSkill() - averageStudentSkillValue.getWebSkill();
		}
		else {
			webSkill = 0.0;
		}
		
			
		detailedSkillShortFall = new Skills(progSkill,anSkill,netSkill,webSkill);
		skillShortFall = anSkill + netSkill + progSkill + webSkill;
	}
	public double getSkillShortfall() {
		return skillShortFall;
	}
	//check for clash between students
	private void personalityClashCheck(ArrayList<StudentInfoWithPrefs> students) throws StudentConflictException {
		
		for(int i=0 ; i<students.size();i++) {
			for(int j=0;j<students.size();j++) {
				//compare student j with difficult student list in student i
				if (students.get(j).compareDifficultStudents(students.get(i).getID())) {
					throw new StudentConflictException();
				}
			}
			
		}
		
	}
	private void personalityTypeCheck(ArrayList<StudentInfoWithPrefs> students) throws PersonalityImbalanceException {
			PersonalityTypes personalities = new PersonalityTypes();
			if(personalities.tallyMissingTypes(students)>1) {
				throw new PersonalityImbalanceException();
			}
	}
	//for an individual student added
	private void checkNotAdded(StudentInfo s1) throws RepeatedMemberException {
		for(int i=0;i<members.size();i++) {
			//if repeated throw exception
			if(members.get(i).getID().equals(s1.getID())) {
				throw new RepeatedMemberException();
		}
	}
	}
	//for all students added
	private void checkNotAdded(ArrayList<StudentInfoWithPrefs> team) throws RepeatedMemberException {
		//loop through team
		for(int i=0;i<team.size();i++) {
			//loop through other member of team
			for(int j=0;j<team.size();j++) {
				//skip comparing own student
				if(i==j) {}
				else {
					//if repeated throw exception
					if(team.get(i).getID().equals(team.get(j).getID())) {
						throw new RepeatedMemberException();
					}
				}
			}
		}
	}
	private void computeAverageSkillCompetency() {
		double anSkill = 0.00;
		double netSkill = 0.00;
		double progSkill = 0.00;
		double webSkill = 0.00;
		
		for(int i=0;i<members.size();i++) {
			//compute analysis skill
			anSkill += members.get(i).getAnSkill();
			//compute net skill
			netSkill += members.get(i).getNetSkill();
			//compute prog skill
			progSkill += members.get(i).getProgSkill();
			//compute web skill
			webSkill += members.get(i).getWebSkill();
		}
		anSkill = (anSkill/members.size());
		netSkill = (netSkill/members.size());
		progSkill = (progSkill/members.size());
		webSkill = (webSkill/members.size());
		averageStudentSkillValue = new Skills(progSkill, anSkill, netSkill, webSkill);
		averageSkillCompetency = (anSkill + netSkill + progSkill + webSkill);
	}
	private void computeFirstAndSecondPrefs(ArrayList<StudentInfoWithPrefs> preferences) {
		int firstAndSecondPrefsCount = 0;
		for(int i=0;i<preferences.size();i++) {
			if(preferences.get(i).getPreferenceOne().equals(getID())) {
				firstAndSecondPrefsCount++;
			}
			else if(preferences.get(i).getPreferenceTwo().equals(getID())) {
				firstAndSecondPrefsCount++;
			}
		}

		setFirstAndSecondPrefsPercent(firstAndSecondPrefsCount, preferences.size());
	}
	public double getFirstAndSecondPrefPercent() {
		return firstAndSecondPrefsPercent;
	}
	public boolean memberAssigned(Student stud) {
		return members.contains(stud);
	}
	private void leaderCheck(ArrayList<StudentInfoWithPrefs> students) throws NoLeaderException {
		boolean noLeader = true;
		for(int i=0;i<students.size();i++) {
			if(students.get(i).getPersonality().getPersonalityId()=='A') {
				noLeader = false;
			}
		}
		if(noLeader) {
			throw new NoLeaderException();
		}
	}
	public double getFirstAndSecondPrefsPercent() {
		return firstAndSecondPrefsPercent;
	}
	public void setFirstAndSecondPrefsPercent(int firstAndSecondPrefsCount, int size) {
		this.firstAndSecondPrefsPercent = (double)firstAndSecondPrefsCount / size * 100;
	}
	public ArrayList<PersonalityType> getPersonalities() {
		return personalities;
	}
	public void setPersonalities(ArrayList<PersonalityType> personalities) {
		this.personalities = personalities;
	}
	public String getSuitabilityMetrics() {
		String outString = "";
		outString += "First and Second Prefs Percent: ";
		outString += firstAndSecondPrefsPercent;
		outString += "% \n";
		outString += "Average Skill competency is: ";
		outString += averageSkillCompetency;
		outString += "Skill shortfall is: ";
		outString += skillShortFall;
		
		return outString;
	}
	public String getMemberIds() {
		String outString = "";
		for(int i=0;i<members.size();i++) {
			outString += members.get(i).getID();
			outString += "/n";
		}
		return outString;
	}
	
	public String getMemberId(int memberPosition) {
		return members.get(memberPosition).getID();
	}
	
	public String getSQL(int memberPosition) {
		String sql = "(" + getID().replace("Pr", "") + ",";;
		sql += members.get(memberPosition).getID().replace("S", "") + ")";
		
		return sql;
	}


	
	
	private void recalculateMetrics() {
		computeAverageSkillCompetency();
		computeFirstAndSecondPrefs(members);
		computeSkillShortfall();
		computeTeamObjectiveFunction();
		computeStudentObjectiveFunction();
		//sort based on objective function
		members.sort((s1,s2) -> Double.compare(s1.getOf().getObjectiveValue(), s2.getOf().getObjectiveValue()));	}

	private void computeStudentObjectiveFunction() {
		for(int i=0;i<members.size();i++) {
			ObjectiveFunction of = new ObjectiveFunction();
			of.calculateStudentObjectiveValue(this, members.get(i));
			members.get(i).setOf(of);
		}
	}

	private void computeTeamObjectiveFunction() {
		ObjectiveFunction of = new ObjectiveFunction();
		of.calculateFinalObjectiveFunction(this);
		setOf(of);
	}

	public ObjectiveFunction getOf() {
		return of;
	}

	public void setOf(ObjectiveFunction of) {
		this.of = of;
	}

	public double getObjectiveValue(String projId) {
		return of.getObjectiveValue();
	}



}

	
