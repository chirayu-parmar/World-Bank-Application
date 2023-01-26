package Graphs;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.json.simple.parser.ParseException;
import DataFetch.AnalysisResult;
import loadGUI.*;

/*Stratergy design - the type of graph used is decided by stratergy during runtime
 * 
 * Facade design pattern - The graphs logic is hidden from the main UI
 * 
 * The Graphs interface is implemented by all the graph classes
 */
@SuppressWarnings("unused")
public interface Graphs {
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
	public default void createGraph(String analysis, String country, String fromDate, String toDate,
			AnalysisResult analysisResult, JPanel west) throws ParseException {
	}
}
