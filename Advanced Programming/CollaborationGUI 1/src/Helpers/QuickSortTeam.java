package Helpers;

import java.util.ArrayList;

import Objects.Project.Team;

public class QuickSortTeam
{


    public ArrayList<Team> sort(ArrayList<Team> array,String projId) {
    	sort(array, 0, array.size()-1,projId);
    	return array;
    } // end of sort()



    protected int partition(ArrayList<Team> array, int leftIndex, int rightIndex,String projId) {
        // pivot is at first element
        double pivot = array.get(leftIndex).getObjectiveValue(projId);
        int i = leftIndex + 1;
        int j = rightIndex;

        while (i <= j) {
        	while (array.get(i).getObjectiveValue(projId) < pivot && i < rightIndex) {
                i++;
            }
        	while (array.get(j).getObjectiveValue(projId) > pivot) {
                j--;
            }

            // swap array[i] and array[j], which are in the incorrect partitions
            // with respect to the pivot
        	if (i <= j) {
        		swap(array, i, j);
        		i++;
        	}
        }

        // swap pivot into its correct position in array
        swap(array, leftIndex, j);

        return j;
    } // end of partition()


    private final void swap(ArrayList<Team> array, int i, int j)
    {
        Team tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    } // end of swap()

    
 
    protected void sort(ArrayList<Team> array, int leftIndex, int rightIndex,String projId) {
    	if (rightIndex - leftIndex < 3) {
    		return;
    	}

    	int midIndex = (int) Math.floor((rightIndex + leftIndex) / 2);

    	// find median
    	int medianIndex;
    	if (array.get(leftIndex).getObjectiveValue(projId) <= array.get(midIndex).getObjectiveValue(projId)) {
    		if (array.get(midIndex).getObjectiveValue(projId) <= array.get(rightIndex).getObjectiveValue(projId)) {
    			medianIndex = midIndex;
    		}
    		else if (array.get(leftIndex).getObjectiveValue(projId) <= array.get(rightIndex).getObjectiveValue(projId)) {
    			medianIndex = rightIndex;
    		}
    		else {
    			medianIndex = leftIndex;
    		}
    	}
    	else if (array.get(leftIndex).getObjectiveValue(projId) <= array.get(rightIndex).getObjectiveValue(projId)) {
    		medianIndex = leftIndex;
    	}
    	else if (array.get(midIndex).getObjectiveValue(projId) <= array.get(rightIndex).getObjectiveValue(projId)) {
    		medianIndex = rightIndex;
    	}
    	else {
    		medianIndex = midIndex;
    	}

    	// move median to array[leftIndex], which will become the pivot.
    	swap(array, medianIndex, leftIndex);
    } // end of medianPivot()

} // end of class QuickSort
