package ObjectiveFunction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.StudentConflictException;
import Helpers.Calculations;
import Helpers.QueueSingleton;
import Helpers.QuickSortTeam;
import Objects.Project.Project;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;

public class QuadraticSwapCompare implements Runnable {
	private int teamPos;
	private int memberPos;
	private ArrayList<Team> teams;
	private QueueSingleton q = QueueSingleton.getSingleton();
	private AtomicBoolean atomic;
	private Calculations calcs = new Calculations();
	
	//initiate and inform the class of the team that you are comparing from, and the student you are comparing from.
	public QuadraticSwapCompare(int teamPos, int memberPos,ArrayList<Team> sortedTeams,AtomicBoolean atomic) {
		this.teamPos = teamPos;
		this.memberPos = memberPos;
		this.teams = sortedTeams;
		this.atomic = atomic;
	}
	
	@Override
	public void run() {
		compareTeams();
	}

	//assume team already sorted teams
	//assume students already sorted (otherwise done in each thread)
			//end of the list is best objective function
	
	private void compareTeams() {
		//current post and last team, loop from last team backwards to just before the middle point of list
		int halfArrayPos = (int) Math.floor(teams.size()/2);
		Team worstTeam = teams.get(teamPos);
		ArrayList<StudentInfoWithPrefs> worstTeamStuds = worstTeam.getMembers();
		StudentInfoWithPrefs worstStud = worstTeamStuds.get(memberPos);
		
		//interrupt the loop if the user does an action that voids what the current process e.g. swap, remove, add
		while(atomic.get()) {
			//get each team
			for (int i=teams.size()-1;i>halfArrayPos;i--) {
				Team comparisonTeam = teams.get(i);
				ArrayList<StudentInfoWithPrefs> comparisonStuds = comparisonTeam.getMembers();
				for(int j=0; j<worstTeamStuds.size();j++) {
					for(int k=comparisonStuds.size()-1;k>=0;k--) {
						try {
						//see if swapping the worst student from the worst team and the best student from the best team improves the objective function in aggregate
						double worstTeamOriginal = worstTeam.getOf().getObjectiveValue();
						double comparisonTeamOriginal = comparisonTeam.getOf().getObjectiveValue();
						double original = worstTeamOriginal + comparisonTeamOriginal;
						
						//create two new temporary teams and return their objective function
						ArrayList<StudentInfoWithPrefs> tempStuds = returnAllExceptSpecifiedStudent(worstTeamStuds,memberPos);
						//add best student
						tempStuds.add(comparisonStuds.get(k));
						Project proj = worstTeam.getProject();
						
						// the new objective function should be calculated during construction
						Team updatedWorstTeam = new Team(proj,tempStuds.get(0),tempStuds.get(1),tempStuds.get(2),tempStuds.get(3));
						
						tempStuds.clear();
						//get the best team
						tempStuds = returnAllExceptSpecifiedStudent(comparisonStuds,k);
						
						//add the worst student
						tempStuds.add(worstStud);
						proj = comparisonTeam.getProject();
						
						// the new objective function should be calculated during construction
						Team updatedComparisonTeam = new Team(proj,tempStuds.get(0),tempStuds.get(1),tempStuds.get(2),tempStuds.get(3));
						
						double worstTeamUpdated = updatedWorstTeam.getOf().getObjectiveValue();
						double comparisonTeamUpdated = updatedComparisonTeam.getOf().getObjectiveValue();
						double updated = worstTeamUpdated + comparisonTeamUpdated;
						
						//improved [reduced] both team's objective function, then add the suggestion to the queue
						if(updated<original) {
							String st = "Swap " + worstStud.getID() + " from " + worstTeam.getID() + " with " + comparisonStuds.get(k) +" from " + teams.get(i) + " to improve the objective function from " + calcs.round2DecPl(original) + " to " + calcs.round2DecPl(updated);
							q.addToQueue(st);
						}
						
						} catch (StudentConflictException | PersonalityImbalanceException | RepeatedMemberException
								| NoLeaderException | IncorrectRankException | IncorrectRangeException e) {
							// in the case of an error skip this comparison
							continue;
						}
						
						
					}
				}
			}
			
		}
	}
	//assign to queue
	
	
	
	
	
	
	private ArrayList<StudentInfoWithPrefs> returnAllExceptSpecifiedStudent(ArrayList<StudentInfoWithPrefs> studs, int i) {
		ArrayList<StudentInfoWithPrefs> returnStuds = new ArrayList<StudentInfoWithPrefs>();
		for(int j=0;i<studs.size();i++) {
			if(j!=i) {
				returnStuds.add(studs.get(j));
			}
		}
		return returnStuds;
	}
	
}
