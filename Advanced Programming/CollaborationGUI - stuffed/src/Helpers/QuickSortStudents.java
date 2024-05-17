package Helpers;

import java.util.ArrayList;

import Objects.ParentObject;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Student.StudentInfoWithPrefsMetrics;

public class QuickSortStudents
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
    private int partition(ArrayList<StudentInfoWithPrefs> array, int leftIndex, int rightIndex,String projId) {
        try {
    	// pivot is at first element
    	StudentInfoWithPrefsMetrics pStud = new StudentInfoWithPrefsMetrics(array.get(leftIndex));
        double pivot = pStud.getObjectiveValue(projId);
        int i = leftIndex + 1;
        int j = rightIndex;
        StudentInfoWithPrefsMetrics stud1 = new StudentInfoWithPrefsMetrics(array.get(i));
        StudentInfoWithPrefsMetrics stud2 = new StudentInfoWithPrefsMetrics(array.get(j));
        
        while (i <= j) {
        	while (stud1.getObjectiveValue(projId) < pivot && i < rightIndex) {
                i++;
            }
        	while (stud2.getObjectiveValue(projId) > pivot) {
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
        } catch(Exception e) {
        	e.printStackTrace();
        }
		return 0;
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

    	try {
    	
    	if (rightIndex - leftIndex < 3) {
    		return;
    	}

    	int midIndex = (int) Math.floor((rightIndex + leftIndex) / 2);

        StudentInfoWithPrefsMetrics leftStud = new StudentInfoWithPrefsMetrics(array.get(leftIndex));
        StudentInfoWithPrefsMetrics midStud = new StudentInfoWithPrefsMetrics( array.get(midIndex));
        StudentInfoWithPrefsMetrics rightStud = new StudentInfoWithPrefsMetrics( array.get(rightIndex));
    	
    	// find median
    	int medianIndex;
    	if (leftStud.getObjectiveValue(projId) <= midStud.getObjectiveValue(projId)) {
    		if (midStud.getObjectiveValue(projId) <= rightStud.getObjectiveValue(projId)) {
    			medianIndex = midIndex;
    		}
    		else if (leftStud.getObjectiveValue(projId) <= rightStud.getObjectiveValue(projId)) {
    			medianIndex = rightIndex;
    		}
    		else {
    			medianIndex = leftIndex;
    		}
    	}
    	else if (leftStud.getObjectiveValue(projId) <= rightStud.getObjectiveValue(projId)) {
    		medianIndex = leftIndex;
    	}
    	else if (midStud.getObjectiveValue(projId) <= rightStud.getObjectiveValue(projId)) {
    		medianIndex = rightIndex;
    	}
    	else {
    		medianIndex = midIndex;
    	}

    	// move median to array[leftIndex], which will become the pivot.
    	swap(array, medianIndex, leftIndex);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } // end of medianPivot()

} // end of class QuickSort
