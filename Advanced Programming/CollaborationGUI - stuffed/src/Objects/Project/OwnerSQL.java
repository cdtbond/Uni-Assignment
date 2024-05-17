package Objects.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Objects.SQL;

public class OwnerSQL extends Owner implements SQL {

	private static final long serialVersionUID = -2610194503400108029L;

	public OwnerSQL(ResultSet rs) throws SQLException, IDOutOfRangeException, InvalidEmailException {
		super("Own" + rs.getInt("ID"), rs.getString("firstName"),rs.getString("lastName"),rs.getString("role"), rs.getString("email"),"Pr" + rs.getInt("projectId"),"C" + rs.getInt("companyId"));
	}
	
	public String getSQL() {
		return "(" + getID().replace("Own", "") + ",'" + getFirstName().replace("'", "''") + "','" + getSurName().replace("'", "''") + "','" + getRole() + "','" + getEmail().replace("'", "''") + "'," + getProjectId().replace("Pr", "")+","+getCompanyId().replace("C", "") + ")";
	}
	
	
	
}
