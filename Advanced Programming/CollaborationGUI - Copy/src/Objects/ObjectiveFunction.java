package Objects;

import java.util.ArrayList;

import Exceptions.NotOneHundredPercentException;

public class ObjectiveFunction {
	private double skillShortfallPercent;
	private double averageCompetencyPercent;
	private double firstAndSecondPrefsPercent;
	
	public ObjectiveFunction(double skillShortfall,double averageCompetency, double firstAndSecondPrefs) throws NotOneHundredPercentException{
		if (notAddUpToOneHundredPercent(skillShortfall,averageCompetency,firstAndSecondPrefs)) {
			throw new NotOneHundredPercentException();
		}
		this.skillShortfallPercent = skillShortfall;
		this.averageCompetencyPercent = averageCompetency;
		this.firstAndSecondPrefsPercent = firstAndSecondPrefs;
	}
	
	
	private boolean notAddUpToOneHundredPercent(double first, double second, double third) {
		first = Math.round(first * 10);
		second = Math.round(second * 10);
		third = Math.round(third * 10);
		
		return (((first + second + third) / 10) != 1.0);
	}
	
	public double calculateFinalObjectiveFunction(Team team, double overallAverageGrade) {
		double percentPrefs = team.getFirstAndSecondPrefPercent();
		double skillShortfall = team.getSkillShortfall();
		double averageGrade = team.getAverageSkillCompetency();
		double gradeDiff = Math.abs(overallAverageGrade-averageGrade);
		
		
		percentPrefs = percentPrefs * firstAndSecondPrefsPercent;
		skillShortfall = skillShortfall * skillShortfallPercent;
		gradeDiff = gradeDiff * averageCompetencyPercent;
		
		return gradeDiff + skillShortfall + percentPrefs;
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
		boolean firstOrSecondPref = false;
		double percentPrefs = 0.0;

		if (stud.getPreferenceOne().equals(projId) || stud.getPreferenceTwo().equals(projId)) {
			firstOrSecondPref = true;
		}
				
		if (firstOrSecondPref) {
			percentPrefs = 1.0;
		}
		
		gradeDiff = gradeDiff * averageCompetencyPercent;
		skillShortFall = skillShortFall * skillShortfallPercent;
		percentPrefs = percentPrefs * firstAndSecondPrefsPercent;
		
		return gradeDiff + skillShortFall + percentPrefs;
	}
	
}
