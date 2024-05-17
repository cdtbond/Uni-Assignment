package ObjectiveFunction;

import java.util.ArrayList;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;

public class ObjectiveFunction {
	private DefaultObjectiveWeighting ob = DefaultObjectiveWeighting.getInstance();

	private double objValue;
	
	
	public ObjectiveFunction() {


	}

	public double getObjValue() {
		return objValue;
	}

	
	public double calculateFinalObjectiveFunction(Team team, double overallAverageGrade) {
		double percentPrefs = 1 - team.getFirstAndSecondPrefPercent();
		double skillShortfall = team.getSkillShortfall();
		double averageGrade = team.getAverageSkillCompetency();
		double gradeDiff = Math.abs(overallAverageGrade-averageGrade);
		
		
		percentPrefs = percentPrefs * ob.getFirstAndSecondPrefs();
		skillShortfall = skillShortfall * ob.getSkillShortFall();
		gradeDiff = gradeDiff * ob.getAverageCompetency();
		
		objValue = gradeDiff + skillShortfall + percentPrefs;
		
		
		return objValue;
	}
	
	
	
	
	//use already assigned members to balance the Average Grade
	public double calculateDraftObjectiveFunction(ArrayList<StudentInfoWithPrefs> alreadyAssignedMembers, StudentInfoWithPrefs stud, double overallAverageGrade, double skillShortFall, String projId) {
		
		//get the overall teams average grade (objective function can be balanced or worsened by other students)
		double teamGrade = 0.00;
		for (int i=0; i < alreadyAssignedMembers.size() ; i++) {
			teamGrade += alreadyAssignedMembers.get(i).getAverageGrade();
		}
		teamGrade += stud.getAverageGrade();
		teamGrade = (teamGrade /(1 + alreadyAssignedMembers.size()));
		
		
		double gradeDiff = Math.abs(teamGrade - overallAverageGrade);
		double percentPrefsMissed = 1.0;

		if (stud.getPreferenceOne().equals(projId) || stud.getPreferenceTwo().equals(projId)) {
			percentPrefsMissed = 0.0;
		}
		
		
		percentPrefsMissed = percentPrefsMissed * ob.getFirstAndSecondPrefs();
		skillShortFall = skillShortFall * ob.getSkillShortFall();
		gradeDiff = gradeDiff * ob.getAverageCompetency();
		
		return gradeDiff + skillShortFall + percentPrefsMissed;
	}
	
}
