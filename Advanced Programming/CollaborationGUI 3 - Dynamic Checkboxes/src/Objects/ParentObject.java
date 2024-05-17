package Objects;
import java.io.Serializable;

import Exceptions.IDOutOfRangeException;

public abstract class ParentObject implements Serializable{
	private static final long serialVersionUID = -8393348144197434160L;
	private String ID;
	private static int rangeRoof = 20;
	private static int rangeFloor = 0;
	public ParentObject(String ID) {
		this.setID(ID);
	}
	public ParentObject(Integer ID) throws IDOutOfRangeException {
		checkIdWithinRange(ID);
		this.setID(addPrefix() + ID);
	}
	

	public abstract String addPrefix();
	public abstract String removePrefix();
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	private void checkIdWithinRange(int value) throws IDOutOfRangeException {
		if (value < rangeFloor || value > rangeRoof) {
			throw new IDOutOfRangeException();
		}
	}
}
