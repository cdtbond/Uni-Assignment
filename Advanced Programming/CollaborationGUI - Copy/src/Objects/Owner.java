package Objects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Owner extends ParentObject {


	private static final long serialVersionUID = -6100323290985991166L;
	private String firstName;
	private String surName;
	private String role;
	private String email;
	private String projectId;
	private String companyId;
	
	
	
	public Owner(String ID,String firstName,String surName,String role,String email,String projectId, String companyId) {
		super(ID);
		this.setFirstName(firstName);
		this.setSurName(surName);
		this.setRole(role);
		this.setEmail(email);
		this.setProjectId(projectId);
		this.setCompanyId(companyId);
		
	}
	public Owner(ResultSet rs) throws SQLException {
		super("Own" + rs.getInt("ID"));
		this.setFirstName(rs.getString("firstName"));
		this.setSurName(rs.getString("lastName"));
		this.setRole(rs.getString("role"));
		this.setEmail(rs.getString("email"));
		this.setProjectId("Pr" + rs.getInt("projectId"));
		this.setCompanyId("C" + rs.getInt("companyId"));
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
	
	public String getSQL() {
		return "(" + getID().replace("Own", "") + ",'" + getFirstName().replace("'", "''") + "','" + getSurName().replace("'", "''") + "','" + getRole() + "','" + getEmail().replace("'", "''") + "'," + getProjectId().replace("Pr", "")+","+getCompanyId().replace("C", "") + ")";
	}
	
	
}
