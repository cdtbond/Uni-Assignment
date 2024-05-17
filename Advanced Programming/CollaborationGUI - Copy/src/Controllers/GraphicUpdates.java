package Controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Data.HashMaps;
import Exceptions.InvalidTeamException;
import Objects.Team;
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
	    		String[] shortlistIds = new String[5];
	    		shortlistIds = 	h1.getShortlistIds();

	    		for (int i=0;i<teamLabels.size();i++) {
		    		
		    		Team team = h1.getTeam(shortlistIds[i]);
		    		

		    		//set the team1 label
		    		teamLabels.get(i).setText(shortlistIds[i]);
		    		
		    		Integer teamSize = team.getMembers().size();
		    		ArrayList<String> labels = getLabels(teamSize,team);
		    		
		    		ArrayList<Label> studArray = studLabels.get(i);
		    		
		    		
		    		for (int j=0;j<studArray.size();j++) {
		    			System.out.println(labels.get(j));
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
			if (teamSize==0) {
				labels.add("");
				labels.add("");
				labels.add("");
				labels.add("");
	   		    			
			} else if (teamSize==1) {
				labels.add(team.getMembers().get(0).getID());
				labels.add("");
				labels.add("");
				labels.add("");
			} else if (teamSize==2) {
				labels.add(team.getMembers().get(0).getID());
				labels.add(team.getMembers().get(1).getID());
				labels.add("");
				labels.add("");
			} else if (teamSize==3) {
				labels.add(team.getMembers().get(0).getID());
				labels.add(team.getMembers().get(1).getID());
				labels.add(team.getMembers().get(2).getID());
				labels.add("");
			} else {
				labels.add(team.getMembers().get(0).getID());
				labels.add(team.getMembers().get(1).getID());
				labels.add(team.getMembers().get(2).getID());
				labels.add(team.getMembers().get(3).getID());
			}
			return labels;
	    }
	    
		private void updateGraphs()
		{	   Map<String,Double> series1 = new TreeMap<String,Double>();
		       try {
		       h1.getTeams();
		       String[] shortlistIds = new String[5];
	   		   shortlistIds = 	h1.getShortlistIds();
		       
		       
		       series1.put("Team 1",h1.getTeam(shortlistIds[0]).getFirstAndSecondPrefsPercent());
	           series1.put("Team 2",h1.getTeam(shortlistIds[1]).getFirstAndSecondPrefsPercent());
		       series1.put("Team 3",h1.getTeam(shortlistIds[2]).getFirstAndSecondPrefsPercent()); 
		       series1.put("Team 4",h1.getTeam(shortlistIds[3]).getFirstAndSecondPrefsPercent()); 
		       series1.put("Team 5",h1.getTeam(shortlistIds[4]).getFirstAndSecondPrefsPercent()); 
		       XYChart.Series dataSeries1  = new XYChart.Series();	             
		       
		       Map<String,Double> series2 = new TreeMap<String,Double>();
		       series2.put("Team 1",h1.getTeam(shortlistIds[0]).getAverageSkillCompetency());       
		       series2.put("Team 2",h1.getTeam(shortlistIds[1]).getAverageSkillCompetency());
		       series2.put("Team 3",h1.getTeam(shortlistIds[2]).getAverageSkillCompetency()); 
		       series2.put("Team 4",h1.getTeam(shortlistIds[3]).getAverageSkillCompetency()); 
		       series2.put("Team 5",h1.getTeam(shortlistIds[4]).getAverageSkillCompetency());         
		       XYChart.Series dataSeries2  = new XYChart.Series();
		        
		       
		       Map<String,Double> series3 = new TreeMap<String,Double>();
		       series3.put("Team 1",h1.getTeam(shortlistIds[0]).getSkillShortfall());
		       series3.put("Team 2",h1.getTeam(shortlistIds[1]).getSkillShortfall());
		       series3.put("Team 3",h1.getTeam(shortlistIds[2]).getSkillShortfall()); 
		       series3.put("Team 4",h1.getTeam(shortlistIds[3]).getSkillShortfall()); 
		       series3.put("Team 5",h1.getTeam(shortlistIds[4]).getSkillShortfall());         
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
		       
		       stDevs.get(0).setText("Std Dev = "+h1.getSdGettingFirstNSecondPref());
		       stDevs.get(1).setText("Std Dev = "+h1.getSdShortfall());
		       stDevs.get(2).setText("Std Dev = "+h1.getSdSkillCompetency());
		       
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