package Objects;
import java.io.Serializable;

public class ParentObject implements Serializable{
	private static final long serialVersionUID = -8393348144197434160L;
	private String ID;
	public ParentObject(String ID) {
		this.setID(ID);
	}

	public String getID() {
		return ID;
	}
	

	public void setID(String iD) {
		ID = iD;
	}
	
	
	


	
}
