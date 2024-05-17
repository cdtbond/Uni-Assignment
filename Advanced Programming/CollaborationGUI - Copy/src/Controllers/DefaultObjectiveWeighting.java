package Controllers;

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
	
	
	public void changeObjectiveFunction(double skillShortfall, double averageCompetency, double firstAndSecondPrefs) {
		this.skillShortfall = skillShortfall;
		this.averageCompetency = averageCompetency;
		this.firstAndSecondPrefs = firstAndSecondPrefs;
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
