package ObjectiveFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Helpers.QuickSortStudents;
import Objects.Project.Project;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Student.StudentInfoWithPrefsMetrics;

public class MatchTeamWithMembers extends Project implements Serializable{
	ObjectiveFunction of;
	private static final long serialVersionUID = 5594880440389638361L;
	private ArrayList<StudentInfoWithPrefs> remainingOptions = new ArrayList<StudentInfoWithPrefs>();
	private ArrayList<StudentInfoWithPrefs> tempAssignments = new ArrayList<StudentInfoWithPrefs>();
	
	public MatchTeamWithMembers(Project proj,DefaultObjectiveWeighting objWeights) throws IDOutOfRangeException{
		super(proj);
		of = new ObjectiveFunction();
		
	}

	public double getObjectiveValueFromLeaderOptions(Integer index) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		StudentInfoWithPrefsMetrics stud = new StudentInfoWithPrefsMetrics(remainingOptions.get(index));
		return stud.getObjectiveValue(getID());
	}

	public ArrayList<StudentInfoWithPrefs> getLeaderOptionsArray() {
		return remainingOptions;
	}
	
	public void tempAssignStudent(StudentInfoWithPrefs student) {
		tempAssignments.add(student);
	}
	
	
	// Step 1 get the leaders 
	public void compareUnassignedLeaders(HashMap<String, StudentInfoWithPrefs> studentInfoWPrefs,double overallAverageGrade) {
		//start from fresh options
		remainingOptions.clear();
		// O(n) students array complexity
		studentInfoWPrefs.entrySet().forEach(entry->{
			StudentInfoWithPrefs student = ( entry).getValue();
			if(student.isLeader()) {
				try {
					calculateAndAssignStudentToArray(student,overallAverageGrade);
				} catch (IncorrectPersonalityTypeException | IncorrectGradeException | IncorrectRangeException | IDOutOfRangeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		 }); 
		//sortArray
		// O(n log n) complexity
		QuickSortStudents qs = new QuickSortStudents();
		remainingOptions = qs.sort(remainingOptions, getID());
	}
	
	
	// Remaining Steps only get those students that don't conflict with the current students & balance the personalities
	public void compareUnassignedConstrained(HashMap<String, StudentInfoWithPrefs> studs,double overallAverageGrade) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		//start from fresh options
		remainingOptions.clear();
		HashSet<Character> blacklistedPersonalities = new HashSet<Character>();
		HashSet<String> blacklistedStudents = new HashSet<String>();
		//make sure a personality clash doesn't happen, add personality types to a blacklist
		blacklistPersonalities(blacklistedPersonalities);
		//make sure a conflict doesn't happen, add students that conflict with existing students to blacklist
		blacklistStudents(blacklistedStudents);
		
		Iterator it = studs.entrySet().iterator();
		// O(n) students array complexity
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			
			StudentInfoWithPrefs student = (StudentInfoWithPrefs) pair.getValue();
			//check this student isn't hated by existing students
			if (blacklistedStudents.contains(student.getID())) {
				//skip this student
				continue;
			}
			if (blacklistedPersonalities.contains(student.getPersonality().getPersonalityId())) {
				//skip this student
				continue;
			}
			
			
			//check this student doesn't hate any existing students
			if (containsAny(student.getDifficultStudents())) {
				//skip this student
				continue;
			}
			calculateAndAssignStudentToArray(student,overallAverageGrade);

		}
		//sortArray
		// O(n log n) complexity
		QuickSortStudents qs = new QuickSortStudents();
		remainingOptions = qs.sort(remainingOptions, getID());
	}
	
	
	//compare the difficult students array with the tempAssignments
	private boolean containsAny(ArrayList<String> difficultStudents) {
		boolean containsEnemy = false;
		for(int i = 0; i<tempAssignments.size();i++) {
			if (difficultStudents.contains(tempAssignments.get(i).getID())) {
				containsEnemy = true;
			}
		}
		return containsEnemy;
	}

	private void blacklistStudents(HashSet<String> blacklistedStudents) {
		for(int i=0;i<tempAssignments.size();i++) {
			blacklistedStudents.addAll(tempAssignments.get(i).getDifficultStudents());
		}
	}

	private void blacklistPersonalities(HashSet<Character> blacklistedPersonalities) {
		//look at the current students
		int leaders = 0;
		int socializers = 0;
		int thinkers = 0;
		int supporters = 0;
		
		for (int i = 0 ; i<tempAssignments.size();i++) {
			char personality = tempAssignments.get(i).getPersonality().getPersonalityId();
			 if(personality=='A') {
				 leaders++;
			 }
			 else if(personality=='B') {
				 socializers++;
			 }
			 else if(personality=='C') {
				 thinkers++;
			 }
			 else {
				 supporters++;
			 }
		}
		
		if(leaders>1) {
			blacklistedPersonalities.add('A');
			if(socializers>0) {
				blacklistedPersonalities.add('B');
			}
			if(thinkers>0) {
				blacklistedPersonalities.add('C');
			}
			if(supporters>0) {
				blacklistedPersonalities.add('D');
			}
		}
		else if(socializers>1) {
			blacklistedPersonalities.add('B');
			if(leaders>0) {
				blacklistedPersonalities.add('A');
			}
			if(thinkers>0) {
				blacklistedPersonalities.add('C');
			}
			if(supporters>0) {
				blacklistedPersonalities.add('D');
			}
		}
		else if(thinkers>1) {
			blacklistedPersonalities.add('C');
			if(leaders>0) {
				blacklistedPersonalities.add('A');
			}
			if(socializers>0) {
				blacklistedPersonalities.add('B');
			}
			if(supporters>0) {
				blacklistedPersonalities.add('D');
			}
		}
		else if(supporters>1) {
			blacklistedPersonalities.add('D');
			if(leaders>0) {
				blacklistedPersonalities.add('A');
			}
			if(socializers>0) {
				blacklistedPersonalities.add('B');
			}
			if(thinkers>0) {
				blacklistedPersonalities.add('C');
			}
		}
		
	}

	//add the stats to the objective function of the student
	public void calculateAndAssignStudentToArray(StudentInfoWithPrefs student, double overallAverageGrade) throws IncorrectPersonalityTypeException, IncorrectGradeException, IncorrectRangeException, IDOutOfRangeException {
		double shortfall = computeSkillShortfall(student);
		//get the objective function value
		double objectiveFunction = of.calculateDraftObjectiveFunction(tempAssignments,student, overallAverageGrade,shortfall, getID());
		//add the objective value for each student
		StudentInfoWithPrefsMetrics studMetric = new StudentInfoWithPrefsMetrics(student);
		studMetric.setObjectiveValue(getID(), objectiveFunction);
		remainingOptions.add(student);
	}
	
	
}



	
