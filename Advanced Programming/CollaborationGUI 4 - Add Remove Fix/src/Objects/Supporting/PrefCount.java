package Objects.Supporting;

import Exceptions.IDOutOfRangeException;
import Objects.ParentObject;

public class PrefCount extends ParentObject implements Comparable<PrefCount> {
	private static final long serialVersionUID = -3951044584153255062L;
	private Integer count;
	private static String prefix = "S";
	
	public PrefCount(String ID,Integer count) {
		super(ID);
		setCount(count);
	}
	public PrefCount(int ID,Integer count) throws IDOutOfRangeException {
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


	@Override
	public String addPrefix() {
		// TODO Auto-generated method stub
		return prefix;
	}


	@Override
	public String removePrefix() {
		// TODO Auto-generated method stub
		return getID().replace(prefix, "");
	}
	

}
