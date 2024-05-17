package Helpers.Undo;

import java.util.ArrayList;

public class LogCommand {
	private String command;
	private ArrayList<String> checkBoxes = new ArrayList<String>();
	
	//called by add student method, will say remove, String - Team, String - Student
	public LogCommand(String command,ArrayList<String> checkBoxes, String newString) {
		this.command = command;
		this.checkBoxes.add(checkBoxes.get(0));
		this.checkBoxes.add(newString);
	}
	
	//called by remove student method, will say add, String - Team, String - Student
	public LogCommand(String command,ArrayList<String> checkBoxes) {
		this.command = command;
		for (int i=0; i<checkBoxes.size();i++) {
			this.checkBoxes.add(checkBoxes.get(i));
		}
	}
	
	
	public String getCommand() {
		return command;
	}
	public ArrayList<String> getCheckBoxes() {
		return checkBoxes;
	}

	
	
	
}
