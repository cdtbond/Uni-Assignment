package Controllers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import Data.HashMaps;
import Exceptions.InvalidProjectException;
import Exceptions.InvalidStudentException;
import Exceptions.InvalidTeamException;
import Exceptions.NoLeaderException;
import Exceptions.PersonalityImbalanceException;
import Exceptions.RepeatedMemberException;
import Exceptions.SameTeamException;
import Exceptions.StudentConflictException;
import Exceptions.StudentNotSubstitutedException;
import Objects.Team;
import Undo.CommandManager;
import Undo.LogCommand;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TeamMetricsController implements Serializable {
	private static final long serialVersionUID = -3447836963816003565L;
	private HashMaps h1;
	private CommandManager cm = new CommandManager();
    private ArrayList<ArrayList<Label>> studentIdLabels = new ArrayList<ArrayList<Label>>();
	private ArrayList<Label> teamLabels = new ArrayList<Label>();	
	private ArrayList<Label> sdLabels = new ArrayList<Label>();
	private ArrayList<BarChart> barCharts = new ArrayList<BarChart>();
	private GraphicUpdates graphics;
	private LabelArguments labelArgs;
	private static DefaultObjectiveWeighting objWeight = DefaultObjectiveWeighting.getInstance();

    //team labels
    @FXML private Label team1Label;
    @FXML private Label team2Label;
    @FXML private Label team3Label;
    @FXML private Label team4Label;
    @FXML private Label team5Label;
    
    
    //team 1
    @FXML private Label t1s1;
    @FXML private Label t1s2;
    @FXML private Label t1s3;
    @FXML private Label t1s4;
    
    @FXML private CheckBox t1s1box;
    @FXML private CheckBox t1s2box;
    @FXML private CheckBox t1s3box;
    @FXML private CheckBox t1s4box;

    //team 2
    @FXML private Label t2s1;
    @FXML private Label t2s2;
    @FXML private Label t2s3;
    @FXML private Label t2s4;
    
    @FXML private CheckBox t2s1box;
    @FXML private CheckBox t2s2box;
    @FXML private CheckBox t2s3box;    
    @FXML private CheckBox t2s4box;
    
    
    //team 3
    @FXML private Label t3s1;
    @FXML private Label t3s2;
    @FXML private Label t3s3;
    @FXML private Label t3s4;
    
    @FXML private CheckBox t3s1box; 
    @FXML private CheckBox t3s2box;    
    @FXML private CheckBox t3s3box;   
    @FXML private CheckBox t3s4box;
    

    //team 4
    @FXML private Label t4s1;
    @FXML private Label t4s2;
    @FXML private Label t4s3;
    @FXML private Label t4s4;
    
    @FXML private CheckBox t4s1box;
    @FXML private CheckBox t4s2box;
    @FXML private CheckBox t4s3box;
    @FXML private CheckBox t4s4box;
    
    
    //team 5
    @FXML private Label t5s1;
    @FXML private Label t5s2;
    @FXML private Label t5s3;
    @FXML private Label t5s4;


    @FXML private CheckBox t5s1box;
    @FXML private CheckBox t5s2box;
    @FXML private CheckBox t5s3box;
    @FXML private CheckBox t5s4box;
    
    
    //Standard Deviation Labels
    @FXML private Label compSD;
    @FXML private Label skillSD;
    @FXML private Label prefSD;

    
    
    //Current Student Id
    @FXML private ComboBox<String> studentId;
    
    
    //bar charts
    @FXML private BarChart<?, ?> PrefChart;
    @FXML private BarChart<?, ?> SkillsChart;
    @FXML private BarChart<?, ?> CompChart;
       
    
   private void setupLabels() {
	   //add students to array
	   ArrayList<Label> teamOne = new ArrayList<Label>();
	   ArrayList<Label> teamTwo = new ArrayList<Label>();
	   ArrayList<Label> teamThree = new ArrayList<Label>();
	   ArrayList<Label> teamFour = new ArrayList<Label>();
	   ArrayList<Label> teamFive = new ArrayList<Label>();
	   
	   teamOne.add(t1s1);
	   teamOne.add(t1s2);
	   teamOne.add(t1s3);
	   teamOne.add(t1s4);

	   studentIdLabels.add(teamOne);

	   teamTwo.add(t2s1);
	   teamTwo.add(t2s2);
	   teamTwo.add(t2s3);
	   teamTwo.add(t2s4);
	   
	   studentIdLabels.add(teamTwo);
	   
	   teamThree.add(t3s1);
	   teamThree.add(t3s2);
	   teamThree.add(t3s3);
	   teamThree.add(t3s4);
	   
	   studentIdLabels.add(teamThree);
	   
	   teamFour.add(t4s1);
	   teamFour.add(t4s2);
	   teamFour.add(t4s3);
	   teamFour.add(t4s4);
	   
	   studentIdLabels.add(teamFour);
	   
	   teamFive.add(t5s1);
	   teamFive.add(t5s2);
	   teamFive.add(t5s3);
	   teamFive.add(t5s4);
	   
	   studentIdLabels.add(teamFive);
	   
	   //add teams to array
	   teamLabels.add(team1Label);
	   teamLabels.add(team2Label);
	   teamLabels.add(team3Label);
	   teamLabels.add(team4Label);
	   teamLabels.add(team5Label);
	   
	   
	   //standard deviations labels
	   
	   sdLabels.add(prefSD);
	   sdLabels.add(skillSD);
	   sdLabels.add(compSD);
	   
   }
   
   private void setUpGraphs() {
	   barCharts.add(PrefChart);
	   barCharts.add(SkillsChart);
	   barCharts.add(CompChart);
	   
   }
  
	
   private void instantiateLabelArguments() {
	   labelArgs = new LabelArguments(h1, studentIdLabels, teamLabels, studentId, barCharts, sdLabels);
   }
	
   
   
	public void swapStudents(ActionEvent evt) {
		
    	ArrayList<String> checkBoxesSelected;
		try {
			//extract the student and team from the checkboxes
			checkBoxesSelected = getStudentsFromCheckBoxesSelected();
			String stud1 = checkBoxesSelected.get(1);
			String stud2 = checkBoxesSelected.get(3);
			String team1 = checkBoxesSelected.get(0);
			String team2 = checkBoxesSelected.get(2);
			
			Integer twoStudentsSelected = 4; //this is because one of the entries is the team, and the other is the student.
			
    	//If there are four entries this is correct. 
    	if (checkBoxesSelected.size()==twoStudentsSelected) {    		
				try {
					
				cancelIfSameTeamSelected(team1,team2);
				
    			//might skip out at some point here
    			Team interimTeam1 = h1.createInterimTeam(team1, stud1, stud2);
    			Team interimTeam2 = h1.createInterimTeam(team2, stud2, stud1);
    			
    			//should not error at this point
    			h1.replaceTeam((team1),interimTeam1);
    			h1.replaceTeam((team2),interimTeam2);
    			LogCommand reverse = new LogCommand("Swap",checkBoxesSelected);
    			cm.execute(reverse);
    			Platform.runLater(new GraphicUpdates(labelArgs));
    			//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
    	        //graphics.updateGraphs(hm, barCharts, sdLabels);
    			 
				// retain if ongoing issues
					
				} catch (StudentConflictException e) {	
					studentConflictThrown(team1,team2,stud1,stud2);				
				} catch (PersonalityImbalanceException e) {
					personalityImbalanceThrown(team1,team2,stud1,stud2);				
				} catch (RepeatedMemberException e) {
					throwErrorAlert("RepeatedMember","This student is already added to the team");
 
				} catch (NoLeaderException e) {
					throwErrorAlert("No Leader","There isn't a leader in this Team");
					
				} catch (InvalidStudentException e) {
					throwErrorAlert("Invalid Student","The Student you are adding does not exist");

				} catch (StudentNotSubstitutedException e) {
					throwErrorAlert("Swap Student Error","There was an error in substitution");
					
				} catch (InvalidProjectException e) {
					throwErrorAlert("Project Error","There was an error in getting the team project");
					
				} catch (SameTeamException e) {
					throwErrorAlert("Same Team","Both Students are in the same team");
					
				} finally {
					h1.recalculateAllTeamMetrics();
				}
				
		}
    	//if more than 4 entries, create an error and do nothing
    	else if(checkBoxesSelected.size()>4) {
 	    	  tooManyCheckboxesTicked();
    	} 
    	//if 2 entries, create an error and do nothing
    	else if(checkBoxesSelected.size()==2) {
 	    	  notEnoughCheckboxesTicked();
    	} 
    	else if(checkBoxesSelected.get(0)==checkBoxesSelected.get(3)) {
    		checkboxesInSameTeamTicked();
    	}
    	//no checkboxes are selected
    	else {
    		noCheckboxesTicked();	
    	}
    	 
    	Platform.runLater(new GraphicUpdates(labelArgs));
		//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
        //graphics.updateGraphs(hm, barCharts, sdLabels);
		} catch (InvalidTeamException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void throwErrorAlert(String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText(headerText);
	    alert.setContentText(contentText);
	    alert.showAndWait();
		
	}

	private void personalityImbalanceThrown(String team1, String team2, String stud1, String stud2) {
		h1.reverseSwap(team1,team2,stud1,stud2);
		Alert alert = new Alert(AlertType.CONFIRMATION, "There is a personality imbalance on this team. Proceed to assign the student ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		//Proceed
		if (alert.getResult() == ButtonType.YES) {
			Team t1 = h1.createFinalTeam((team1),stud1,stud2);
			Team t2 = h1.createFinalTeam((team2),stud2,stud1);
			h1.replaceTeam(team1, t1);
			h1.replaceTeam(team2, t2);
		}
	}

	private void studentConflictThrown(String team1, String team2, String stud1, String stud2) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "The student you added either doesn't like another student, or isn't liked by a student on the team. Proceed to assign the student ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		//Proceed
		if (alert.getResult() == ButtonType.YES) {
    			Team t1 = h1.createFinalTeam((team1),stud1,stud2);
    			Team t2 = h1.createFinalTeam((team2),stud2,stud1);
				h1.replaceTeam(team1, t1);
				h1.replaceTeam(team2, t2);
		}
	}
	
	
	private void cancelIfSameTeamSelected(String team1, String team2) throws SameTeamException {
		if(team1.equals(team2)) {
			throw new SameTeamException();
		}
		
	}


	
	
	
	public void removeStudent(ActionEvent evt) {

    	ArrayList<String> checkBoxesSelected;
		try {
			checkBoxesSelected = getStudentsFromCheckBoxesSelected();
			System.out.println("Team - " + checkBoxesSelected.get(0) + " Student - " + checkBoxesSelected.get(1));
			LogCommand log = new LogCommand("Remove",checkBoxesSelected);

    	//If there are two entries, team and student this is correct
    	if (checkBoxesSelected.size()==2) {
				h1.removeTeamMember(checkBoxesSelected.get(0), checkBoxesSelected.get(1));
				cm.execute(log);
				Platform.runLater(new GraphicUpdates(labelArgs));
				//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
		        //graphics.updateGraphs(hm, barCharts, sdLabels);
    			
		}
    	//if more than 4 entries, create an error and do nothing
    	else if(checkBoxesSelected.size()>4) {
 	    	  tooManyCheckboxesTicked();
    	} 
    	//if 2 entries, create an error and do nothing
    	else if(checkBoxesSelected.size()==2) {
 	    	  notEnoughCheckboxesTicked();
    	} 
    	else if(checkBoxesSelected.get(0)==checkBoxesSelected.get(3)) {
    		checkboxesInSameTeamTicked();
    	}
    	//no checkboxes are selected
    	else {
    		noCheckboxesTicked();	
    	}
		
		} catch (InvalidTeamException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	@FXML public void actionButton(ActionEvent evt) {
		
	    
	    	   if (((Button)evt.getSource()).getText().compareTo("Add")== 0) {
	    	   	addStudent(evt);		     
	    	   }
	    	   else if (((Button)evt.getSource()).getText().compareTo("Swap")== 0) {
	    	       swapStudents(evt);	   
	    	   }
	    	   else if (((Button)evt.getSource()).getText().compareTo("Remove")== 0) {
	    		   removeStudent(evt);
	    	   }
	    	   else if (((Button)evt.getSource()).getText().compareTo("Return to Menu")== 0) {
	    		   returnToMenu(evt);
	    	   }
	    	   else {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    		    alert.setTitle("Exception Dialog");
	    		    alert.setHeaderText("Button not recognised");
	    		    alert.setContentText("The button that was pressed was not recognised");
	    		    alert.showAndWait();
	    	   }
	    	   
	}
	
    public void addStudent(ActionEvent evt) {
    	
    	String newString = (String) studentId.getValue();
    	
    	if(newString=="") {
			Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle("Exception Dialog");
		    alert.setHeaderText("No Student Selected");
		    alert.setContentText("No student has been selected, aborting");
		    alert.showAndWait();
    		
    	} else {

    	
    	ArrayList<String> checkBoxesSelected;
		try {
			checkBoxesSelected = getStudentsFromCheckBoxesSelected();
		

    	
    	//If there are two entries this is correct
    	if (checkBoxesSelected.size()==2) {

				try {
				LogCommand log = new LogCommand("Add",checkBoxesSelected,newString);
				h1.addStudentToTeam(checkBoxesSelected.get(0), newString);
    			cm.execute(log);
    			Platform.runLater(new GraphicUpdates(labelArgs));
    			//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
    	        //graphics.updateGraphs(hm, barCharts, sdLabels);
    			
    			} catch (StudentConflictException e) {
    				Alert alert = new Alert(AlertType.ERROR);
    			    alert.setTitle("Exception Dialog");
    			    alert.setHeaderText("Student Conflict");
    			    alert.setContentText("The Student you added either doesn't like another one on the team or isn't liked by one or more team members");
    			    alert.showAndWait(); 
				} catch (PersonalityImbalanceException e) {
    				Alert alert = new Alert(AlertType.ERROR);
    			    alert.setTitle("Exception Dialog");
    			    alert.setHeaderText("Personality Imbalance");
    			    alert.setContentText("There isn't enough variety of personalities for this team");
    			    alert.showAndWait(); 
				} catch (RepeatedMemberException e) {
    				Alert alert = new Alert(AlertType.ERROR);
    			    alert.setTitle("Exception Dialog");
    			    alert.setHeaderText("Repeated Member");
    			    alert.setContentText("This student is already added to the team");
    			    alert.showAndWait(); 
				} catch (NoLeaderException e) {
    				Alert alert = new Alert(AlertType.ERROR);
    			    alert.setTitle("Exception Dialog");
    			    alert.setHeaderText("No Leader");
    			    alert.setContentText("There isn't a leader in this Team");
    			    alert.showAndWait(); 
				} catch (InvalidStudentException e) {
    				Alert alert = new Alert(AlertType.ERROR);
    			    alert.setTitle("Exception Dialog");
    			    alert.setHeaderText("Invalid Student");
    			    alert.setContentText("The Student you are adding does not exist");
    			    alert.showAndWait(); 
				
    		}
		}
    	
    	//if more than 2 entries, create an error and do nothing
    	else if(checkBoxesSelected.size()>2) {
 	    	  tooManyCheckboxesTicked();
    	} 
    	//no checkboxes are selected
    	else {
    		noCheckboxesTicked();	
    	}
		} catch (InvalidTeamException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	}
    }
    
    private void noStudentEntered() {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText("Invalid CheckBoxes");
	    alert.setContentText("No student has been entered");
	    alert.showAndWait();  	
    }
    
    private void noCheckboxesTicked() {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText("Invalid CheckBoxes");
	    alert.setContentText("A checkbox must be selected");
	    alert.showAndWait();  	
    }
    
    private void tooManyCheckboxesTicked() {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText("Invalid CheckBoxes");
	    alert.setContentText("You've selected too many checkboxes");
	    alert.showAndWait(); 
    }
    
    private void notEnoughCheckboxesTicked() {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText("Invalid CheckBoxes");
	    alert.setContentText("You've only selected one checkbox, you must select two");
	    alert.showAndWait(); 
    }
	
    private void checkboxesInSameTeamTicked() {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText("Invalid CheckBoxes");
	    alert.setContentText("You can't select checkboxes within the same team");
	    alert.showAndWait(); 
    }
    
   //adds the team name and the student position
    private ArrayList<String> getStudentsFromCheckBoxesSelected() throws InvalidTeamException {
    	
    	//Getting Set of keys from HashMap 
    	Set<String> keySet = h1.getTeams().keySet();
    	         
    	//Creating an ArrayList of keys by passing the keySet 
    	         
    	ArrayList<String> teams = new ArrayList<String>(keySet);

    	
    	
    	ArrayList<String> checkBoxes = new ArrayList<String>();
    	try {
    	//team 1
    	if(t1s1box.isSelected()) {
    		checkBoxes.add(teams.get(0));
    		String stud = h1.getTeam(teams.get(0)).getMembers().get(0).getID();
    		checkBoxes.add(stud);
    	}
    	if(t1s2box.isSelected()) {
    		checkBoxes.add(teams.get(0));
    		String stud = h1.getTeam(teams.get(0)).getMembers().get(1).getID();
    		checkBoxes.add(stud);
    	}
    	if(t1s3box.isSelected()) {
    		checkBoxes.add(teams.get(0));
    		String stud = h1.getTeam(teams.get(0)).getMembers().get(2).getID();
    		checkBoxes.add(stud);
    	}
    	if(t1s4box.isSelected()) {
    		checkBoxes.add(teams.get(0));
    		String stud = h1.getTeam(teams.get(0)).getMembers().get(3).getID();
    		checkBoxes.add(stud);
    	}
    	//team 2
    	if(t2s1box.isSelected()) {
    		checkBoxes.add(teams.get(1));
    		String stud = h1.getTeam(teams.get(1)).getMembers().get(0).getID();
    		checkBoxes.add(stud);
    	}
    	if(t2s2box.isSelected()) {
    		checkBoxes.add(teams.get(1));
    		String stud = h1.getTeam(teams.get(1)).getMembers().get(1).getID();
    		checkBoxes.add(stud);
    	}
    	if(t2s3box.isSelected()) {
    		checkBoxes.add(teams.get(1));
    		String stud = h1.getTeam(teams.get(1)).getMembers().get(2).getID();
    		checkBoxes.add(stud);
    	}
    	if(t2s4box.isSelected()) {
    		checkBoxes.add(teams.get(1));
    		String stud = h1.getTeam(teams.get(1)).getMembers().get(3).getID();
    		checkBoxes.add(stud);
    	}
    	//team 3
    	if(t3s1box.isSelected()) {
    		checkBoxes.add(teams.get(2));
    		String stud = h1.getTeam(teams.get(2)).getMembers().get(0).getID();
    		checkBoxes.add(stud);
    	}
    	if(t3s2box.isSelected()) {
    		checkBoxes.add(teams.get(2));
    		String stud = h1.getTeam(teams.get(2)).getMembers().get(1).getID();
    		checkBoxes.add(stud);
    	}
    	if(t3s3box.isSelected()) {
    		checkBoxes.add(teams.get(2));
    		String stud = h1.getTeam(teams.get(2)).getMembers().get(2).getID();
    		checkBoxes.add(stud);
    	}
    	if(t3s4box.isSelected()) {
    		checkBoxes.add(teams.get(2));
    		String stud = h1.getTeam(teams.get(2)).getMembers().get(3).getID();
    		checkBoxes.add(stud);
    	}
    	//team 4
    	if(t4s1box.isSelected()) {
    		checkBoxes.add(teams.get(3));
    		String stud = h1.getTeam(teams.get(3)).getMembers().get(0).getID();
    		checkBoxes.add(stud);
    	}
    	if(t4s2box.isSelected()) {
    		checkBoxes.add(teams.get(3));
    		String stud = h1.getTeam(teams.get(3)).getMembers().get(1).getID();
    		checkBoxes.add(stud);
    	}
    	if(t4s3box.isSelected()) {
    		checkBoxes.add(teams.get(3));
    		String stud = h1.getTeam(teams.get(3)).getMembers().get(2).getID();
    		checkBoxes.add(stud);
    	}
    	if(t4s4box.isSelected()) {
    		checkBoxes.add(teams.get(3));
    		String stud = h1.getTeam(teams.get(3)).getMembers().get(3).getID();
    		checkBoxes.add(stud);
    	}
    	//team 5
    	if(t5s1box.isSelected()) {
    		checkBoxes.add(teams.get(4));
    		String stud = h1.getTeam(teams.get(4)).getMembers().get(0).getID();
    		checkBoxes.add(stud);
    	}
    	if(t5s2box.isSelected()) {
    		checkBoxes.add(teams.get(4));
    		String stud = h1.getTeam(teams.get(4)).getMembers().get(1).getID();
    		checkBoxes.add(stud);
    	}
    	if(t5s3box.isSelected()) {
    		checkBoxes.add(teams.get(4));
    		String stud = h1.getTeam(teams.get(4)).getMembers().get(2).getID();
    		checkBoxes.add(stud);
    	}
    	if(t5s4box.isSelected()) {
    		checkBoxes.add(teams.get(4));
    		String stud = h1.getTeam(teams.get(4)).getMembers().get(3).getID();
    		checkBoxes.add(stud);
    	}
    	} catch (IndexOutOfBoundsException e) {
    		checkBoxes.add("");
    	}
    	return checkBoxes;
    }
    
    //adds the team name and the student position
    private ArrayList<String> getTeamsFromCheckBoxesSelected() throws InvalidTeamException {
    	
    	//Getting Set of keys from HashMap 
    	Set<String> keySet = h1.getTeams().keySet();
    	         
    	//Creating an ArrayList of keys by passing the keySet 
    	         
    	ArrayList<String> teams = new ArrayList<String>(keySet);

    	ArrayList<String> checkBoxes = new ArrayList<String>();
    	try {
    	//team 1
    	if(t1s1box.isSelected() || t1s2box.isSelected() || t1s3box.isSelected() || t1s4box.isSelected()) {
    		checkBoxes.add(teams.get(0));
    	}
    	//team 2
    	if(t2s1box.isSelected() || t2s2box.isSelected() || t2s3box.isSelected() || t2s4box.isSelected()) {
    		checkBoxes.add(teams.get(1));
    	}
    	//team 3
    	if(t3s1box.isSelected() || t3s2box.isSelected() || t3s3box.isSelected() || t3s4box.isSelected()) {
    		checkBoxes.add(teams.get(2));
    	}
    	//team 3
    	if(t4s1box.isSelected() || t4s2box.isSelected() || t4s3box.isSelected() || t4s4box.isSelected()) {
    		checkBoxes.add(teams.get(3));
    	}
    	
    	} catch (IndexOutOfBoundsException e) {
    		checkBoxes.add("");
    	}
    	return checkBoxes;
    }
    
    
	@FXML public void getInfo(ActionEvent event) {
		try {
		ArrayList<String> checkBoxes = getTeamsFromCheckBoxesSelected(); 
		if(checkBoxes.size() != 1) {
			Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Exception Dialog");
		    alert.setHeaderText("Team Conflict");
		    alert.setContentText("Only select checkboxes in One Team");
		    alert.showAndWait(); 
		}
		else {
			switchToInfoView(event,checkBoxes);
		}
		
		} catch (InvalidTeamException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void switchToInfoView(ActionEvent event,ArrayList<String> checkBoxes) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/TeamInfo.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			TeamInfoController controller = loader.getController();
			controller.setHashmaps(h1);
			controller.setLabelsTeam(h1.getTeam(checkBoxes.get(0)));
			//get Stage information
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@FXML public void getChangeObjectiveFunction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/ObjectiveWeightings.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			
			ObjWeightController controller = loader.getController();
			controller.setHashmaps(h1);
			//get Stage information
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
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


	public void setHashmaps(HashMaps hm) {
		h1 = hm;
		setupLabels();
		setUpGraphs();
		instantiateLabelArguments();
		
		Platform.runLater(new GraphicUpdates(labelArgs));
		//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
        //graphics.updateGraphs(hm, barCharts, sdLabels);
        studentId.getItems().addAll(h1.getUnassignedStudents());
	}

	@FXML public void displayMessage(String string) {
		Alert alert = new Alert(AlertType.CONFIRMATION, string , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		//Proceed
		if (alert.getResult() == ButtonType.YES) {
			h1.assignTempTeamsToTeams();			
		}
		Platform.runLater(new GraphicUpdates(labelArgs));
		//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
        //graphics.updateGraphs(hm, barCharts, sdLabels);
		
	}
	
	@FXML public void getSuggestion(ActionEvent event) {
		
		displayMessage(h1.autoCalculateObjectiveFunction(objWeight.getSkillShortFall(),objWeight.getAverageCompetency(), objWeight.getFirstAndSecondPrefs()));
	}
    	
	
	private void undoAction() {
		LogCommand log = cm.undo();
		if(log.getCommand()=="Add") {
			removeStudent(log);
		}
		else if(log.getCommand()=="Remove") {
			try {
				addStudent(log);
			} catch (StudentConflictException | PersonalityImbalanceException | RepeatedMemberException
					| NoLeaderException | InvalidStudentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				swapStudent(log);
			} catch (StudentConflictException | PersonalityImbalanceException | RepeatedMemberException
					| NoLeaderException | StudentNotSubstitutedException | InvalidStudentException
					| InvalidProjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void swapStudent(LogCommand log) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, StudentNotSubstitutedException, InvalidStudentException, InvalidProjectException {
		//get the teams and students
		ArrayList<String> array = log.getCheckBoxes();	
		String stud1 = array.get(1);
		String stud2 = array.get(3);
		String team1 = array.get(0);
		String team2 = array.get(2);
		//might skip out at some point here
		Team interimTeam1 = h1.createInterimTeam(team1, stud2, stud1);
		Team interimTeam2 = h1.createInterimTeam(team2, stud1, stud2);
		//should not error at this point
		h1.replaceTeam((team1),interimTeam1);
		h1.replaceTeam((team2),interimTeam2);
		
	}

	private void addStudent(LogCommand log) throws StudentConflictException, PersonalityImbalanceException, RepeatedMemberException, NoLeaderException, InvalidStudentException {
		ArrayList<String> array = log.getCheckBoxes();
		h1.addStudentToTeam(array.get(0), array.get(1));	
	}

	private void removeStudent(LogCommand log) {
		ArrayList<String> array = log.getCheckBoxes();
		h1.removeTeamMember(array.get(0), array.get(1));
	}

	private void redoAction() {
		LogCommand log = cm.redo();
		if(log.getCommand()=="Add") {
			try {
				addStudent(log);
			} catch (StudentConflictException | PersonalityImbalanceException | RepeatedMemberException
					| NoLeaderException | InvalidStudentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(log.getCommand()=="Remove") {
			removeStudent(log);
		}
		else {
			try {
				swapStudent(log);
			} catch (StudentConflictException | PersonalityImbalanceException | RepeatedMemberException
					| NoLeaderException | StudentNotSubstitutedException | InvalidStudentException
					| InvalidProjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@FXML public void undo(ActionEvent event) {
		undoAction();
		Platform.runLater(new GraphicUpdates(labelArgs));
		//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
        //graphics.updateGraphs(hm, barCharts, sdLabels);
	}
	
	@FXML public void redo(ActionEvent event) {
		redoAction();
		Platform.runLater(new GraphicUpdates(labelArgs));
		//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
        //graphics.updateGraphs(hm, barCharts, sdLabels);
	}
	
    
}
