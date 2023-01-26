package Graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.util.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.parser.ParseException;
import BarState.BarOne;
import BarState.BarState;
import BarState.BarThree;
import BarState.BarTwo;
import DataFetch.AnalysisResult;

@SuppressWarnings("unused")

//Sate design pattern

public class BarGraph implements Graphs {
	private BarState state;

	/*
	 * calls the graph states to display result on the UI
	 * 
	 * @param analysis type String - analysis name.
	 * 
	 * @param country type String - Country value on which the analysis will be
	 * performed.
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param analysisResult type AnalysisResult - the analysis results data.
	 * 
	 * @param west type Jpanel - Main Ui on which the graph will be displayed
	 * 
	 */
	public void createGraph(String analysis, String country, String fromDate, String toDate,
			AnalysisResult analysisResult, JPanel west) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// fetching Data from Hashmap
		HashMap<String, HashMap<Integer, Double>> analysisResults = analysisResult.getTheData();
		System.out.println(analysisResults.size());

		// Calling appropriate State
		if (analysisResults.size() == 1) {
			state = new BarOne();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}

		if (analysisResults.size() == 2) {
			state = new BarTwo();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}
		if (analysisResults.size() == 3) {
			state = new BarThree();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}
	}
}