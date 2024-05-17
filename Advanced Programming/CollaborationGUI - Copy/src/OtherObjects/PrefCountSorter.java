package OtherObjects;

import java.util.ArrayList;
import java.util.Collections;

public class PrefCountSorter {
	ArrayList<PrefCount> prefCount = new ArrayList<>();
	
	public PrefCountSorter(ArrayList<PrefCount> prefCount) {         
	    this.prefCount = prefCount;     
	  }       
	  public ArrayList<PrefCount> getSortedPrefCountByValue() {         
	    Collections.sort(prefCount);         
	    return prefCount;     
	  } 
}
