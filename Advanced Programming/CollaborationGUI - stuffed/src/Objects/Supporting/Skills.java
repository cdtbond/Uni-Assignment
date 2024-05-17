package Objects.Supporting;

import java.io.Serializable;
import java.util.ArrayList;

import Exceptions.IncorrectRangeException;

public class Skills implements Serializable{

	private static final long serialVersionUID = -4466697960548588383L;
	private Integer skillFloor = 0;
	private Integer skillRoof = 4;
	private ArrayList<Skill> skills = new ArrayList<Skill>();
	
	public Skills(Integer progValue, Integer netValue, Integer anValue, Integer webValue) throws IncorrectRangeException {
		Skill prog = new progSkill((double)progValue);
		Skill net = new progSkill((double)netValue);
		Skill an = new progSkill((double)anValue);
		Skill web = new progSkill((double)webValue);
		
		checkWithinRange(progValue);
		skills.add(prog);
		checkWithinRange(netValue);
		skills.add(net);
		checkWithinRange(anValue);		
		skills.add(an);
		checkWithinRange(webValue);		
		skills.add(web);
		
	}
	
	
	public Skills(double progSkill, double netSkill, double anSkill, double webSkill) {
		Skill prog = new progSkill(progSkill);
		Skill net = new progSkill(netSkill);
		Skill an = new progSkill(anSkill);
		Skill web = new progSkill(webSkill);
		
		skills.add(prog);
		skills.add(net);		
		skills.add(an);	
		skills.add(web);
	}


	public Skills(ArrayList<Skill> skills) {
		this.skills = skills;
	}


	public void setSkills(Integer skillFirst,Integer skillSecond,Integer skillThird, Integer skillFourth) {
		ArrayList<Integer> skills = new ArrayList<Integer>();
		skills.add(skillFirst);
		skills.add(skillSecond);
		skills.add(skillThird);
		skills.add(skillFourth);
	}
	
	private void checkWithinRange(int value) throws IncorrectRangeException{
		if ((value >= skillFloor && value <= skillRoof)) {
			
		}
		else {
			throw new IncorrectRangeException();
		}
	}
	
	
	
	
	public void printSkills() {
		
		for (Skill skill : skills) {
			System.out.print(skill.getName() + " is " + skill.getValue());
		}
	}
	

	public ArrayList<Skill> getSkills() {
		return skills;
	}
	
	public void setProgSkill(Double value) {
		for (Skill skill : skills) {
			if (skill instanceof progSkill) {
				skill.setValue(value);
			}
			else {
				System.out.println("No programming skill");
			}
		}
	}
	
	public void setNetSkill(Double value) {
		for (Skill skill : skills) {
			if (skill instanceof netSkill) {
				skill.setValue(value);
			}
			else {
				System.out.println("No net skill");
			}
		}
	}	
	
	public void setAnSkill(Double value) {
		for (Skill skill : skills) {
			if (skill instanceof anSkill) {
				skill.setValue(value);
			}
			else {
				System.out.println("No analysis skill");
			}
		}
	}
	
	public void setWebSkill(Double value) {
		for (Skill skill : skills) {
			if (skill instanceof webSkill) {
				skill.setValue(value);
			}
			else {
				System.out.println("No web skill");
			}
		}
	}
	
	public Double getProgSkill() {
		return skills.get(0).getValue();
	}	
	public Double getNetSkill() {
		return skills.get(1).getValue();
	}
	public Double getAnSkill() {
		return skills.get(2).getValue();
	}
	public Double getWebSkill() {
		return skills.get(3).getValue();
	}
}
