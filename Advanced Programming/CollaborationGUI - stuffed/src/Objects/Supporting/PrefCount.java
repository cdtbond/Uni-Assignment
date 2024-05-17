package Objects.Supporting;

import Exceptions.IDOutOfRangeException;
import Objects.ParentObject;

public class PrefCount extends ParentObject implements Comparable<PrefCount> {
	private Integer count;
	
	
	public PrefCount(String ID,Integer count) throws IDOutOfRangeException {
		super(ID);
		setCount(count);
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	@Override
	public int compareTo(PrefCount prefs) {
		 return (this.getCount() < prefs.getCount() ? -1 : 
	            (this.getCount() == prefs.getCount() ? 0 : 1));  
	}
	
	  @Override     
	  public String toString() {         
	    return "ID: " + getID() + "; Count: " + getCount();     
	  }
	

}
