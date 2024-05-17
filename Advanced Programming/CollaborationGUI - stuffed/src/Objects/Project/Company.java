package Objects.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNamingException;
import Objects.ParentObject;

public class Company extends ParentObject {

	private static final long serialVersionUID = 7519158114731873077L;
	private String name;
	private String abn;
	private String url;
	private String address;
	
	public Company(String id, String name, String ABN, String URL, String address) throws ABNValuesIncorrectException, ABNLengthIncorrectException, IDOutOfRangeException {
		super(id);		
		this.setName(name);
		abnValuesValid(ABN);
		abnLengthValid(ABN);
		this.setABN(ABN);
		this.setURL(URL);
		this.setAddress(address);
	}

	public String getABN() {
		return abn;
	}
	public void setABN(String abn) {
		this.abn = abn;
	}
	public String getURL() {
		return url;
	}
	public void setURL(String url) {
		this.url = url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


private void abnValuesValid(String value) throws ABNValuesIncorrectException {
		try {
			Double.parseDouble(value);
		}
		catch(NumberFormatException e) {
			throw new ABNValuesIncorrectException();
		}	
		
	}
private void abnLengthValid(String value) throws ABNLengthIncorrectException {
		if (value.length() != 11) {
			throw new ABNLengthIncorrectException();
		}
	}
	
	
}
