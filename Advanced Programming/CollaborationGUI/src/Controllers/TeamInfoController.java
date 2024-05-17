package Controllers;

import Data.HashMaps;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TeamInfoController {

    @FXML     private Label teamIdLabel;
    @FXML     private Label teamTitleLabel;
    @FXML     private Label teamAnSkillLabel;
    @FXML     private Label teamProgSkillLabel;
    @FXML     private Label teamNetSkillLabel;
    @FXML     private Label teamWebSkillLabel;
    
    @FXML     private Label s1IdLabel;
    @FXML     private Label s2IdLabel;
    @FXML     private Label s3IdLabel;
    @FXML     private Label s4IdLabel;


    @FXML     private Label s1Prefs;
    @FXML     private Label s2Prefs;
    @FXML     private Label s3Prefs;
    @FXML     private Label s4Prefs;

	@FXML 	  private Button returnToTeamMetricsButton;
	
	
    @FXML     private Label s1Personality;
    @FXML     private Label s2Personality;
    @FXML     private Label s3Personality;
    @FXML     private Label s4Personality;
    
    @FXML     private Label s1Enemies;
    @FXML     private Label s2Enemies;
    @FXML     private Label s3Enemies;
    @FXML     private Label s4Enemies;
    
    @FXML     private Label s1AnSkill;
    @FXML     private Label s2AnSkill;
    @FXML     private Label s3AnSkill;
    @FXML     private Label s4AnSkill;
    
    @FXML     private Label s1ProgSkill;
    @FXML     private Label s2ProgSkill;
    @FXML     private Label s3ProgSkill;
    @FXML     private Label s4ProgSkill;
    
    @FXML     private Label s1NetSkill;
    @FXML     private Label s2NetSkill;
    @FXML     private Label s3NetSkill;
    @FXML     private Label s4NetSkill;
	
    @FXML     private Label s1WebSkill;
    @FXML     private Label s2WebSkill;
    @FXML     private Label s3WebSkill;
    @FXML     private Label s4WebSkill;
    
	HashMaps h1;
	Team currentTeam;
	
    public void setHashmaps(HashMaps hm) {
    	h1 = hm;
    }
    public void setLabelsTeam(Team team) {
    	currentTeam = team;
    	updateLabels();
    }
	
    private String removeS0DifficultStudents(String stud) {
    	if(stud=="S0") {
    		return "";
    	} else {
    		return stud;
    	}
    }
    
    
	private void updateLabels() {
		//Set Team Labels
		teamIdLabel.setText(currentTeam.getID());
		teamTitleLabel.setText(currentTeam.getTitle());
		teamProgSkillLabel.setText(currentTeam.getProgSkill()+"");
		teamAnSkillLabel.setText(currentTeam.getAnSkill()+"");
		teamNetSkillLabel.setText(currentTeam.getNetSkill()+"");
		teamWebSkillLabel.setText(currentTeam.getWebSkill()+"");
		
		StudentInfoWithPrefs currentStudent = 	currentTeam.getMembers().get(0);
		//First Student
		s1IdLabel.setText(currentStudent.getID());
		s1ProgSkill.setText(currentStudent.getProgSkill()+"");
		s1AnSkill.setText(currentStudent.getAnSkill()+"");
		s1NetSkill.setText(currentStudent.getNetSkill()+"");
		s1WebSkill.setText(currentStudent.getWebSkill()+"");
		s1Personality.setText(currentStudent.getPersonality().getPersonalityId()+"");
		s1Prefs.setText(currentStudent.getPreferenceOne() +" " + currentStudent.getPreferenceTwo() +" " + currentStudent.getPreferenceThree() +" " + currentStudent.getPreferenceFour());
		s1Enemies.setText(removeS0DifficultStudents(currentStudent.getDifficultStudents().get(0)) + " " + removeS0DifficultStudents(currentStudent.getDifficultStudents().get(1)));
		
		currentStudent = 	currentTeam.getMembers().get(1);
		//Second Student
		s2IdLabel.setText(currentStudent.getID());
		s2ProgSkill.setText(currentStudent.getProgSkill()+"");
		s2AnSkill.setText(currentStudent.getAnSkill()+"");
		s2NetSkill.setText(currentStudent.getNetSkill()+"");
		s2WebSkill.setText(currentStudent.getWebSkill()+"");
		s2Personality.setText(currentStudent.getPersonality().getPersonalityId()+"");
		s2Prefs.setText(currentStudent.getPreferenceOne() +" " + currentStudent.getPreferenceTwo() +" " + currentStudent.getPreferenceThree() +" " + currentStudent.getPreferenceFour());
		s2Enemies.setText(removeS0DifficultStudents(currentStudent.getDifficultStudents().get(0)) + " " + removeS0DifficultStudents(currentStudent.getDifficultStudents().get(1)));
		
		currentStudent = 	currentTeam.getMembers().get(2);
		//Third Student
		s3IdLabel.setText(currentStudent.getID());
		s3ProgSkill.setText(currentStudent.getProgSkill()+"");
		s3AnSkill.setText(currentStudent.getAnSkill()+"");
		s3NetSkill.setText(currentStudent.getNetSkill()+"");
		s3WebSkill.setText(currentStudent.getWebSkill()+"");
		s3Personality.setText(currentStudent.getPersonality().getPersonalityId()+"");
		s3Prefs.setText(currentStudent.getPreferenceOne() +" " + currentStudent.getPreferenceTwo() +" " + currentStudent.getPreferenceThree() +" " + currentStudent.getPreferenceFour());
		s3Enemies.setText(removeS0DifficultStudents(currentStudent.getDifficultStudents().get(0)) + " " + removeS0DifficultStudents(currentStudent.getDifficultStudents().get(1)));
		
		currentStudent = 	currentTeam.getMembers().get(3);
		//Four Student
		s4IdLabel.setText(currentStudent.getID());
		s4ProgSkill.setText(currentStudent.getProgSkill()+"");
		s4AnSkill.setText(currentStudent.getAnSkill()+"");
		s4NetSkill.setText(currentStudent.getNetSkill()+"");
		s4WebSkill.setText(currentStudent.getWebSkill()+"");
		s4Personality.setText(currentStudent.getPersonality().getPersonalityId()+"");
		s4Prefs.setText(currentStudent.getPreferenceOne() +" " + currentStudent.getPreferenceTwo() +" " + currentStudent.getPreferenceThree() +" " + currentStudent.getPreferenceFour());
		s4Enemies.setText(removeS0DifficultStudents(currentStudent.getDifficultStudents().get(0)) + " " + removeS0DifficultStudents(currentStudent.getDifficultStudents().get(1)));
	}
	
	
	@FXML public void returnToTeamMetrics(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/TeamMetrics.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			TeamMetricsController controller = loader.getController();
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
