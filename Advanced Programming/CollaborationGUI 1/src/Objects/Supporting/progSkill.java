package Objects.Supporting;

import java.io.Serializable;

public class progSkill extends Skill implements Serializable{

	private static final long serialVersionUID = 8153904681245543095L;

	public progSkill(Double value) {
		super('p',"Programming & Software Engineering");
		super.setValue(value);
	}

}
