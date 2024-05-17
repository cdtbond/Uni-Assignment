package Objects.Student;

import Exceptions.IDOutOfRangeException;
import Exceptions.IncorrectRangeException;
import Objects.ParentObject;
import Objects.Supporting.Skills;

public class Student extends ParentObject {
	private static final long serialVersionUID = -1571929985736159527L;
	private Double averageGrade;
	private Skills skills;
	private static String prefix = "S";
	public Student(String ID, Integer progRank, Integer anRank, Integer netRank, Integer webRank)
			throws IncorrectRangeException {
		super(ID);
		Skills skills = new Skills(progRank,anRank,netRank,webRank);
		this.setSkills(skills);
		this.setAverageGrade(progRank, anRank, netRank, webRank);
	}

	public Student(String id, Skills skills) {
		super(id);
		this.setSkills(skills);
	}

	public Student(String ID, String progRank, String anRank, String netRank, String webRank) throws NumberFormatException, IncorrectRangeException {
		super(ID);
		Integer progInt = Integer.parseInt(progRank.substring(2));
		Integer anInt = Integer.parseInt(anRank.substring(2));
		Integer netInt = Integer.parseInt(netRank.substring(2));
		Integer webInt = Integer.parseInt(webRank.substring(2));
		
		Skills skills = new Skills(progInt,anInt,netInt,webInt);
		this.setSkills(skills);
		this.setAverageGrade(progInt, anInt, netInt, webInt);
		
	}

	public Student(int ID, int progRank, int anRank, int netRank, int webRank) throws NumberFormatException, IncorrectRangeException, IDOutOfRangeException {
		super(ID);
		Integer progInt = progRank;
		Integer anInt = anRank;
		Integer netInt = netRank;
		Integer webInt = webRank;
		
		Skills skills = new Skills(progInt,anInt,netInt,webInt);
		this.setSkills(skills);
		this.setAverageGrade(progInt, anInt, netInt, webInt);
		
	}
	
	public Double getAverageGrade() {
		return averageGrade;
	}


	public void setAverageGrade(Integer grade1,Integer grade2,Integer grade3, Integer grade4){
		
		int interimGrade = grade1 + grade2 + grade3 + grade4;
		this.averageGrade = (double)interimGrade/4;

	}


	public Skills getSkills() {
		return skills;
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
	
	
	
	private void setSkills(Skills skills) {
		this.skills = skills;
	}

	public String toString() {
		return getID() + ";" + "P " + getSkills().getProgSkill() + ";" + "A " + getSkills().getAnSkill() + ";"+"N " + getSkills().getNetSkill() + ";"+"W "+getSkills().getWebSkill();
	}

	@Override
	public String addPrefix() {
		return prefix;
	}

	@Override
	public String removePrefix() {
		return getID().replace(prefix, "");
	}

}
