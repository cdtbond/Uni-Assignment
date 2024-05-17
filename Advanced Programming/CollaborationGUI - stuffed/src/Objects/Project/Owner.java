package Objects.Project;

import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Objects.ParentObject;

public class Owner extends ParentObject {


	private static final long serialVersionUID = -6100323290985991166L;
	private String firstName;
	private String surName;
	private String role;
	private String email;
	private String projectId;
	private String companyId;
	
	
	
	public Owner(String ID,String firstName,String surName,String role,String email,String projectId, String companyId) throws IDOutOfRangeException, InvalidEmailException {
		super(ID);
		this.setFirstName(firstName);
		this.setSurName(surName);
		this.setRole(role);
		validEmail(email);
		this.setEmail(email);
		this.setProjectId(projectId);
		this.setCompanyId(companyId);
		
	}




	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getSurName() {
		return surName;
	}



	public void setSurName(String surName) {
		this.surName = surName;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getProjectId() {
		return projectId;
	}



	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	
	
	private void validEmail(String email) throws InvalidEmailException{
		if (email.contains("@")==false) {
			throw new InvalidEmailException();
		}	
		if (email.contains(".")==false) {
			throw new InvalidEmailException();
		}
	}



	public String getCompanyId() {
		return companyId;
	}



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}	
	

	
}
