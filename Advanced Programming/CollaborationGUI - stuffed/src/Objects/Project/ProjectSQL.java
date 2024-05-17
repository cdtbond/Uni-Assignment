package Objects.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Objects.SQL;

public class ProjectSQL extends Project implements SQL {

	private static final long serialVersionUID = -3379287992022726122L;

	public ProjectSQL(ResultSet rs) throws SQLException, IncorrectRankException, IncorrectRangeException, IDOutOfRangeException {
		super("Pr" + rs.getInt("ID"),rs.getString("Title"),rs.getString("Description"),"Own" + rs.getInt("OwnerId"),rs.getInt("Skill1"),rs.getInt("Skill2"),rs.getInt("Skill3"),rs.getInt("Skill4"));
	}
	
	public ProjectSQL(Project project) throws IDOutOfRangeException {
		super(project);
	}

	public String getSQL() {
		return "(" + getID().replace("Pr", "") + ",'" + getTitle() + "','" + getDescription() + "'," + getOwnerId().replace("Own", "") + "," + Math.round(getSkills().getProgSkill()) + ","+ Math.round(getSkills().getAnSkill()) + "," + Math.round(getSkills().getNetSkill()) + "," + Math.round(getSkills().getWebSkill()) + ")";
	}
	
	public String getShortlistSQL() {
		return "(" + getID().replace("Pr", "") + ")";
	}
}
