package Objects.Project;

import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Objects.FileHandling;

public class OwnerFileHandling extends Owner implements FileHandling {
	private static final long serialVersionUID = 9164771131454810489L;
	public OwnerFileHandling(String ID, String firstName, String surName, String role, String email, String projectId,
			String companyId) throws IDOutOfRangeException, InvalidEmailException {
		super(ID, firstName, surName, role, email, projectId, companyId);
		// TODO Auto-generated constructor stub
	}



	public String toString() {
		return getID() + ";" + getFirstName() + ";" + getSurName() + ";" + getRole() + ";" + getEmail() + ";" + getProjectId()+";"+getCompanyId();
	}
	
	
}
