package Graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.json.simple.parser.ParseException;

import DataFetch.AnalysisResult;
import ScatterState.ScatterOne;
import ScatterState.ScatterState;
import ScatterState.ScatterThree;
import ScatterState.ScatterTwo;
import loadGUI.MainUI;

//Sate Design Pattern
@SuppressWarnings("unused")
public class ScatterChart implements Graphs {
	private ScatterState state;

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
		HashMap<String, HashMap<Integer, Double>> analysisResults = analysisResult.getTheData();
		System.out.println(analysisResults.size());

		// Calls Appropriate State
		if (analysisResults.size() == 1) {
			state = new ScatterOne();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}

		if (analysisResults.size() == 2) {
			state = new ScatterTwo();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}
		if (analysisResults.size() == 3) {
			state = new ScatterThree();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}
	}
}