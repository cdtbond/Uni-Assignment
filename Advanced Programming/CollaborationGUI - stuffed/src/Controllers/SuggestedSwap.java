package Controllers;

import Data.HashMaps;
import ObjectiveFunction.DefaultObjectiveWeighting;
import javafx.fxml.FXMLLoader;

public class SuggestedSwap implements Runnable {

	private DefaultObjectiveWeighting weighting = DefaultObjectiveWeighting.getInstance();
	private String response;
	
	public SuggestedSwap(HashMaps h1) {
		response = h1.autoCalculateObjectiveFunction(weighting);
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
