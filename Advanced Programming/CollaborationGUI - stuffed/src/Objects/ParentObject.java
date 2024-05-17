package Objects;
import java.io.Serializable;

import Exceptions.IDOutOfRangeException;

public class ParentObject implements Serializable{
	private static final long serialVersionUID = -8393348144197434160L;
	private String ID;

	public ParentObject(String ID) throws IDOutOfRangeException {

		this.setID(ID);
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	


	
}
