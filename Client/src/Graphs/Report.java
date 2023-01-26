package Graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jfree.chart.ChartPanel;
import org.json.simple.parser.ParseException;

import DataFetch.AnalysisResult;
import loadGUI.MainUI;

@SuppressWarnings("unused")
public class Report implements Graphs {

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
		int start = Integer.parseInt(fromDate);
		int end = Integer.parseInt(toDate);
		String reportMessage = analysis + "\n" + "==============================\n";

		// fetch Data
		HashMap<String, HashMap<Integer, Double>> analysisResults = analysisResult.getTheData();
		System.out.println(analysisResults.size());

		// add values to graph

		for (int year = start; year <= end; year++) {
			reportMessage += "\nYear " + year + ":\n";
			for (Map.Entry<String, HashMap<Integer, Double>> entry : analysisResults.entrySet()) {
				String analysisVariable = entry.getKey();
				System.out.println(analysisVariable);
				HashMap<Integer, Double> analysisData = entry.getValue();
				// Do things with the list
				double value = analysisData.get(year);

				System.out.println(year + "  " + new DecimalFormat("0.00").format(value));

				reportMessage += analysisVariable + " => " + new DecimalFormat("0.00").format(value) + "\n";

				continue;
			}
		}
		// Display text as report and display on ui
		JTextArea report = new JTextArea();
		report.setEditable(false);
//		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);

		System.out.println(reportMessage);

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);

		west.add(outputScrollPane);
	}
}