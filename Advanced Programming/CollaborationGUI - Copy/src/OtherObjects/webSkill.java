package OtherObjects;

import java.io.Serializable;

public class webSkill extends Skill implements Serializable {

	private static final long serialVersionUID = 209680680437075999L;

	public webSkill(Double value) {
		super('w',"Web & Mobile applications");
		super.setValue(value);
	}

}
