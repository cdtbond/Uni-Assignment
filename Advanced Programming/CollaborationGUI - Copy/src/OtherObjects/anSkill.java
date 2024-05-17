package OtherObjects;

import java.io.Serializable;

public class anSkill extends Skill implements Serializable{

	private static final long serialVersionUID = -3853521751929542109L;

	public anSkill(Double value) {
		super('a',"Analytics and Big Data");
		super.setValue(value);
	}

}
