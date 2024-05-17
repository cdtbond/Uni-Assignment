package Objects;
import OtherObjects.Skills;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;

public class Project extends ParentObject {

	private static final long serialVersionUID = 2162981913724762422L;
	private String title;
	private String description;
	private String ownerId;
	private Skills skills;
	
	public Project(String ID, String title,String description,String ownerId, Integer progRank, Integer anRank, Integer netRank, Integer webRank) throws IncorrectRankException, IncorrectRangeException {
		super(ID);
		this.setTitle(title);
		this.setDescription(description);
		this.setOwnerId(ownerId);
		checkRanks(progRank,anRank,netRank,webRank);
		this.setSkills(new Skills(progRank,netRank,anRank,webRank));
	}
	
	public Project(Project proj) {
		super(proj.getID());
		this.setTitle(proj.getTitle());
		this.setDescription(proj.getDescription());
		this.setOwnerId(proj.getOwnerId());
		this.setSkills(proj.getSkills());
	}

	public Project(ResultSet rs) throws SQLException {
		super("Pr" + rs.getInt("ID"));
		this.setTitle(rs.getString("Title"));
		this.setDescription(rs.getString("Description"));
		this.setOwnerId("Own" + rs.getString("OwnerId"));
		this.setSkills(new Skills(rs.getInt("Skill1"),rs.getInt("Skill2"),rs.getInt("Skill3"),rs.getInt("Skill4")));
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Skills getSkills() {
		return skills;
	}
	public void setSkills(Skills skills) {
		this.skills = skills;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	@Override
	public String toString() {
		return getID() + ";" + getTitle() + ";" + getDescription() + ";" + getOwnerId() + ";P "+  getSkills().getProgSkill() + ";A "+ getSkills().getAnSkill() + ";N " + getSkills().getNetSkill() + ";W " + getSkills().getWebSkill();
	}

	
	public String getSQL() {
		return "(" + getID().replace("Pr", "") + ",'" + getTitle() + "','" + getDescription() + "'," + getOwnerId().replace("Own", "") + "," + Math.round(getSkills().getProgSkill()) + ","+ Math.round(getSkills().getAnSkill()) + "," + Math.round(getSkills().getNetSkill()) + "," + Math.round(getSkills().getWebSkill()) + ")";
	}
	
	public String getShortlistSQL() {
		return "(" + getID().replace("Pr", "") + ")";
	}
	
	
	public double getAnSkill() {
		return skills.getAnSkill();
	}
	public double getNetSkill() {
		return skills.getNetSkill();
	}
	public double getProgSkill() {
		return skills.getProgSkill();
	}
	public double getWebSkill() {
		return skills.getWebSkill();
	}
	
	private void checkRanks(Integer skillRankFirst,Integer skillRankSecond, Integer skillRankThird, Integer skillRankFourth) throws IncorrectRankException {
		ArrayList<Integer> skillsSort = new ArrayList<Integer>();
		
		skillsSort.add(skillRankFirst);
		skillsSort.add(skillRankSecond);
		skillsSort.add(skillRankThird);
		skillsSort.add(skillRankFourth);		
		
		Collections.sort(skillsSort);
		
	
		if (checkEqual(skillsSort.get(0),1) && checkEqual(skillsSort.get(1),2) && checkEqual(skillsSort.get(2),3) && checkEqual(skillsSort.get(3),4)) {
			//Correct	
		}
				
		else {
			throw new IncorrectRankException();
		}
	}
	
	private boolean checkEqual(int value1, int value2) {
		return value1 == value2;
	}
	
	
	public double computeSkillShortfall(StudentInfoWithPrefs stud) {
			double anSkill;
			double netSkill;
			double progSkill;
			double webSkill;
			
			if(getAnSkill()>stud.getAnSkill()) {
				anSkill = getAnSkill() - stud.getAnSkill();
			}
			else {
				anSkill = 0.0;
			}
			if(getNetSkill()>stud.getNetSkill()) {
				netSkill = getNetSkill() - stud.getNetSkill();
			}
			else {
				netSkill = 0.0;
			}
			if (getProgSkill()>stud.getProgSkill()) {
				progSkill = getProgSkill() - stud.getProgSkill();	
			}
			else {
				progSkill = 0.0;
			}
			if (getWebSkill()>stud.getWebSkill()) {
				webSkill = getWebSkill() - stud.getWebSkill();
			}
			else {
				webSkill = 0.0;
			}
			
			return (anSkill + netSkill + progSkill + webSkill);
		
	}
	
	
}
