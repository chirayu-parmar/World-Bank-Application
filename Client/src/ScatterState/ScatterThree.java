package ScatterState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

/*
 * State Design Pattern
 * This class is the Third state on the Scatter chart where the 3 plot are displayed
 */
public class ScatterThree implements ScatterState {

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

		TimeSeries series1 = new TimeSeries("");
		TimeSeries series2 = new TimeSeries("");
		TimeSeries series3 = new TimeSeries("");
		int series = 1;

		// add data to data set

		for (Map.Entry<String, HashMap<Integer, Double>> entry : analysisResults.entrySet()) {
			String analysisVariable = entry.getKey();
			if (series == 1) {
				series1 = new TimeSeries(analysisVariable);
			}

			if (series == 2) {
				series2 = new TimeSeries(analysisVariable);
			}
			if (series == 3) {
				series3 = new TimeSeries(analysisVariable);
			}
			System.out.println(analysisVariable);
			HashMap<Integer, Double> analysisData = entry.getValue();

			int start = Integer.parseInt(fromDate);
			int end = Integer.parseInt(toDate);

			for (int i = start; i <= end; i++) {

				System.out.println(i + "  " + analysisData.get(i));
				if (analysisData.get(i) != 0) {
					if (series == 1) {
						series1.add(new Year(i), analysisData.get(i));
					}
					if (series == 2) {
						series2.add(new Year(i), analysisData.get(i));
					}
					if (series == 3) {
						series3.add(new Year(i), analysisData.get(i));
					}
				}
			}
			series++;

		}
		// return chart panel to graph

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);

		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		JFreeChart scatterChart = new JFreeChart(analysis, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;
	}

}
