package Controllers;

import java.util.ArrayList;

import Data.HashMaps;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectGradeException;
import Exceptions.IncorrectPersonalityTypeException;
import Exceptions.IncorrectRangeException;
import Exceptions.InvalidSKeyException;
import Objects.Project.Project;
import Objects.Student.Preferences;
import Objects.Student.Student;
import Objects.Student.StudentInfo;
import Objects.Student.StudentInfoWithPrefs;
import Objects.Supporting.PersonalityTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UpdateStudentsController {
	HashMaps h1;
	MenuController mc;
	
	ArrayList<ChoiceBox> preferences = new ArrayList<ChoiceBox>();
	   @FXML private ChoiceBox pref1;    
	   @FXML private ChoiceBox pref2;
	   @FXML private ChoiceBox pref3;
	   @FXML private ChoiceBox pref4;
	   @FXML private ChoiceBox prefsStudentSelect;
	   
	   ArrayList<ChoiceBox> info = new ArrayList<ChoiceBox>();
	   @FXML private ChoiceBox infoStudentSelect;
	   @FXML private ChoiceBox personality;
	   @FXML private ChoiceBox enemy2;
	   @FXML private ChoiceBox enemy1;
	   
	   
	   
	   @FXML public void addStudentPrefs(ActionEvent event) {
		   
		   
		   try {
			Preferences prefs = new Preferences((String) prefsStudentSelect.getValue(),(String) pref1.getValue(),(String) pref2.getValue(),(String) pref3.getValue(),(String) pref4.getValue());
			h1.addPreferences(prefs);
		   
		   
		   
		} catch (DuplicatePreferenceException e) {
			throwErrorAlert("Duplicate Preference", "A preference has been duplicated, select each project a maximum of once.");
		} catch (IdAlreadyUsedException e) {
			throwErrorAlert("Duplicate Id", "This student has already added their preferences.");
		}
		   
		   
	    }

	   @FXML
	    void clearStudentPrefs(ActionEvent event) {
		   clearBoxes(preferences);
	    }

	   @FXML
	    void clearStudentInfo(ActionEvent event) {
		   clearBoxes(info);
	    }

	   @FXML
	    void addStudentInfo(ActionEvent event) {
		   try {
			Student stud = h1.getStudent((String) infoStudentSelect.getValue());
			
			StudentInfo studInfo;
			
			//if 2 enemy students selected add to constructor
			if((String) enemy2.getValue() != "") {
				studInfo =	new StudentInfo(stud,(char) personality.getValue(),(String) enemy1.getValue(),(String) enemy2.getValue());
			} 
			//if 1 enemy student selected, add to constructor
			else if((String) enemy1.getValue() != "") {
				studInfo = new StudentInfo(stud,(char) personality.getValue(),(String) enemy1.getValue());
			} 
			//if 0 enemy students selected, don't add them to constructor
			else {
				studInfo = new StudentInfo(stud,(char) personality.getValue());
			}
			
			h1.addStudInfo(studInfo);
			
			
			
		} catch (InvalidSKeyException e) {
			throwErrorAlert("Student ID Mismatch", "That Id doesn't match a student, try again.");
		} catch (IncorrectPersonalityTypeException e) {
			throwErrorAlert("Incorrect Personality Type", "The selected personality type is not a valid option.");
		} catch (IncorrectGradeException e) {
			throwErrorAlert("Incorrect Grade", "The selected grade is not a valid option.");
		} catch (IncorrectRangeException e) {
			throwErrorAlert("Incorrect ID Range", "The id falls outside the predefined range.");
		} catch (IdAlreadyUsedException e) {
			throwErrorAlert("Id Used", "That id has already been entered.");
		}
	    }
	
	private void addChoiceBoxesToArrayList() {
		preferences.add(prefsStudentSelect);
		preferences.add(pref1);
		preferences.add(pref2);
		preferences.add(pref3);
		preferences.add(pref4);
		
		info.add(infoStudentSelect);
		info.add(personality);
		info.add(enemy1);
		info.add(enemy2);
	}
	   
	   
	private void clearBoxes(ArrayList<ChoiceBox> array) {
	    	for(int i=0;i<array.size();i++) {
	    		array.get(i).setSelectionModel(null);
	    	}
	    }
	
	private void addChoicesToChoiceBoxes() {
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
	    
    public void setHashmaps(HashMaps hm) {
    	h1 = hm;
    	addChoicesToChoiceBoxes();
    	addChoiceBoxesToArrayList();
    	
    }

	
	private void throwErrorAlert(String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText(headerText);
	    alert.setContentText(contentText);
	    alert.showAndWait();
		
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
