package Controllers;

import java.util.ArrayList;

import Data.HashMaps;
import Objects.Project.Project;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Supporting.PersonalityTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateStudentsController {
	HashMaps h1;
	MenuController mc;
	
	   @FXML private ChoiceBox pref1;    
	   @FXML private ChoiceBox pref2;
	   @FXML private ChoiceBox pref3;
	   @FXML private ChoiceBox pref4;
	   
	   
	   @FXML private ChoiceBox infoStudentSelect;
	   @FXML private ChoiceBox personality;
	   @FXML private ChoiceBox prefsStudentSelect;

	   @FXML private ChoiceBox enemy2;
	   @FXML private ChoiceBox enemy1;
	   
	   
	   
	   @FXML public void addStudentPrefs(ActionEvent event) {

		   
		   
	    }

	   @FXML
	    void clearStudentPrefs(ActionEvent event) {
		  
	    }

	   @FXML
	    void clearStudentInfo(ActionEvent event) {

	    }

	   @FXML
	    void addStudentInfo(ActionEvent event) {

	    }
	
	    private void clearFields(ArrayList<TextField> array) {
	    	for(int i=0;i<array.size();i++) {
	    		array.get(i).clear();
	    	}
	    }
	
    public void setHashmaps(HashMaps hm) {
    	h1 = hm;
		   ArrayList<String> studIds = new ArrayList<String>();
		   ArrayList<StudentInfoWithPrefs> studs = h1.getArrayStudentInfoWithPrefs();
		   for(int i=0;i<studs.size();i++) {
			   studIds.add(studs.get(i).getID());
		   }
		   infoStudentSelect.getItems().addAll(studIds);
		   prefsStudentSelect.getItems().addAll(studIds);
		   enemy1.getItems().addAll(studIds);
		   enemy2.getItems().addAll(studIds);
		   
		   ArrayList<String> projIds = new ArrayList<String>();
		   ArrayList<Project> projs = h1.getArrayProjects();
		   for(int i=0;i<projs.size();i++) {
			   projIds.add(projs.get(i).getID());
		   }
		   pref1.getItems().addAll(projIds);
		   pref2.getItems().addAll(projIds);
		   pref3.getItems().addAll(projIds);
		   pref4.getItems().addAll(projIds);
		   
		   PersonalityTypes personalityType = new PersonalityTypes();
		   personality.getItems().addAll(personalityType.getPersonalityIds());
    }

	
	@FXML public void returnToMenu(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/Menu.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			MenuController controller = loader.getController();
			controller.setHashmaps(h1);
			//get Stage information
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
