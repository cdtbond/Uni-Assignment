package Controllers;

import Data.HashMaps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
	
	
	@FXML public void okButton() {
		skillText.toString();
	}
	
	@FXML public void cancelButton() {
		
	}
    
}
