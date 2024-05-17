package Objects.SQLObjects;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.IDOutOfRangeException;
import Objects.SQL;
import Objects.Project.Company;

public class CompanySQL extends Company implements SQL {
	private static final long serialVersionUID = 8151694741066468244L;

	public CompanySQL(ResultSet rs) throws SQLException, ABNValuesIncorrectException, ABNLengthIncorrectException, IDOutOfRangeException {
		super(rs.getInt("ID"),rs.getString("Name"),rs.getString("ABN"),rs.getString("URL"),rs.getString("Address"));
	}
	public CompanySQL(Company comp) throws ABNValuesIncorrectException, ABNLengthIncorrectException {
		super(comp.getID(),comp.getName(),comp.getABN(),comp.getURL(),comp.getAddress());
	}
	public String getSQL() {
		return "('" + removePrefix() + "','" + getName() + "','" + getABN() + "','" + getURL() + "','" + getAddress() + "')";
	}

}
