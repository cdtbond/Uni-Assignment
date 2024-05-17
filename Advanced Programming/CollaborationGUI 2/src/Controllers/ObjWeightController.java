package Controllers;

import Data.HashMaps;
import Exceptions.NotOneHundredPercentException;
import Helpers.Calculations;
import ObjectiveFunction.DefaultObjectiveWeighting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ObjWeightController {
    @FXML    private TextField skillText;
    @FXML    private TextField compText;
    @FXML    private TextField prefText;
    private HashMaps h1;
    
	private static DefaultObjectiveWeighting objWeight = DefaultObjectiveWeighting.getInstance();
	
    public void setHashmaps(HashMaps hm) {
    	h1 = hm;
    }
    
	private void startTeamMetricsGUI(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/TeamMetrics.fxml"));
			Parent tableViewParent = loader.load();
			
			Scene scene = new Scene(tableViewParent);
			
			TeamMetricsController controller = loader.getController();
			controller.setHashmaps(h1);
			//get Stage information
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private double labelTo2DecPlaces(TextField textfield) {
		return Calculations.round2DecPl(Double.parseDouble(textfield.getText()));
	}
	
	
	@FXML public void okButton(ActionEvent event) {
		try {
			double skill = labelTo2DecPlaces(skillText);
			double comp = labelTo2DecPlaces(compText);
			double pref = labelTo2DecPlaces(prefText);
			objWeight.changeObjectiveFunction(skill, comp, pref);
			startTeamMetricsGUI(event);
		} catch (NotOneHundredPercentException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle("Exception Dialog");
		    alert.setHeaderText("Numbers don't add up to 1");
		    alert.setContentText("The numbers entered do not add up to 1");
		    alert.showAndWait();
		}
	}
	
	@FXML public void cancelButton(ActionEvent event) {
		skillText.clear();
		compText.clear();
		prefText.clear();
		startTeamMetricsGUI(event);
	}
    
}
