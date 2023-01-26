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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.json.simple.parser.ParseException;
import DataFetch.AnalysisResult;
import LineState.LineOne;
import LineState.LineState;
import LineState.LineThree;
import LineState.LineTwo;
import loadGUI.MainUI;

@SuppressWarnings("unused")
public class LineGraph implements Graphs {
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
	private LineState state;

	public void createGraph(String analysis, String country, String fromDate, String toDate,
			AnalysisResult analysisResult, JPanel west) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Fetch Data from HashMap
		HashMap<String, HashMap<Integer, Double>> analysisResults = analysisResult.getTheData();
		System.out.println(analysisResults.size());

		// Call appropriate State
		if (analysisResults.size() == 1) {
			state = new LineOne();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}

		if (analysisResults.size() == 2) {
			state = new LineTwo();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}
		if (analysisResults.size() == 3) {
			state = new LineThree();
			west.add(state.getGraph(analysisResults, fromDate, toDate, analysis));
		}
	}

}