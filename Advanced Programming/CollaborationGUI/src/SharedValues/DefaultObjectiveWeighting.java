package SharedValues;

import Exceptions.NotOneHundredPercentException;

public class DefaultObjectiveWeighting {
	
	private double skillShortfall = 0.6;
	private double averageCompetency = 0.3;
	private double firstAndSecondPrefs = 0.1;
	
    private static DefaultObjectiveWeighting INSTANCE;
   
    
    private DefaultObjectiveWeighting() {        
    }
    
    public static DefaultObjectiveWeighting getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DefaultObjectiveWeighting();
        }
        
        return INSTANCE;
    }
	
	
	public void changeObjectiveFunction(double skillShortfall, double averageCompetency, double firstAndSecondPrefs) throws NotOneHundredPercentException {
		if(notAddUpToOneHundredPercent(skillShortfall,averageCompetency,firstAndSecondPrefs)) {
			throw new NotOneHundredPercentException();
		}
		this.skillShortfall = skillShortfall;
		this.averageCompetency = averageCompetency;
		this.firstAndSecondPrefs = firstAndSecondPrefs;
	}
	
	
	private boolean notAddUpToOneHundredPercent(double first, double second, double third) {
		first = Math.round(first * 10);
		second = Math.round(second * 10);
		third = Math.round(third * 10);
		
		return (((first + second + third) / 10) != 1.0);
	}
	
	public Double getSkillShortFall() {
		return skillShortfall;
	}
	
	public Double getAverageCompetency() {
		return averageCompetency;
	}
	
	public Double getFirstAndSecondPrefs() {
		return firstAndSecondPrefs;
	}
}
