package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import Data.HashMaps;
import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Exceptions.IncorrectRangeException;
import Exceptions.IncorrectRankException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNamingException;
import Objects.Project.Company;
import Objects.Project.Owner;
import Objects.Project.Project;
import Objects.Student.Student;
import Objects.Supporting.Skills;
import Objects.Supporting.anSkill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddResourcesController {


	
	HashMaps h1;
	// Company
	@FXML Button returnToMenuButton;
	private ArrayList<TextField> companyFields = new ArrayList<TextField>();
    @FXML private TextField cId;
    @FXML private TextField cName;
    @FXML private TextField abn;
    @FXML private TextField url;
	@FXML private TextField address;
    
	//owner
	private ArrayList<TextField> ownerFields = new ArrayList<TextField>();
    @FXML  private TextField ownId;
    @FXML  private TextField ownFirstName;
    @FXML  private TextField ownCompId;
    @FXML  private TextField ownSurname;
    @FXML  private TextField ownProjId;
    @FXML  private TextField ownRole;
    @FXML  private TextField ownEmail;
    

    
    //Project
	private ArrayList<TextField> projectFields = new ArrayList<TextField>();
	private ArrayList<ChoiceBox> projectChoiceBoxes = new ArrayList<ChoiceBox>();
    @FXML  private TextField prId;
    @FXML  private TextField prTitle;
    @FXML  private TextField PrDesc;
    @FXML  private TextField projOwnId;
    @FXML  private ChoiceBox rank1Combo;
    @FXML  private ChoiceBox rank2Combo;
    @FXML  private ChoiceBox rank3Combo;
    @FXML  private ChoiceBox rank4Combo;


    
    
    //student
	private ArrayList<TextField> studentFields = new ArrayList<TextField>();
	private ArrayList<ChoiceBox> studentChoiceBoxes = new ArrayList<ChoiceBox>();
    @FXML  private TextField sId;
    @FXML  private ChoiceBox anMark;
    @FXML  private ChoiceBox webMark;
    @FXML  private ChoiceBox progMark;
    @FXML  private ChoiceBox netMark;
    

	
	private void throwErrorAlert(String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Exception Dialog");
	    alert.setHeaderText(headerText);
	    alert.setContentText(contentText);
	    alert.showAndWait();
		
	}
	
    

	
    @FXML public void addCompany(ActionEvent event) {	
    	try {
			Company comp = new Company(Integer.getInteger(cId.getText()),cName.getText(),abn.getText(),url.getText(),address.getText());
			h1.addCompany(comp);
			clearFields(companyFields);
		} catch (SQLException e) {
			throwErrorAlert("SQL Exception", "This entry thew a SQL Exception, try again");
		} catch (IDOutOfRangeException e) {
			throwErrorAlert("Id out of range", "The specified id was outside the range allowed for");
		} catch (ABNValuesIncorrectException e) {
			throwErrorAlert("ABN Incorrect characters", "Only integer numbers make up the 11 digit ABN");
		} catch (ABNLengthIncorrectException e) {
			throwErrorAlert("ABN Length incorrect", "11 digits are required for a valid ABN");
		} catch (IdAlreadyUsedException e) {
			throwErrorAlert("Id Already Used", "That Id is already in use by an existing company");
		}
    	
    }
    @FXML  public void addProject(ActionEvent event) {
		try {
			System.out.println(prId.getText());
			System.out.println(prTitle.getText());
			System.out.println(PrDesc.getText());
			System.out.println(projOwnId.getText());
			System.out.println(rank1Combo.getValue());
			System.out.println(rank2Combo.getValue());
			System.out.println(rank3Combo.getValue());
			System.out.println(rank4Combo.getValue());
			
			
    	Project proj = new Project(Integer.getInteger(prId.getText()),prTitle.getText(),PrDesc.getText(),projOwnId.getText(),(int) rank1Combo.getValue(),(int) rank2Combo.getValue(),(int) rank3Combo.getValue(),(int) rank4Combo.getValue());
    	h1.addProject(proj);
		clearFields(projectFields);
    			
		} catch (IncorrectRankException e) {
			throwErrorAlert("Ranking Incorrect", "The specified rank was incorrect, each type can only be selected once");
		} catch (IncorrectRangeException e) {
			throwErrorAlert("Incorrect range", "The specified id was incorrect");
		} catch (IDOutOfRangeException e) {
			throwErrorAlert("Id out of range", "The specified was outside the range allowed for");
		} catch (IdAlreadyUsedException e) {
			throwErrorAlert("Id Already Used", "That Id is already in use by an existing project");
		}
    	
    }

    
    @FXML public void addOwner(ActionEvent event) {
		try {
	    	Owner own = new Owner(Integer.getInteger(ownId.getText()),ownFirstName.getText() + " " +ownSurname.getText(),ownRole.getText(),ownEmail.getText(),ownProjId.getText(),ownCompId.getText());
	    	h1.addOwner(own);
			clearFields(ownerFields);
	    			
		    } catch (IDOutOfRangeException e) {
				throwErrorAlert("Id out of range", "The specified was outside the range allowed for");
			} catch (IdAlreadyUsedException e) {
				throwErrorAlert("Id Already Used", "That Id is already in use by an existing project");
			} catch (InvalidNamingException e) {
				throwErrorAlert("Inavlid Name", "The name is invalid, choose another");
			} catch (InvalidEmailException e) {
				throwErrorAlert("Inavlid Email", "A valid email should include the characters '@' and '.'");
			}
    }

    
    @FXML public void addStudent(ActionEvent event) {
    		try {
				Student stud = new Student(sId.getText(),(int)progMark.getValue(),(int)anMark.getValue(),(int)netMark.getValue(),(int)webMark.getValue());
				h1.addStudents(stud);
				clearFields(studentFields);
			} catch (IncorrectRangeException e) {
				throwErrorAlert("Id out of range", "The specified was outside the range allowed for");			
			} catch (IdAlreadyUsedException e) {
				throwErrorAlert("Id already used", "That Id is already in use by an existing project");
			}
    }
	
    
    
    
    private void clearFields(ArrayList<TextField> array) {
    	for(int i=0;i<array.size();i++) {
    		array.get(i).clear();
    	}
    }
    

    
    @FXML public void cancelCompany(ActionEvent event) {
    	clearFields(companyFields);
    }
    @FXML  public void cancelProject(ActionEvent event) {
    	clearFields(projectFields);
    }
    @FXML  public void cancelOwner(ActionEvent event) {
    	clearFields(ownerFields);
    }
    @FXML public void cancelStudent(ActionEvent event) {
    	clearFields(studentFields);
    }
    public void setHashmaps(HashMaps hm) {
    	h1 = hm;
    	addCompanyToTextField();
    	addOwnerFieldsToTextField();
    	addStudentToTextField();
    	addProjectToTextField();
    	
    	Skills skills = new Skills(0,1,2,3);
    	ArrayList<String> marks = new ArrayList<String>();
    	for (int i=skills.getSkillFloor();i<=skills.getSkillRoof();i++) {
    		marks.add(i+"");
    	}
    	
        anMark.getItems().addAll(marks);
        webMark.getItems().addAll(marks);
        progMark.getItems().addAll(marks);
        netMark.getItems().addAll(marks);
        marks.remove(0);
        rank1Combo.getItems().addAll(marks);
        rank2Combo.getItems().addAll(marks);
        rank3Combo.getItems().addAll(marks);
        rank4Combo.getItems().addAll(marks);
    }

    private void addOwnerFieldsToTextField() {
    	ownerFields.add(ownId);
    	ownerFields.add(ownFirstName);
    	ownerFields.add(ownSurname);
    	ownerFields.add(ownCompId);
    	ownerFields.add(ownProjId);
    	ownerFields.add(ownRole);
    	ownerFields.add(ownEmail);
    }

    
    private void addCompanyToTextField() {
    	companyFields.add(cId);
    	companyFields.add(cName);
    	companyFields.add(abn);
    	companyFields.add(url);
    	companyFields.add(address);
    }
    
    private void addStudentToTextField() {
    	companyFields.add(sId);
    	studentChoiceBoxes.add(anMark);
    	studentChoiceBoxes.add(webMark);
    	studentChoiceBoxes.add(progMark);
    	studentChoiceBoxes.add(netMark);
    }
    

    private void addProjectToTextField() {
    	projectFields.add(prId);
    	projectFields.add(prTitle);
    	projectFields.add(PrDesc);
    	projectChoiceBoxes.add(rank1Combo);
    	projectChoiceBoxes.add(rank2Combo);
    	projectChoiceBoxes.add(rank3Combo);
    	projectChoiceBoxes.add(rank4Combo);
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
