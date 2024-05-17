package OtherObjects;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Ranking implements Serializable{

	private static final long serialVersionUID = -719055024146276718L;
	HashMap<String,Integer> rank = new HashMap<String, Integer>();
	
	public Ranking() {

	}
	
	public void addKey(String key) {
		rank.put(key, 0);
	}
	
	
	public void addVote(String key, int value) {
		int newValue = rank.get(key);
		newValue += value;
		rank.put(key, newValue);
	}
	
	public HashMap<String,Integer> removeBottomOfList(Integer numberToRemove) {
		Iterator iter = rank.entrySet().iterator();
		for(int i=0;i <= numberToRemove;i++) {
			Map.Entry pair = (Map.Entry)iter.next();
			rank.remove(pair.getKey());
		}
		return rank;
	}
	
	
    // function to sort hashmap by values 
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> finalHM = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            finalHM.put(aa.getKey(), aa.getValue()); 
        } 
        return finalHM; 
    }
	
}
