package Helpers;

import java.util.ArrayList;

import Objects.Project.Team;

public class QuickSortTeams
{

	/**
	 * Sort array.
	 *
	 * @param array Array to be sorted.
	 * @return 
	 */
    public ArrayList<Team> sort(ArrayList<Team> array,String projId) {
    	sort(array, 0, array.size()-1,projId);
    	return array;
    } // end of sort()


    /**
     * Quicksort partition function.  Swap and divide into two partitions.
     *
     * @param array Array to be sorted.
     * @param leftIndex The leftmost position to sort from (inclusive).
     * @param leftIndex The rightmost position to sort to (inclusive).
     *
     * @return Index where pivot is placed.
     */
    protected int partition(ArrayList<Team> array, int leftIndex, int rightIndex,String projId) {
        // pivot is at first element
        double pivot = array.get(leftIndex).getOf().getObjValue();
        int i = leftIndex + 1;
        int j = rightIndex;

        while (i <= j) {
        	while (array.get(i).getOf().getObjValue() < pivot && i < rightIndex) {
                i++;
            }
        	while (array.get(j).getOf().getObjValue() > pivot) {
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

    
    /**
     * Median of three.
     *
     * @param teams Array to take median form.
     * @param leftIndex The leftmost position to take median from (inclusive).
     * @param leftIndex The rightmost position to take median from (inclusive).
     */
    protected void sort(ArrayList<Team> teams, int leftIndex, int rightIndex,String projId) {
    	if (rightIndex - leftIndex < 3) {
    		return;
    	}

    	int midIndex = (int) Math.floor((rightIndex + leftIndex) / 2);

    	// find median
    	int medianIndex;
    	if (teams.get(leftIndex).getOf().getObjValue() <= teams.get(midIndex).getOf().getObjValue()) {
    		if (teams.get(midIndex).getOf().getObjValue() <= teams.get(rightIndex).getOf().getObjValue()) {
    			medianIndex = midIndex;
    		}
    		else if (teams.get(leftIndex).getOf().getObjValue() <= teams.get(rightIndex).getOf().getObjValue()) {
    			medianIndex = rightIndex;
    		}
    		else {
    			medianIndex = leftIndex;
    		}
    	}
    	else if (teams.get(leftIndex).getOf().getObjValue() <= teams.get(rightIndex).getOf().getObjValue()) {
    		medianIndex = leftIndex;
    	}
    	else if (teams.get(midIndex).getOf().getObjValue() <= teams.get(rightIndex).getOf().getObjValue()) {
    		medianIndex = rightIndex;
    	}
    	else {
    		medianIndex = midIndex;
    	}

    	// move median to array[leftIndex], which will become the pivot.
    	swap(teams, medianIndex, leftIndex);
    } // end of medianPivot()

} // end of class QuickSort
