package Objects.Project;

import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNamingException;
import Objects.ParentObject;

public class Owner extends ParentObject {


	private static final long serialVersionUID = -6100323290985991166L;
	private String firstName;
	private String surName;
	private String role;
	private String email;
	private String projectId;
	private String companyId;
	private static String prefix = "Own";
	
	
	public Owner(String ID,String firstName,String surName,String role,String email,String projectId, String companyId) {
		super(ID);
		this.setFirstName(firstName);
		this.setSurName(surName);
		this.setRole(role);
		this.setEmail(email);
		this.setProjectId(projectId);
		this.setCompanyId(companyId);
	}
	
	public Owner(int ID,String fullName,String role,String email,String projectId, String companyId) throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		super(ID);
		validName(fullName);
		this.setFirstName(firstName(fullName));
		this.setSurName(surName(fullName));
		this.setRole(role);
		validEmail(email);
		this.setEmail(email);
		this.setProjectId(projectId);
		this.setCompanyId(companyId);	
	}
	

	
	private String firstName(String name) {
		String[] parts = name.split(" ");
		return parts[0];
	}	
	private String surName(String name) {
		String[] parts = name.split(" ");
		return parts[1];
	}		
	private void validEmail(String email) throws InvalidEmailException{
		if (email.contains("@")==false) {
			throw new InvalidEmailException();
		}	
		if (email.contains(".")==false) {
			throw new InvalidEmailException();
		}
	}
	private void validName(String name) throws InvalidNamingException{
		String space = " ";
		if (name.contains(space)) {
		}
		else {
			throw new InvalidNamingException();
		}
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
	
	
	
	
	public String toString() {
		return getID() + ";" + getFirstName() + ";" + getSurName() + ";" + getRole() + ";" + getEmail() + ";" + getProjectId()+";"+getCompanyId();
	}



	public String getCompanyId() {
		return companyId;
	}



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}	
	

@Override
public String addPrefix() {
	return prefix;
}
@Override
public String removePrefix() {
	return getID().replace(prefix, "");
}
	
}
