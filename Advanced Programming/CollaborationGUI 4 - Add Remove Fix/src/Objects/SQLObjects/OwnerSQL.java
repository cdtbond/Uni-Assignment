package Objects.SQLObjects;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNamingException;
import Objects.SQL;
import Objects.Project.Owner;

public class OwnerSQL extends Owner implements SQL {
	private static final long serialVersionUID = -2610194503400108029L;
	public OwnerSQL(ResultSet rs) throws SQLException, IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		super(rs.getInt("ID"),rs.getString("firstName") + " "+rs.getString("lastName"),rs.getString("role"),rs.getString("email"),"Pr" + rs.getInt("projectId"),"C" + rs.getInt("companyId"));
	}
	public OwnerSQL(Owner own) {
		super(own.getID(),own.getFirstName(),own.getSurName(),own.getRole(),own.getEmail(),own.getProjectId(),own.getCompanyId());
	}
	public String getSQL() {
		return "(" + removePrefix() + ",'" + getFirstName().replace("'", "''") + "','" + getSurName().replace("'", "''") + "','" + getRole() + "','" + getEmail().replace("'", "''") + "'," + getProjectId().replace("Pr", "")+","+getCompanyId().replace("C", "") + ")";
	}
}
