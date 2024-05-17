package Controllers;

import java.io.Serializable;

import Data.HashMaps;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Menu.ShortlistingMenu;
import SharedValues.AverageGrade;
import dataStorage.DatabaseInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuController implements Serializable {
	private static final long serialVersionUID = 6749915344898168788L;
	private static HashMaps h1;
	ShortlistingMenu sl = new ShortlistingMenu();
	private static DatabaseInterface di = new DatabaseInterface();
	
    @FXML     private Button addResourcesButton;
    @FXML     private Button quitButton;
    @FXML     private Button shortlistProjectButton;
    @FXML     private Button updateStudentsButton;
    @FXML     private Button teamMetricsButton;
	

    
	
    @FXML public void setOnCloseRequest(ActionEvent actionEvent) {
        Scene scene = quitButton.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        
    	di.insertAllObjectsIntoSQL(h1);
    	stage.close();
    }
    
    
    
    
    
    
	@FXML public void startTeamMetricsGUI(ActionEvent event) {
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

	
	@FXML public void startAddResourcesGUI(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/AddResources.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			AddResourcesController controller = loader.getController();
			controller.setHashmaps(h1);
			//get Stage information
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void startUpdateStudentsGUI(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/UpdateStudents.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			UpdateStudentsController controller = loader.getController();
			controller.setHashmaps(h1);
			//get Stage information
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void throwErrorAlert(String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText(headerText);
	    alert.setContentText(contentText);
	    alert.showAndWait();
		
	}

	
	
	public void shortlistProjects() {
		Double projectsNeeded = (double) (h1.getStudentCount()/4);
		Integer projectsNeededInt = (int) Math.ceil(projectsNeeded);
		Integer projectsToRemove = h1.getProjectsCount() - projectsNeededInt;
		h1.makeShortlist(h1.returnKeysToRemove(projectsToRemove));
		//finally merge the studentInfo and Student preferences into one object
		//add new students into unassignedStudents
		
		try {
			h1.mergeStudentInfoAndPreferences();
			h1.addAllStudentsToUnassignedStudentsHashMap();
		} catch (IncorrectPersonalityTypeException e) {
			throwErrorAlert("Merge Error","Error in merging preferences and student info, Incorrect Personality Type.");
		} catch (IncorrectGradeException e) {
			throwErrorAlert("Merge Error","Error in merging preferences and student info, Incorrect Grade.");
		} catch (IncorrectRangeException e) {
			throwErrorAlert("Merge Error","Error in merging preferences and student info, Incorrect Range.");
		}
		calculateAverageGrade();
	}
	//calculate the average grade at this point
	
	public void calculateAverageGrade() {
		AverageGrade average = AverageGrade.getSingleton();
		average.calcAndSetAverageGrade(h1.getStudents());
	}
	
	
	
	
	
	
	
	public void initialize() {
		
    	//Get from Serialization
		//HandleSerialization hs = new HandleSerialization();
    	//h1 = hs.loadSerializedInputToGraphics();

    	//Get from Database
		h1 = di.loadSQL();
    	h1.setMenuController(this);
	}







	public void setHashmaps(HashMaps hm) {
		h1 = hm;
	}
    
	
	
}
