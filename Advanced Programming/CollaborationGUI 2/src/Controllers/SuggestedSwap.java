package Controllers;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import Data.HashMaps;
import ObjectiveFunction.DefaultObjectiveWeighting;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class SuggestedSwap implements Runnable {
	HashMaps h1;
	private static AtomicInteger at = new AtomicInteger(0);
    private final AtomicBoolean stop = new AtomicBoolean(false);
	private String response;

	
	
	public SuggestedSwap(HashMaps h1,Button swapButton) {
		this.h1 = h1;
		
		
	//	response = h1.autoCalculateObjectiveFunction(weighting);
	}
	
	
	
	
	
	@Override
	public void run() {
		TeamMetricsController tmc = returnController();
		System.out.println(response);
		tmc.displayMessage(response);
	}

	
	
	
	public TeamMetricsController returnController() {
		TeamMetricsController tmc = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/TeamMetrics.fxml"));
			tmc = loader.getController();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tmc;
	}
	
	
}
