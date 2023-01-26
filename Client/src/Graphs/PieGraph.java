package Graphs;

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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.simple.parser.ParseException;

import DataFetch.AnalysisResult;
import loadGUI.ErrorGUI;
import loadGUI.MainUI;

@SuppressWarnings("unused")
public class PieGraph implements Graphs {
	private ErrorGUI errorGUI;

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
		// Error GUI if data is not suitable for pie Graph
		this.errorGUI = new ErrorGUI();

		if (analysis.equals("Forest Area (% of land area)")
				|| analysis.equals("Government expenditure on education, total (% of GDP)")) {

			// Fetching Data
			HashMap<String, HashMap<Integer, Double>> test = analysisResult.getTheData();
			int analysisSize = test.size();

			if (analysisSize == 1) {

				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				double data = 0;

				for (Map.Entry<String, HashMap<Integer, Double>> entry : test.entrySet()) {
					String analysisVariable = entry.getKey();
					System.out.println(analysisVariable);
					HashMap<Integer, Double> analysisData = entry.getValue();
					// calculate other values and add data to dataset and then plot the graph to ui.

					int start = Integer.parseInt(fromDate);
					int end = Integer.parseInt(toDate);

					for (int i = start; i <= end; i++) {
						System.out.println(i + "  " + analysisData.get(i));
						data = data + analysisData.get(i);

					}
					data = data / analysisData.size();

					dataset.addValue(data, analysisVariable, analysis);
					dataset.addValue(100 - data, "other", analysis);

					// Plotting graph
					JFreeChart pieChart = ChartFactory.createMultiplePieChart(analysis, dataset, TableOrder.BY_COLUMN,
							true, true, false);
					pieChart.setBorderPaint(null);
					ChartPanel chartPanel = new ChartPanel(pieChart);
					chartPanel.setPreferredSize(new Dimension(400, 300));
					chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
					chartPanel.setBackground(Color.white);
					west.add(chartPanel);
				}
			}
		} else {
			// if data not appropriate then display error
			errorGUI.display("Pie Graph can not be rendered for " + analysis + "!", "Please remove it.");

		}
	}

}
