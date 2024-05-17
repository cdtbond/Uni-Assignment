package Objects.SQLObjects;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Objects.SQL;
import Objects.Project.Project;
import Objects.Supporting.Skills;

public class ProjectSQL extends Project implements SQL {

	public ProjectSQL(ResultSet rs) throws SQLException, IncorrectRankException, IncorrectRangeException, IDOutOfRangeException {
		super(rs.getInt("ID"),rs.getString("Title"),rs.getString("Description"),"Own" + rs.getString("OwnerId"),rs.getInt("Skill1"),rs.getInt("Skill2"),rs.getInt("Skill3"),rs.getInt("Skill4"));
		}
		
	public ProjectSQL(Project project) {
		super(project);
	}

	public String getSQL() {
		return "(" + getID().replace("Pr", "") + ",'" + getTitle() + "','" + getDescription() + "'," + getOwnerId().replace("Own", "") + "," + Math.round(getSkills().getProgSkill()) + ","+ Math.round(getSkills().getAnSkill()) + "," + Math.round(getSkills().getNetSkill()) + "," + Math.round(getSkills().getWebSkill()) + ")";
	}
	
	public String getShortlistSQL() {
		return "(" + getID().replace("Pr", "") + ")";
	}
}
