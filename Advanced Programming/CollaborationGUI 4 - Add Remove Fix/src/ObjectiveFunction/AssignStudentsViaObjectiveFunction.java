package ObjectiveFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AssignStudentsViaObjectiveFunction {

	
	public AssignStudentsViaObjectiveFunction() {
	}
	
	
	
	// loop though projects and assign the most appealing student to each project 
	//	- if a previously assigned student is better in the new project, remove from old, add to new
	// continue until all projects have a student assigned
	public HashMap<String,ProjectObjectiveFunction> assignProjectGreedyAlgorithm(ArrayList<MatchTeamWithMembers> matches) {
		//record the Student, Project and Objective Function
		HashMap<String,ProjectObjectiveFunction> assignedStudents = new HashMap<String,ProjectObjectiveFunction>();
		HashSet<String> projectsAssigned = new HashSet<String>();
		int studentIndex = 0;
		// assign so long as a smaller objective function isn't already assigned
		// if a project already has 
		while (projectsAssigned.size() < matches.size()) {
			//loop through each project
			for (int i = 0; i<matches.size();i++) {
				MatchTeamWithMembers match = matches.get(i);
				String projId = match.getID();
				//get the current studentIndex (first time through while loop will be 0)
				String studId = match.getLeaderOptionsArray().get(studentIndex).getID();
				double objectiveValue = match.getObjectiveValueFromLeaderOptions(studentIndex);
				
				//project already has a student assigned
				if(projectsAssigned.contains(projId)) {
					//skip this project
					continue;
				}
				//student already assigned to another project
				if(assignedStudents.containsKey(studId)) {
					//replace student if better fit for the objective function
					replaceStudentIfBetter(assignedStudents,projectsAssigned,studId,projId,objectiveValue);
				} 
				//student not already assigned to another project, assign to project
				else {
					//assign student
					assignStudent(assignedStudents,projectsAssigned,studId,projId,objectiveValue);
				}
			}
			studentIndex++;
		}
		return assignedStudents;
	}
	
	
	private boolean betterOFOutcome(HashMap<String,ProjectObjectiveFunction> assignedStudents, String studentId, double newObjectiveValue) {
		return newObjectiveValue < assignedStudents.get(studentId).getObjectiveFunction();
	}
	
	private void replaceStudentIfBetter(HashMap<String,ProjectObjectiveFunction> assignedLeaders,HashSet<String> projectsAssigned,String studId,String projId,double newObjectiveValue) {
		//check the new objective function provides a better (lower) value than the assigned objective function
		if (betterOFOutcome(assignedLeaders, studId, newObjectiveValue)) {
			ProjectObjectiveFunction pOF = new ProjectObjectiveFunction(projId,newObjectiveValue);
			
			projectsAssigned.remove(assignedLeaders.get(studId).getProjectId());
			assignedLeaders.put(studId, pOF);
			projectsAssigned.add(projId);

		} 
		//otherwise don't do anything, wait until the next slot to assign
	}

	private void assignStudent(HashMap<String,ProjectObjectiveFunction> assignedStudents,HashSet<String> projectsAssigned,String studId,String projId,double newObjectiveValue) {
		ProjectObjectiveFunction pOF = new ProjectObjectiveFunction(projId,newObjectiveValue);					
		assignedStudents.put(studId, pOF);
		projectsAssigned.add(projId);
	}
	
	
	
}
