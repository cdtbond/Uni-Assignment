package Objects.Supporting;

import java.io.Serializable;

import Exceptions.IncorrectPersonalityTypeException;

public class PersonalityType implements Serializable{

	private static final long serialVersionUID = 6684711601403022994L;
	private char personalityId;
	private String personalityType;
	
	
	
	public PersonalityType(char personalityId) throws IncorrectPersonalityTypeException {
		
		if (personalityId == 'A') {
			this.setPersonalityId('A');
			this.setPersonalityType("Likes to be a Leader (Director)");
		}
		
		else if (personalityId == 'B') {
			this.setPersonalityId('B');
			this.setPersonalityType("Outgoing and maintains good relationships (Socializer)");
		}
		
		else if (personalityId == 'C') {
			this.setPersonalityId('C');
			this.setPersonalityType("Detail oriented (Thinker)");
		}
		
		else if (personalityId == 'D') {
			this.setPersonalityId('D');
			this.setPersonalityType("Less assertive (Supporter)");
		}
		
		else {
			throw new IncorrectPersonalityTypeException();
		}
	}
	

	



	public char getPersonalityId() {
		return personalityId;
	}



	public void setPersonalityId(char personalityId) {
		this.personalityId = personalityId;
	}



	public String getPersonalityType() {
		return personalityType;
	}



	public void setPersonalityType(String personalityType) {
		this.personalityType = personalityType;
	}

}
