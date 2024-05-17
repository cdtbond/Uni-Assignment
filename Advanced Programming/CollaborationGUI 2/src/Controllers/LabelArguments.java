package Controllers;

import java.util.ArrayList;

import Data.HashMaps;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class LabelArguments {
	private HashMaps h1;
	private ArrayList<ArrayList<Label>> studLabels;
	private ArrayList<Label> teamLabels;
	private ComboBox studentCombo;
	private ArrayList<BarChart> charts;
	private ArrayList<Label> stDevs;
	
	public LabelArguments(HashMaps h1,ArrayList<ArrayList<Label>> studLabels,ArrayList<Label> teamLabels, ComboBox studentCombo, ArrayList<BarChart> charts, ArrayList<Label> stDevs) {
		this.h1 = h1;
		this.studLabels = studLabels;
		this.teamLabels = teamLabels;
		this.studentCombo = studentCombo;
		this.charts = charts;
		this.stDevs = stDevs;
	}

	public HashMaps getH1() {
		return h1;
	}

	public ArrayList<ArrayList<Label>> getStudLabels() {
		return studLabels;
	}

	public ArrayList<Label> getTeamLabels() {
		return teamLabels;
	}

	public ComboBox getStudentCombo() {
		return studentCombo;
	}

	public ArrayList<BarChart> getCharts() {
		return charts;
	}

	public ArrayList<Label> getStDevs() {
		return stDevs;
	}

}
