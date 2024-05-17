package Objects.Supporting;
import java.io.Serializable;
import java.util.ArrayList;

import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;

//Lists all possible personality types hardcoded
public class PersonalityTypes implements Serializable{
	ArrayList<Character> personalityIDs = new ArrayList<Character>();
	private static final long serialVersionUID = -5917546250023747028L;
	
	public PersonalityTypes() {
		personalityIDs.add('A');
		personalityIDs.add('B');
		personalityIDs.add('C');
		personalityIDs.add('D');
	}
	
	//check tally of missing personality types
	public int tallyMissingTypes(ArrayList<StudentInfoWithPrefs> team) {
		int tally = 0;
		//put all personalities of the team in a new flat arraylist
		ArrayList<Character> teamPersonalities = new ArrayList<Character>();
		for(int i=0;i<team.size();i++) {
			teamPersonalities.add(team.get(i).getPersonality().getPersonalityId());
		}
		
		for(int i=0;i<personalityIDs.size();i++) {
			if(teamPersonalities.contains(personalityIDs.get(i))) {
			}
			else {
				tally++;
			}
		}
		
		return tally;
		
	}
	
	
	
}
