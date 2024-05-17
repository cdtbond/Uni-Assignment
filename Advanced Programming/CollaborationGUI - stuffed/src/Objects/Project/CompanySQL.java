package Objects.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.IDOutOfRangeException;
import Objects.SQL;

public class CompanySQL extends Company implements SQL {

	private static final long serialVersionUID = -1040279260428641766L;

	public CompanySQL(ResultSet rs) throws SQLException, ABNValuesIncorrectException, ABNLengthIncorrectException, IDOutOfRangeException {
		super("C" + rs.getInt("ID"), rs.getString("Name"), rs.getString("ABN"), rs.getString("URL"), rs.getString("Address"));

	}
	
	@Override
	public String getSQL() {
		return "('" + getID().replace("C", "") + "','" + getName() + "','" + getABN() + "','" + getURL() + "','" + getAddress() + "')";
	}
	


	
}
