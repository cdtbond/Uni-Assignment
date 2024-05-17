package Objects.Supporting;

import java.io.Serializable;

public class Skill implements Serializable{

	private static final long serialVersionUID = 2724486512163328706L;
	private String longName;
	private char shortName;
	private Double value;
	
	public Skill(char shortName, String longName) {
		this.setShortName(shortName);
		this.setName(longName);
	}

	public String getName() {
		return longName;
	}

	public void setName(String name) {
		this.longName = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public char getShortName() {
		return shortName;
	}

	public void setShortName(char shortName) {
		this.shortName = shortName;
	}

}
