package BarState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

//State Design Pattern
/*
 * This class is the First state on the BarGraph where only one bar graph is displayed
 */
public class BarOne implements BarState {

	/*
	 * @param analysisResults type HashMap<String, HashMap<Integer, Double>> -
	 * Stores the name of the analysis as key then another nested hashmap with dates
	 * and data values
	 * 
	 * @param fromDate type String - Start date of the analysis
	 * 
	 * @param toDate type String - end date of the analysis
	 * 
	 * @param analysis type String - name of the analysis
	 * 
	 * @return ChartPanel
	 */
	@Override
	public ChartPanel getGraph(HashMap<String, HashMap<Integer, Double>> analysisResults, String fromDate,
			String toDate, String analysis) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// fetching data from HashMap and add data to dataset
		for (Map.Entry<String, HashMap<Integer, Double>> entry : analysisResults.entrySet()) {
			String analysisVariable = entry.getKey();
			System.out.println(analysisVariable);
			HashMap<Integer, Double> analysisData = entry.getValue();

			int start = Integer.parseInt(fromDate);
			int end = Integer.parseInt(toDate);

			for (int year = start; year <= end; year++) {
				System.out.println(year + "  " + analysisData.get(year));
				if (analysisData.get(year) != 0) {
					dataset.setValue(analysisData.get(year), analysisVariable, year + "");
				}
			}
		}

		// Plot Data and return to ui.
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));
		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		JFreeChart barChart = new JFreeChart(analysis, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;

	}

}
