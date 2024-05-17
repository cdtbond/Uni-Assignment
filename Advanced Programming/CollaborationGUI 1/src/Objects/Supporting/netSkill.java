package Objects.Supporting;

import java.io.Serializable;

public class netSkill extends Skill implements Serializable{

	private static final long serialVersionUID = -4420796213568912803L;

	public netSkill(Double value) {
		super('n',"Networking and Security");
		super.setValue(value);
	}

}
