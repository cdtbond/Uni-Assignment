package Objects.Project;

import java.util.ArrayList;

import Exceptions.IDOutOfRangeException;
import Objects.Student.StudentInfoWithPrefs;

public class TeamSQL extends Team  {

	private static final long serialVersionUID = -997688497505791608L;

	public TeamSQL(Project proj, ArrayList<StudentInfoWithPrefs> studs) throws IDOutOfRangeException {
		super(proj, studs);
		// TODO Auto-generated constructor stub
	}

	public String getSQL(int memberPosition) {
		String sql = "(" + getID().replace("Pr", "") + ",";;
		sql += getMembers().get(memberPosition).getID().replace("S", "") + ")";
		
		return sql;
	}


	
	
}
