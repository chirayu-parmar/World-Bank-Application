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

/*
 * State Design Pattern
 * This class is the second state on the BarGraph where the second bar graph is displayed in the MainUI
 */

public class BarTwo implements BarState {

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
	@SuppressWarnings("unused")
	@Override
	public ChartPanel getGraph(HashMap<String, HashMap<Integer, Double>> analysisResults, String fromDate,
			String toDate, String analysis) {

		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

		// Fetch data from hashmap and add to data set
		int series = 1;
		for (Map.Entry<String, HashMap<Integer, Double>> entry : analysisResults.entrySet()) {
			String analysisVariable = entry.getKey();
			System.out.println(analysisVariable);
			HashMap<Integer, Double> analysisData = entry.getValue();

			int start = Integer.parseInt(fromDate);
			int end = Integer.parseInt(toDate);

			for (int year = start; year <= end; year++) {
				System.out.println(year + "  " + analysisData.get(year));
				if (analysisData.get(year) != 0) {
					if (series == 1) {
						dataset1.setValue(analysisData.get(year), analysisVariable, year + "");
					}
					if (series == 2) {
						dataset1.setValue(analysisData.get(year), analysisVariable, year + "");
					}

				}
			}
			series++;
		}

		// Plot Graph and return to ui
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset1);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		JFreeChart barChart = new JFreeChart(analysis, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		// Different way to create bar chart
		/*
		 * dataset = new DefaultCategoryDataset();
		 * 
		 * dataset.addValue(3.946, "Unemployed", "Men"); dataset.addValue(96.054,
		 * "Employed", "Men"); dataset.addValue(3.837, "Unemployed", "Women");
		 * dataset.addValue(96.163, "Employed", "Women"); barChart =
		 * ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
		 * "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
		 */

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;

	}

}
