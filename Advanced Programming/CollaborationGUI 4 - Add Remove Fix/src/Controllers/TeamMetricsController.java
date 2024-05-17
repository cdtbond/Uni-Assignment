package Controllers;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

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
import Helpers.Undo.CommandManager;
import Helpers.Undo.LogCommand;
import ObjectiveFunction.DefaultObjectiveWeighting;
import Objects.Project.Team;
import Objects.Student.StudentInfoWithPrefs;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
	private Label[][] labelArray;
	private CheckBox[][] checkboxArray;
	private Label[] teamLabelArray;

	@FXML private HBox studentHBox;
    
    
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
    
    @FXML private Button suggest;
   
	   //standard deviations labels
    private void setupSDLabels() {
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
	   labelArgs = new LabelArguments(h1, labelArray, teamLabelArray, studentId, barCharts, sdLabels);
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
        	for(int i=0;i<checkboxArray.length;i++) {        		
        		for(int j=0;j<checkboxArray[i].length;j++) {
        			if(checkboxArray[i][j].isSelected()) {
        				checkBoxes.add(teams.get(i));
        				String stud = h1.getTeam(teams.get(i)).getMembers().get(j).getID();
        				checkBoxes.add(stud);
        			}
        		}
        		}
       		} catch(IndexOutOfBoundsException e) {
    		checkBoxes.add("");
    	}
    	return checkBoxes;
    }
    
    
    private ArrayList<String> getTeamsFromCheckBoxesSelected() throws InvalidTeamException {
    	//Getting Set of keys from HashMap 
    	Set<String> keySet = h1.getTeams().keySet();
    	         
    	//Creating an ArrayList of keys by passing the keySet     	         
    	ArrayList<String> teams = new ArrayList<String>(keySet);
    	ArrayList<String> checkBoxes = new ArrayList<String>();
    	try {
    	for(int i=0;i<checkboxArray.length;i++) {
    		
    		for(int j=0;j<checkboxArray[i].length;j++) {
    			if(checkboxArray[i][j].isSelected())
    				checkBoxes.add(teams.get(i));
    		}
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

	
	@FXML public void changeObjectiveFunction(ActionEvent event) {
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

	   public void alterFunction(ActionEvent event) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/ObjWeight.fxml"));
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

	
	
	public void setHashmaps(HashMaps hm) {
		h1 = hm;
		addTeamGrids(hm.getArrayTeams());
		setupSDLabels();
		setUpGraphs();
		instantiateLabelArguments();
		
		Platform.runLater(new GraphicUpdates(labelArgs));
		//graphics.updateLabels(h1, studentIdLabels, teamLabels, studentId);	
        //graphics.updateGraphs(hm, barCharts, sdLabels);
        studentId.getItems().addAll(h1.getUnassignedStudents());
	}

	
	
	   public void backgroundSwapSuggestion() {
		   h1.getTeams().size();
		   
	   }
	
	   private void setGridProperties(GridPane grid, int i) {
		   if((i % 2) == 0) {
				grid.setStyle("-fx-background-color: #C0C0C0;");
			}
			else {
				grid.setStyle("-fx-background-color: #8A8A8A;");
			}
			grid.setMaxWidth(Double.MAX_VALUE);
			grid.setHgap(10);
			grid.setVgap(5);
			
	   }
	   
	   private void setupTeamLabel(Label teamLabel, Team team,GridPane grid, int i) {
			//grid.setPrefWidth(Double.MAX_VALUE);
			teamLabel.setText(team.getID());
			grid.add(teamLabel, 0, 0);
			teamLabelArray[i] = teamLabel;
	   }
	   
	   
		private void addTeamGrids(ArrayList<Team> teams) {
			teams.get(0);
			this.labelArray = new Label[teams.size()][teams.get(0).getMaxStuds()];
			this.checkboxArray = new CheckBox[teams.size()][teams.get(0).getMaxStuds()];
			this.teamLabelArray = new Label[teams.size()];
			
			for(int i=0;i<teams.size();i++) {
				Team team = teams.get(i);
				
				//set up the teams grid
				GridPane grid = new GridPane();
				setGridProperties(grid,i);
				
				//add title
				Label teamLabel = new Label();
				setupTeamLabel(teamLabel,team,grid,i);
				//Students
				setStudentsLabels(team,grid,i);
				
				//place in gridpane
				studentHBox.getChildren().add(grid);
				studentHBox.setHgrow(grid, Priority.ALWAYS);
		}
			
			studentHBox.setSpacing(20);
	}
	
	
	
	
	
	private void setStudentsLabels(Team team,GridPane grid,int i) {
		//Students
		ArrayList<StudentInfoWithPrefs> studs = team.getMembers();
		for(int j=0;j<studs.size();j++) {
			Label studLabel = new Label();
			studLabel.setText(studs.get(j).getID());
			CheckBox studCheck = new CheckBox();					
			grid.add(studLabel, 0, j+1);
			grid.add(studCheck, 1, j+1);
			labelArray[i][j] = studLabel;
			checkboxArray[i][j] = studCheck;
	}
			
		}

	public synchronized void displayMessage(String string) {
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
		
		displayMessage(h1.autoCalculateObjectiveFunction(objWeight));		
		
        //Platform.runLater(new SuggestedSwap(h1)); 
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
