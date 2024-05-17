package OtherObjects;

import java.util.ArrayList;



public class StandardDeviation {

	
	//Standard Deviation

    public double stDev(ArrayList<Double> numbers){
    	double mean = 0.0;
    	int length = numbers.size();
    	double interimValue = 0.0;
    	
    	//get average of all the numbers
    	for(int i=0;i<length;i++) {
    		mean += numbers.get(i);
    		
    	}
    	mean = (mean/length);
    	
    	//get the variance from mean 'squared'
    	for(int i=0;i<length;i++) {
    		interimValue += square(numbers.get(i)-mean);
    	}
    	//Divide interim value by length 
    	interimValue = (interimValue/length);
    	//finally square root the value
    	interimValue = Math.sqrt(interimValue);
    	return Math.round(interimValue*100.0)/100.0;
  }
    private double square(double x) {
    	return x * x;
    }
	
	
}
