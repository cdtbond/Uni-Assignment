package Controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import Data.HashMaps;
import Exceptions.InvalidTeamException;
import Objects.Project.Team;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GraphicUpdates implements Runnable{

	HashMaps h1;
	ArrayList<ArrayList<Label>> studLabels;
	ArrayList<Label> teamLabels;
	ComboBox studentCombo;
	ArrayList<BarChart> charts;
	ArrayList<Label> stDevs;
	
	
	
	public GraphicUpdates(LabelArguments labelArgs) {
		this.h1 = labelArgs.getH1();
		this.studLabels = labelArgs.getStudLabels();
		this.teamLabels = labelArgs.getTeamLabels();
		this.studentCombo = labelArgs.getStudentCombo();
		this.charts = labelArgs.getCharts();
		this.stDevs = labelArgs.getStDevs();
	}
	
	
	   private void updateLabels() {
	    	try {
	    		
	        	//Getting Set of keys from HashMap 
	        	Set<String> keySet = h1.getTeams().keySet();
	        	         
	        	//Creating an ArrayList of keys by passing the keySet 
	        	         
	        	ArrayList<String> teams = new ArrayList<String>(keySet);
	        	

	    		for (int i=0;i<teamLabels.size();i++) {
		    		
		    		Team team = h1.getTeam(teams.get(i));
		    		

		    		//set the team1 label
		    		teamLabels.get(i).setText(teams.get(i));
		    		
		    		Integer teamSize = team.getMembers().size();
		    		ArrayList<String> labels = getLabels(teamSize,team);
		    		
		    		ArrayList<Label> studArray = studLabels.get(i);
		    		
		    		
		    		for (int j=0;j<studArray.size();j++) {
		    			studArray.get(j).setText(labels.get(j));
		    		}    		
		    		
	    		}
	    		
	    		studentCombo.getItems().clear();
	            studentCombo.getItems().addAll(h1.getUnassignedStudents());
				
				
			} catch (InvalidTeamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
}
	   
	    private ArrayList<String> getLabels(int teamSize,Team team) {
	    	ArrayList<String> labels = new ArrayList<String>();
	    	int emptySlots = 4-teamSize;
	    	
	    	for(int i=0;i<teamSize;i++) {
	    		labels.add(team.getMembers().get(i).getID());
	    	}
	    	for(int i=0;i<emptySlots;i++) {
	    		labels.add("");
	    	}
			return labels;
	    }
	    
		private void updateGraphs()
		{	   Map<String,Double> series1 = new TreeMap<String,Double>();
			   Map<String,Double> series2 = new TreeMap<String,Double>();
		       Map<String,Double> series3 = new TreeMap<String,Double>();
		       try {
		       h1.getTeams();
	           Set<String> keySet = h1.getTeams().keySet();
	           ArrayList<String> teams = new ArrayList<String>(keySet);
	           
	   		   
		       for(int i =0;i<teams.size();i++) {
		    	   series1.put(teams.get(i),h1.getTeam(teams.get(i)).getFirstAndSecondPrefsPercent());
		    	   series2.put(teams.get(i),h1.getTeam(teams.get(i)).getAverageSkillCompetency()); 
		    	   series3.put(teams.get(i),h1.getTeam(teams.get(i)).getSkillShortfall());
		       }
		       XYChart.Series dataSeries1  = new XYChart.Series();	             
		       XYChart.Series dataSeries2  = new XYChart.Series();		             
		       XYChart.Series dataSeries3  = new XYChart.Series();
		       
		       
		       dataSeries1.setName("Teams");
		       for (String c : series1.keySet() )
		          dataSeries1.getData().add(new XYChart.Data(c, series1.get(c))); 
		       charts.get(0).getData().clear();
		       charts.get(0).getData().add(dataSeries1);	

		        		       
		       dataSeries2.setName("Teams");
		       for (String c : series2.keySet() )
		          dataSeries2.getData().add(new XYChart.Data(c, series2.get(c)));  
		       charts.get(1).getData().clear();
		       charts.get(1).getData().add(dataSeries2);
		       
		       
		       dataSeries3.setName("Teams");
		       for (String c : series3.keySet() )
		          dataSeries3.getData().add(new XYChart.Data(c, series3.get(c)));  
		       charts.get(2).getData().clear();
		       charts.get(2).getData().add(dataSeries3);
		       
		       h1.computeSDGettingFirstNSecondPref();	       
		       h1.computeSdSkillCompetency();	       
		       h1.computeSDSkillShortfall();	       
		       
		       stDevs.get(0).setText("Std Dev = "+h1.getFirstAndSecondPref());
		       stDevs.get(1).setText("Std Dev = "+h1.getShortfall());
		       stDevs.get(2).setText("Std Dev = "+h1.getSkillCompetency());
		       
		       } catch (InvalidTeamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		}

		@Override
		public void run() {
			updateLabels();
			updateGraphs();
			
		}
	    
	    
}