package Objects;

import java.util.ArrayList;

public class QuickSort
{

	/**
	 * Sort array.
	 *
	 * @param array Array to be sorted.
	 * @return 
	 */
    public ArrayList<StudentInfoWithPrefs> sort(ArrayList<StudentInfoWithPrefs> array,String projId) {
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
    protected int partition(ArrayList<StudentInfoWithPrefs> array, int leftIndex, int rightIndex,String projId) {
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


    private final void swap(ArrayList<StudentInfoWithPrefs> array, int i, int j)
    {
        StudentInfoWithPrefs tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    } // end of swap()

    
    /**
     * Median of three.
     *
     * @param array Array to take median form.
     * @param leftIndex The leftmost position to take median from (inclusive).
     * @param leftIndex The rightmost position to take median from (inclusive).
     */
    protected void sort(ArrayList<StudentInfoWithPrefs> array, int leftIndex, int rightIndex,String projId) {
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
