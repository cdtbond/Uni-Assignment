package ObjectiveFunction;

import java.util.ArrayList;

import Data.HashMaps;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;
import SharedValues.AverageGrade;

public class ObjectiveFunction {
	private DefaultObjectiveWeighting ob = DefaultObjectiveWeighting.getInstance();
	private HashMaps hm = HashMaps.getSingleton();
	private double objValue;
	private AverageGrade average = AverageGrade.getSingleton();
	
	public ObjectiveFunction() {


	}


	
	public double calculateFinalObjectiveFunction(Team team) {
		double percentPrefs = 1 - team.getFirstAndSecondPrefPercent();
		double skillShortfall = team.getSkillShortfall();
		double averageGrade = team.getAverageSkillCompetency();
		double gradeDiff = Math.abs(average.getAverageGrade()-averageGrade);
		
		
		percentPrefs = percentPrefs * ob.getFirstAndSecondPrefs();
		skillShortfall = skillShortfall * ob.getSkillShortFall();
		gradeDiff = gradeDiff * ob.getAverageCompetency();
		
		objValue = gradeDiff + skillShortfall + percentPrefs;
		
		
		return objValue;
	}
	
	
	public double getObjectiveValue() {
		return objValue;
	}
	
	public void setObjectiveValue(double ov) {
		this.objValue = ov;
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
