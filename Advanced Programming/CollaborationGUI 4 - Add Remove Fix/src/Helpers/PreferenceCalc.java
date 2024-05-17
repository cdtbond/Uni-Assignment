package Helpers;

import java.util.ArrayList;

import Objects.Supporting.PrefCount;
import Objects.Supporting.PrefCountSorter;

public class PreferenceCalc {
	private ArrayList<PrefCount> prefCounts = new ArrayList<PrefCount>();
	
	public void addFour(String str) {
		
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 4;
				prefCounts.get(i).setCount(value);
			}
		}
	}
	
	public void addThree(String str) {
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 3;
				prefCounts.get(i).setCount(value);
			}
		}
	}

	public void addTwo(String str) {
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 2;
				prefCounts.get(i).setCount(value);
			}
		};
	}
	
	public void addOne(String str) {
		
		for (int i = 0;i<prefCounts.size();i++) {
			if (prefCounts.get(i).getID()==str) {
				Integer value = prefCounts.get(i).getCount();
				value += 1;
				prefCounts.get(i).setCount(value);
			}
		}
	}
	public ArrayList<String> returnKeysToRemove(Integer qtyToRemove) {
		
		PrefCountSorter prefCountSorter = new PrefCountSorter(prefCounts);
		ArrayList<PrefCount> sortedPrefCount = prefCountSorter.getSortedPrefCountByValue();
		
		ArrayList<String> projectsToRemove = new ArrayList<String>();
		
		for(int i=0;i<qtyToRemove;i++) {
			projectsToRemove.add(sortedPrefCount.get(i).getID());
		}
		
		return projectsToRemove;
	}
	
	public String stringPrefCount() {
		String outOutput = "";
		for (int i=0;i<prefCounts.size();i++) {
			 outOutput += prefCounts.get(i).toString() + "\n";
		}
		return outOutput;
	}

	public void addpref(PrefCount pc) {
		prefCounts.add(pc);
	}
}
